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

JAR="target/tech-challenge-1.0-SNAPSHOT-jar-with-dependencies.jar"
PREFCLASS="za.co.garland.opennms.PrefixCheck"

if [ ! -f $JAR ] ; then
	echo "$JAR does not exist, try building with:"
	echo "mvn clean compile assembly:single"
	exit 1
fi

if [ -z $FILENAME ] ; then
	echo "Usage: $0 YAMLFILE [INPUTFILE] [ALGORITHM]"
	echo "INPUTFILE is an optional input"
	echo "ALGORITHM is also optional"
	echo "Valid ALGORITHMs are linear|bin|tree"
	echo "If no ALGORITHM is given it defaults to bin"
	echo 
	exit 2
fi

if [ ! -f $FILENAME ] ; then
	echo "File $FILENAME does not exist"
	exit 3
fi


if [ -f $INPUT ] ; then
	cat $INPUT | java -cp $JAR $PREFCLASS $FILENAME $SEARCHMETHOD
else
	java -cp $JAR $PREFCLASS $FILENAME $SEARCHMETHOD
fi