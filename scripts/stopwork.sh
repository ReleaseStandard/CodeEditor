#!/bin/bash
#
# This script is responsible from removing a module from CodeEditor-application.
#
#

if [ "$#" -eq "0" ] ; then
	echo "You must give plugins as arguments here ..."
	exit 1
fi

tmp="/tmp/stopwork${RANDOM}${RANDOM}";

function init() {
	finit
	> "$tmp";
}
function finit() {
	echo "Removing $tmp ...";
	sleep 3;
	rm -fr "$tmp";
}
for plugin in "$@" ; do
	echo "Stop working on $plugin"
	rm "$plugin"
	rm -r ".git/modules/${plugin}";
	grep -v "${plugin}" .gitmodules > "$tmp";
	mv "$tmp" .gitmodules
done
