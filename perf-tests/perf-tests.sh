#!/bin/bash

echo "Line counts"
wc -l large.yaml
wc -l large-in.txt

echo -e "\n\nTree Search"
time ../check.sh large.yaml large-in.txt tree > large-out-tree.txt

echo -e "\n\nLinear Search"
time ../check.sh large.yaml large-in.txt linear > large-out-linear.txt

echo -e "\n\nHalf Search"
time ../check.sh large.yaml large-in.txt half > large-out-half.txt

#Verify output
diff large-out-tree.txt large-out-linear.txt
diff large-out-tree.txt large-out-half.txt