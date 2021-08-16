#!/bin/bash
#
# update workflows on github
#

rootModification=$(git status -s .github/)
echo "rootModification=$rootModification"
if [ "$FORCE" = "" ] ; then
	FORCE=false
fi
git submodule foreach --recursive bash -c "if $FORCE || ! [ \"$rootModification\" = \"\" ] ; then \
		if [ -d ".github" ] ; then \
			cp -r ../.github/* ./.github/ ; \
			rm -f ./.github/workflows/testBuildSystem.yml ; \
			if ! [ \"\$(git status -s .github/)\" = \"\" ] ; then \
		                git add .github/ ; \
				git commit -m \"update workflow\" ; \
		                git push origin ; \
			fi ; \
		fi ; \
        fi"

