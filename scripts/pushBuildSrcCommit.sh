#!/bin/bash

git submodule foreach --recursive "if ! [ \"\$(basename \$PWD)\" = \"buildSrc\" ] ; then git add buildSrc/ ; git commit -m 'update' ; git push --set-upstream origin master ; fi"
