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


if [ -z $FILENAME ] ; then
	echo "Usage: $0 YAMLFILE INPUTFILE [linear|bin|tree]"
	exit 1
fi

if [ ! -f $FILENAME ] ; then
	echo "File $FILENAME does not exist"
	exit 2
fi


JARS="target/tech-challenge-1.0-SNAPSHOT-jar-with-dependencies.jar"

PREFCLASS="za.co.garland.opennms.PrefixCheck"

if [ -f $INPUT ] ; then
	cat $INPUT | java -cp $JARS $PREFCLASS $FILENAME $SEARCHMETHOD
else
	java -cp $JARS $PREFCLASS $FILENAME $SEARCHMETHOD
fi