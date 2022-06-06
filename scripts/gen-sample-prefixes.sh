#!/bin/bash
#
# Generate some random number strings
#
# Author: Rob
# Date: 03/06/2022
#

FILE=$1
LINES=$2

if [ -z $LINES ] ; then
	echo "Usage: $0 [YAMLFILE] [LINES]"
	exit 1
fi

if [ -f $FILE ] ; then
	echo "File $FILE exists"
	exit 2
fi

echo "trap-type-oid-prefix:" > $FILE

echo "Generating $LINES lines"

for l in $( eval echo {1..$LINES} ); do
	LINE=""
	for part in {1..10}; do		
  		RAND=$(( ( RANDOM % 99 )  + 1 ))
  		LINE="$LINE.$RAND"
  	done
    echo "- $LINE" >> $FILE
done

echo "Done"

echo "======= LINE COUNT ======="
wc -l $FILE 

echo "======= UNIQ CHECK ======="
cat $FILE | sort -n | uniq -c | sort -n | tail
