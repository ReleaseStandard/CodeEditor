#!/bin/bash
#
# This repo is used to lexer and parser from g4 files.
# 
#
#
#
#
function prereq() {
	echo "";
	echo " Checking prerequisites ..."
	echo "";
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
	echo "";
	echo "";
	echo "";
	return $res;
}
repo="https://github.com/antlr/grammars-v4/";
tmp="/tmp/";
gitfolder="$tmp/grammars-v4/";
antlr="https://www.antlr.org/download/antlr-4.9.2-complete.jar";
rootfolder="$(pwd)";
declare -A mapping;

#
# Mapping between CodeEditor module and antlr g4s
#
#mapping["codeeditormodule"]="antlr g4"
mapping["mksh"]="";
mapping["java"]="";
mapping["python"]="";
mapping["golang"]="golang";
mapping["cobol85"]="cobol85";
mapping["html"]="";


prereq || exit 1;
cd "$tmp";
wget -c "$antlr";
antlr="$tmp/${antlr/*\//}";
git clone --depth=1 "$repo";
cd "$gitfolder"
git pull


for k in "${!mapping[@]}" ; do
	v="${mapping[$k]}";
	
	cd "$gitfolder/$v";
	java -jar "$antlr" *.g4;
	cp *.g4 "$rootfolder/language-$k/src/main/java/io/github/rosemoe/editor/langs/cobol85/";
	for file in *.java ; do
		dest="$rootfolder/language-$k/src/main/java/io/github/rosemoe/editor/langs/cobol85/$file";
		echo "package package io.github.rosemoe.editor.langs.$k;" > "$dest";
		cat "$file" >> "$dest";
	done
	cd "$gitfolder";
done








