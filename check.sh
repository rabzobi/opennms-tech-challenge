#!/bin/bash
#
# Run the check code
#
# TODO: write batch file for windows 
#
# Author: Rob
# Date: 5 June 2022

FILENAME=$1
INPUT=$2
SEARCHMETHOD=$3
SCRIPTDIR=$( dirname -- "$0"; )

JAR="$SCRIPTDIR/target/tech-challenge-1.0-SNAPSHOT-jar-with-dependencies.jar"
PREFCLASS="za.co.garland.opennms.PrefixCheck"

if [ ! -f $JAR ] ; then
	echo "$JAR does not exist, try building with:"
	echo "mvn clean compile assembly:single"
	exit 1
fi

if [ -z $FILENAME ] ; then
	echo "Usage: $0 YAMLFILE [INPUTFILE] [ALGORITHM]"
	echo "YAMLFILE is a compulsory config file with the prefixes in it"
	echo "INPUTFILE is an optional input (which is piped via stdin) useful for perf tests"
	echo "ALGORITHM is also optional"
	echo "Valid ALGORITHMs are linear|half|tree"
	echo "If no ALGORITHM is given it defaults to half as it has performed best"
	echo 

	echo "linear= A standard linear search, queries data as is in yaml file, no need to spend time sorting"
	echo "half= Half interval search, sorts data and performs half searches"
	echo "tree= Tree search, builds tree structure and traverses the tree following the prefix"
	exit 2
fi

if [ ! -f $FILENAME ] ; then
	echo "File $FILENAME does not exist. You can generate sample data with the gen-sample-prefixes.sh"
	exit 3
fi


if [ -f $INPUT ] ; then
	cat $INPUT | java -cp $JAR $PREFCLASS $FILENAME $SEARCHMETHOD
else
	java -cp $JAR $PREFCLASS $FILENAME $SEARCHMETHOD
fi