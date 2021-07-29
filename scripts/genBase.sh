#!/bin/bash

githubOwner=ReleaseStandard
function genB() {
	echo "![$1](https://github.com/${githubOwner}/CodeEditor-$2/actions/workflows/$1.yml/badge.svg)"
}
function genJ() {
	echo "[![](https://jitpack.io/v/$1/$2.svg)](https://jitpack.io/#$1/$2)"
}
function genMC() {
	echo "![](https://maven-badges.herokuapp.com/maven-central/io.github.${1}.CodeEditor/$2/badge.svg)"
}
function genSonatype() {
	echo "![](https://img.shields.io/nexus/r/io.github.${1}.CodeEditor/${2}?server=https%3A%2F%2Fs01.oss.sonatype.org)"
}
echo "| Piece    |  Status |  Artifacts  |"
echo "|----------|---------|-------------|"
for p in application language-cobol85 language-java language-universal widget-symbolinput editor \
          language-golang language-mksh logger-debug emptyPlugin language-html language-python widget-linenumber ; do
	links=""
	for f in $(ls "CodeEditor-$p/.github/workflows/") ; do
		f="${f/.yml/}"
		links="$links $(genB $f $p)"
	done
	echo "| [$p](https://github.com/${githubOwner}/CodeEditor-$p/) | $links  | $(genJ ${githubOwner} CodeEditor-$p) $(genSonatype ${githubOwner} CodeEditor-$p)"
done
