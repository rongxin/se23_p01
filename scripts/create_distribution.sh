#!/bin/bash
DIST_PATH=../dist/se23_p01
PROJECT_PATH=..

echo "creating distribution folder"
mkdir -p $DIST_PATH
rm -rf $DIST_PATH/*

echo "copy source code to distribution folder"
cp -r $PROJECT_PATH/src/ $DIST_PATH
cp -r $PROJECT_PATH/test/ $DIST_PATH
cp -r $PROJECT_PATH/lib/ $DIST_PATH

echo "copy docs to distribution folder"
cp -r $PROJECT_PATH/docs/ $DIST_PATH

echo "create data and classes folder"
mkdir -p $DIST_PATH/data
mkdir -p $DIST_PATH/classes

echo "copying scripts to distribution"
cp $PROJECT_PATH/scripts/setenv.bat $DIST_PATH/
cp $PROJECT_PATH/scripts/compile.bat $DIST_PATH/
cp $PROJECT_PATH/scripts/run.bat $DIST_PATH/

cp $PROJECT_PATH/scripts/setenv.sh $DIST_PATH/
cp $PROJECT_PATH/scripts/compile.sh $DIST_PATH/
cp $PROJECT_PATH/scripts/run.sh $DIST_PATH/
cp $PROJECT_PATH/scripts/initdata.sh $DIST_PATH/
