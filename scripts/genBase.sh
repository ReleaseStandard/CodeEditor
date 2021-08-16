#!/bin/bash
#
# Generate links and shields from repo owner and modules inside this repo
#

githubOwner=ReleaseStandard
function genB() {
	echo "[![$1](https://github.com/${githubOwner}/CodeEditor-$2/actions/workflows/$1.yml/badge.svg)](https://github.com/ReleaseStandard/CodeEditor-$2/actions/workflows/$1.yml)"
}
function genJ() {
	echo "[![](https://jitpack.io/v/$1/$2.svg)](https://jitpack.io/#$1/$2)"
}
function genMC() {
	echo "[![](https://img.shields.io/maven-central/v/io.github.${1}.CodeEditor/${2/CodeEditor-/})](https://repo1.maven.org/maven2/io/github/${1}/CodeEditor/${2/CodeEditor-/}/)"
}
function genSonatype() {
	echo "[![](https://img.shields.io/nexus/r/io.github.${1}.CodeEditor/${2/CodeEditor-/}?server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/releases/io/github/${1}/CodeEditor/${2/CodeEditor-/}/)"
}
echo "| Piece    |  Status |  Artifacts  |"
echo "|----------|---------|-------------|"
EXCLUDED=(buildSrc CodeEditor.wiki)
for p in $(git submodule foreach "echo \$name >&2" 2>&1 1>/dev/null) ; do
	if [[ "${EXCLUDED[@]}" =~ "${p}" ]]; then continue ; fi
	links=""
	for f in $(ls "$p/.github/workflows/") ; do
		f="${f/.yml/}"
		links="$links $(genB $f $p)"
	done
	shields=""
	for m in genSonatype genMC ; do
		shields="${shields} $($m ${githubOwner} CodeEditor-$p)"
	done
	echo "| [$p](https://github.com/${githubOwner}/CodeEditor-$p/) | $links  | ${shields}"
done
