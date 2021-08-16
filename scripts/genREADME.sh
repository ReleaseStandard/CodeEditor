#!/bin/bash
#
# Generate a read me for submodules
#

function p() {
	echo "$@"
}
#out=/dev/stdout
out=/tmp/tmp${RANDOM}${RANDOM}${RANDOM}tmp
githubOwner=ReleaseStandard
EXCLUDED=(buildSrc CodeEditor.wiki)
for p in $(git submodule foreach "echo \$name >&2" 2>&1 1>/dev/null) ; do
	if [[ "${EXCLUDED[@]}" =~ "${p}" ]]; then continue ; fi
	echo "# CodeEditor module $p" >$out
	echo "" >$out
	./scripts/genBase.sh |head -n 2 >$out
	./scripts/genBase.sh |grep "$p" >$out

	>$out cat <<"EOT"

### How to integrate this to your application
In the top level ```build.gradle```
```gradle
buildscript {
    repositories {
        mavenCentral()
    }
}
```
In the module level ```build.gradle```
```gradle
dependencies {
EOT
echo "    implementation 'io.github.${githubOwner}.CodeEditor:$p:+'" >$out
>$out cat <<"EOT"
}
```

EOT
	r=README.md
	if ! [ -f "$p/$r" ] ; then
		op="$(pwd)"
		cd $p
		echo " * Need to gen README.md in $p"
		mv $out $r
		git add $r
		git commit -m "Update README.md"
		cd $op
	fi
done
