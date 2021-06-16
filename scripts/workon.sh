#!/bin/bash
#
# Simple script to start working on some modules
#
#

base="https://github.com/ReleaseStandard/";
if [ "$#" -eq "0" ] ; then
	echo "You must give plugins as arguments here ...";
	exit 1;
fi


for plugin in "$@" ; do
	echo "Working on $plugin";
	url="${base}/${plugin}";
	git submodule add "$url";
	rm -r "${plugin}";
	ln -rs "../${plugin}" ./
done
