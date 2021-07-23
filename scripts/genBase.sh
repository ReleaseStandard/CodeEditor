#!/bin/bash

githubOwner=ReleaseStandard
function genB() {
	echo "![$1](https://github.com/${githubOwner}/CodeEditor-$2/actions/workflows/$1.yml/badge.svg)"
}
function genJ() {
	echo "[![](https://jitpack.io/v/$1/$2.svg)](https://jitpack.io/#$1/$2)"
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
	echo "| [$p](https://github.com/${githubOwner}/CodeEditor-$p/) | $links  | $(genJ ${githubOwner} CodeEditor-$p)"
done
