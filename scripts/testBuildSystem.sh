#!/bin/bash
#
# This script test the build system
# it does not test the correctness of the program
#

output=/dev/null
if ! [ "$OUTPUT" = "" ] ; then output="$OUTPUT" ; fi
success=0
failure=0
RED="\033[01;31m"
GREEN="\033[01;32m"
ZERO="\033[0m"
>$output
_EXCLUDED_MODULES=(buildSrc CodeEditor.wiki)
_EXCLUDED_MODULES=("${_EXCLUDED_MODULES[@]}" "${EXCLUDED_MODULES[@]}")

function assert() {
	local rv="$?"
	if ( [ "$3" = "" ] && [ "$rv" -eq "0" ] ) || ( [ "$2" = "$3" ] && ! [ "$3" = "" ] ) ; then
		printf "[${GREEN}SUCCESS${ZERO}] $1\n"
		((success=success+1))
		return 0
	fi
	echo "RESULTS" >>$output
	echo " $2" >>$output
	echo "EXPECTED" >>$output
	echo " $3" >>$output
	printf "[${RED}FAILURE${ZERO}] $1\n"
	((failure=failure+1))
	return 1
}
function init() {
	echo "Launching tests ..."
}
function finit() {
	result="success"
	if [ "$failure" -ne "0" ] ; then result="fail" ; fi
	printf "Build ${result} failure=${RED}${failure}${ZERO}, success=${GREEN}${success}${ZERO}\n"
}
function testIntro() {
	echo ""
	echo "$@"
}
function moduleExcluded() {
	for m in "${_EXCLUDED_MODULES[@]}" ; do 
		if [ "$m" = "$1" ] ; then
			return 0
		fi
	done
	return 1
}

init

CMD=("$@")
UNITS=(true true true)
for i in "${!CMD[@]}" ; do
	UNITS[$i]=${CMD[$i]}
done
#UNITS=(false false true)

if ${UNITS[0]} ; then
	testIntro "Project count"
	assert "project count 33" "$(./gradlew projects |grep -c 'Project')" "33"
fi

if ${UNITS[1]} ; then
	testIntro "build and test"
	echo "First cleaning"
        ./gradlew clean 2>/dev/null 1>&2
	for mod in $(git submodule --quiet foreach 'echo $sm_path') ; do
		moduleExcluded "$mod" && continue
		assert "from root :${mod}:assemble" "$(./gradlew :${mod}:assemble 2>&1)"
	        assert "from root :${mod}:test" "$(./gradlew :${mod}:test 2>&1)"
	done
	echo "Waiting builds"
	wait $(jobs -p)
	./gradlew clean 2>/dev/null 1>&2
	for mod in $(git submodule --quiet foreach 'echo $sm_path') ; do
		moduleExcluded "$mod" && continue
		op="$(pwd)"
                cd "${mod}"
                assert "from ${mod} assemble" "$(./gradlew clean assemble 2>&1)"
                assert "from ${mod} test" "$(./gradlew test 2>&1)"
                cd "$op"
	done
fi

if ${UNITS[2]} ; then
	testIntro "Inspecting content of builds"
	outs="build/outputs/"
	for mod in $(git submodule --quiet foreach 'echo $sm_path') ; do
		moduleExcluded "$mod" && continue
	        op="$(pwd)"
	        cd "${mod}"


		## Checking aar's
		AAR=($(find $out -name "*release*.aar"))
		for a in "${AAR[@]}" ; do
			pathInSrc="${op}/${mod}/${a/build*outputs*aar*.aar/}"
			PATHS=""
			id="/tmp/id${RANDOM}${RANDOM}"
			op2="$(pwd)"
			mkdir -p "$id"
			cp "$a" "$id"
			cd "$id"
			unzip $id/* 2>/dev/null >/dev/null
			rm *.aar
			for f in $(find -name "*.jar") ; do
				PATHS="${PATHS}$(unzip -l $f)"
			done
			fail=false
			for filePath in $(find "$pathInSrc" -name "*.java") ; do
                                echo "$filePath"|grep -E "(buildSrc|src/test|src/androidTest|build/generated)">/dev/null 2>/dev/null
                                if [ "$?" = "0" ] ; then continue ; fi
				file="${filePath/*\/src\/main\/java\//}"
				file="${file/%.java/.class}"
				echo "$PATHS" | grep "$file" 2>/dev/null >/dev/null
				if ! [ "$?" = "0" ] ; then
					echo "class $file" >$output
					fail=true
				fi
			done
			if $fail ; then
                                assert "AAR INSPECTION $mod ($a)" 1 0
			else
                                assert "AAR INSPECTION $mod ($a)" 1 1
			fi
			rm -fr "$id"
			cd "$op2"
		done


		## Checking jars
		JAR=($(find "${op}/${mod}" -wholename "*/build/*.jar"))
		for j in "${JAR[@]}" ; do
			echo "$j" | grep -E "(javadoc|sources|intermediates|buildSrc|aar_rebundle)" 2>/dev/null >/dev/null
			if [ "$?" = "0" ] ; then continue ; fi
			pathInSrc="${j/build*libs*}"
			PATHS="$(unzip -l $j)"
			fail=false
			for filePath in $(find "$pathInSrc" -name "*.java") ; do
                                echo "$filePath"|grep -E "(buildSrc|src/test|src/androidTest|build/generated)">/dev/null 2>/dev/null
				if [ "$?" = "0" ] ; then continue ; fi
				file="${filePath/*\/src\/main\/java\//}"
                                file="${file/%.java/.class}"
                                echo "$PATHS" | grep "$file" 2>/dev/null >/dev/null
                                echo "$PATHS" | grep "$file" 2>/dev/null >/dev/null
                                if ! [ "$?" = "0" ] ; then
                                        echo "class $file" >$output
                                        fail=true
                                fi
			done
	                if $fail ; then
	                        assert "JAR INSPECTION $mod ($j)" 1 0
	                else
                                assert "JAR INSPECTION $mod ($j)" 1 1
	                fi
		done
		cd "$op"
	done
fi

if ${UNITS[3]} ; then
	testIntro "Test signatures of builds"
	./gradlew assemble signPublication >/dev/null 2>&1
	for f in $(find -name "*.asc") ; do
		assert "Signature of $f" "$(gpg --verify $f ${f/%.asc/} 2>&1)"
	done
fi

if ${UNITS[4]} ; then
	testIntro "Test live modifications"
	f=logger-debug/logger-debug/src/main/java/io/github/rosemoe/editor/core/util/Logger.java
	cp "$f" "/tmp/"
	echo "TEST" >> "$f"
	./gradlew :editor:assemble 1>/dev/null 2>&1
	RV="$?"
	assert "on dependency editor -> logger-debug" "$RV" "1"
	mv "/tmp/${f/*\//}" "$f"
fi

finit


