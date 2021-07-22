#!/bin/bash


function genB() {
	echo "![$1](https://github.com/ReleaseStandard/CodeEditor-$2/actions/workflows/$1.yml/badge.svg)"
}
echo "| Piece    |  Status |"
echo "|----------|-------|"
for p in application language-cobol85 language-java language-universal widget-symbolinput editor \
          language-golang language-mksh logger-debug emptyPlugin language-html language-python widget-linenumber ; do
	links=""
	for f in $(ls "CodeEditor-$p/.github/workflows/") ; do
		f="${f/.yml/}"
		links="$links $(genB $f $p)"
	done
	echo "| $p | $links  |"
done
