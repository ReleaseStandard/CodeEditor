#!/bin/bash

git submodule foreach --recursive "if [ \"\$(basename \$PWD)\" = \"buildSrc\" ] ; then git pull --rebase origin master & fi"

