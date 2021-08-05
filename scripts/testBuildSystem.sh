#!/bin/bash
# That that every thing is working in the build system
#
#
success=0
failure=0
function assert() {
	local rv="$?"
	if ( [ "$3" = "" ] && [ "$rv" -eq "0" ] ) || ( [ "$2" = "$3" ] && ! [ "$3" = "" ] ) ; then
		printf "[\033[01;32mSUCCESS\033[0m] $1\n"
		((success=success+1))
		return 0
	fi
	echo "RESULTS"
	echo " $2"
	echo "EXPECTED"
	echo " $3"
	printf "[\033[01;31mFAILURE\033[0m] $1\n"
	((failure=failure+1))
	return 1
}
function init() {
	echo "Launching tests ..."
}
function finit() {
	if [ "$failure" -eq "0" ] ; then
		echo "Tests done failure=${failure}, success=${success}"
	else
		echo "Tests failure failure=${failure}, success=${success}"
	fi
}

init

#
## asserts
#
assert "project count 33" "$(./gradlew projects |grep -c 'Project')" "33"
for mod in logger-debug editor widget-linenumber ; do
	assert "build ${mod}" "$(./gradlew :CodeEditor-${mod}:assemble)"
	op="$(pwd)"
	cd "CodeEditor-${mod}"
        assert "build from folder ${mod}" "$(./gradlew assemble)"
	cd "$op"
done

finit
