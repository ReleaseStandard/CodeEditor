#!/bin/bash
#
# update workflows on github
#


git submodule foreach --recursive bash -c 'if ! [ "$(git status -s .github/)" = "" ] ; then \
		cp ../.github/workflows/* ./.github/workflows/
                git add .github/ ; \
		git commit -m "update workflow" ; \
                git push origin ; \
        fi'

