function execOnAll() {
	for f in "$@" ; do
		for m in CodeEditor-emptyPlugin CodeEditor-language-html CodeEditor-language-python \
			 CodeEditor-widget-linenumber CodeEditor-application CodeEditor-language-cobol85 CodeEditor-language-java \
			 CodeEditor-editor CodeEditor-language-golang CodeEditor-language-mksh CodeEditor-logger-debug \
			 CodeEditor-language-universal CodeEditor-widget-symbolinput ; do
			"$f" "$m"
		done
	done
}

function execOnAllCTX() {
	local cmd="$1"
	function _a() {
		local op=$(pwd) ; 
		cd "$1" ;
		"$cmd" "$1"
		cd "$op" ;
	}
	execOnAll _a
}
