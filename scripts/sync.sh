#!/bin/bash
#
#
# This is used to synchronize for a given mapping (see the mapping variable below)
#  grammar from antlr4 project into CodeEditor https://github.com/antlr/grammars-v4/tree/master/golang
#
# Author: Release Standard
#
function display_title() {
	echo "";
	echo "          $@";
	echo "";
}
function prereq() {
	display_title "Checking prerequisites ..."
	res=0;
	TOINSTALL=();
	for cmd in "git" "wget" "java" ; do
		if  whereis "$cmd" |grep " " ; then
			printf " \033[01;32mOK\033[0m\n";
		else
			echo "$cmd:"
			printf " \033[01;31mNOK\033[0m\n";
			TOINSTALL[${#TOINSTALL[@]}]="$cmd";
			res=1;
		fi
	done
	if [ "${#TOINSTALL[@]}" -gt "0" ] ; then
		read -p "Missing deps, try to install ?(y/n)> "
		if [ "$REPLY" = "y" ] || [ "$REPLY" = "Y" ] ; then
			sudo apt-get install -y "${TOINSTALL[@]}"
		fi
	fi
	display_title;
	return $res;
}
repo="https://github.com/antlr/grammars-v4/";
tmp="/tmp/";
gitfolder="$tmp/grammars-v4/";
antlr="https://www.antlr.org/download/antlr-4.9.2-complete.jar";
rootfolder="$(pwd)";
declare -A mapping;

#
#
# Mapping between CodeEditor module and antlr g4s
#  ex: put the antlr cobol85 into language-cobol85   => mapping["cobol85"]="cobol85";
#      put the antlr azerty  into language-azert     => mapping["azert"]="azerty";
#      put the antlr abc     into language-bb        => mapping["bb"]="abc";
#
#mapping["codeeditormodule"]="antlr g4"
mapping["mksh"]="";
mapping["java"]="";
mapping["python"]="";
#mapping["golang"]="golang";
mapping["cobol85"]="cobol85";
mapping["html"]="";


prereq || exit 1;
cd "$tmp";
wget --timeout=1 --dns-timeout=1 --connect-timeout=1 --read-timeout=1 -c "$antlr";
antlr="$tmp/${antlr/*\//}";
git clone --depth=1 "$repo";
cd "$gitfolder"
git pull


for k in "${!mapping[@]}" ; do

	v="${mapping[$k]}";
	antlrfolder="$gitfolder/$v"
	if ! [ -d "$antlrfolder" ] || [ "$v" = "" ] ; then continue; fi
	cd "$antlrfolder";


	display_title "Processing $v antlrfolder=$antlrfolder";
	java -jar "$antlr" *.g4;
	cp *.g4 "$rootfolder/language-$k/src/main/java/io/github/rosemoe/editor/langs/$k/";
	for file in *.java ; do
		dest="$rootfolder/language-$k/src/main/java/io/github/rosemoe/editor/langs/$k/$file";
		echo "package io.github.rosemoe.editor.langs.$k;" > "$dest";
		cat "$file" >> "$dest";
	done
	cd "$gitfolder";
	display_title "***";
done








cd "$rootfolder";
./gradlew assemble || exit 1;
git add .
git commit -m "Languages synchronize with https://github.com/antlr/grammars-v4/"
