# opennms-tech-challenge

Check if OID exists in list of traps.
This is done by searching for a trap that starts with the OID.

To compile I used 
 1. Oracle JDK 17.0.1 (Anything above 8 should work as support for lamdba expression is required)
 2. Apache Maven 3.6.2 but more recent versions should work
 
To build:
  mvn clean compile assembly:single

To run: 
 ./check [yaml-filename]
yaml-filename contains the prefixes for this run

You can also pass [input-filename] and [algorithm]

There are 3 algorithms to choose from
1. A standard linear search, queries data as is in yaml file
2. Binary search, sorts data and performs half searches
3. Tree search, builds tree structure and traverses the tree following the prefix

I have not done extensive performance testing but I found the binary search to have the best performance. The tree search should perform best with large amounts of data but currently the tree construction slows it down.

If you need some sample data, use ./scripts/gen-sample-prefixes.sh
