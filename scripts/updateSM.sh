#!/bin/bash
#
#
# simple probleme:
#  submodule of git repo don't track HEAD of their repo.
#  so we have to update manually.
# ex: buildSrc debug-logger.
#
# Related issue:
#  https://stackoverflow.com/questions/8191299/update-a-submodule-to-the-latest-commit
#
op="$(pwd)"
branch="master"

function init() {
	echo ""
	echo "$@"
}
function finit() {
	echo "$@"
	echo ""
}



# update top level submodules
init "Syncing toplevel submodules ..."
git submodule update --remote --merge

# update nested submodules
for module in $(git submodule | sed 's/^[ \t]*[^ \t]\+[ \t]*\([^ \t]\+\)[ \t]*.*/\1/g') ; do
	cd "$module"
	init "Updating modules of $module ..."
	git checkout master
	git submodule update --remote --merge
	git add .
	git commit --amend --no-edit
	git push --set-upstream origin +HEAD:$branch
#	git tag -d latest
#	git tag latest
	git push --tags -f
	finit
	cd "$op"
	git add "${module}"
done

# Commit top level submodules modifications
git add .
git commit --amend --no-edit
git push --set-upstream origin +HEAD:main
finit
