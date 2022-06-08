# opennms-tech-challenge

Check if OID exists in list of traps.
This is done by searching for a trap that starts with the OID.

To compile I used 
 1. Oracle JDK 17.0.1 (Anything above 8 should work as support for lamdba expression is required)
 2. Apache Maven 3.6.2 but more recent versions should work
 
To build:
  mvn clean compile assembly:single
  
To run unit tests: 
  mvn test

To run: 
 ./check YAMLFILE [INPUTFILE] [ALGORITHM]
 
  1. YAMLFILE is a compulsory config file with the prefixes in it
  2. INPUTFILE is an optional input (which is piped via stdin) useful for perf tests
  3. ALGORITHM is also optional
  
  Valid ALGORITHMs are linear|half|tree
  If no ALGORITHM is given it defaults to half as it has performed best

There are 3 algorithms to choose from
1. linear= A standard linear search, queries data as is in yaml file, no need to spend time sorting
2. half= Half interval search. Data is sorted at startup
3. tree= Tree search, traverses the tree using the given prefix 

I have not done extensive performance testing but I found the half-interval search to have the best performance.
I found the tree construction to be slow but searches were reasonably efficient

If you need some sample data, use: ./scripts/gen-sample-prefixes.sh
(this just just generates some sample yaml data, it isn't real snmp which will affect the algorithm to choose).

Development was done with Eclipse 2021-12 (4.22.0). I have also tested with OpenJDK 11.0.15 on Debian 11 (bullseye).
