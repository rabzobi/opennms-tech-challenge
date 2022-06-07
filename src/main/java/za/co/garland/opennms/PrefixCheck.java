package za.co.garland.opennms;

import java.io.IOException;
import java.util.Scanner;

import za.co.garland.opennms.TrapTools.SearchAlgorithm;

/**
 * Instantiate a new tool with a specified yaml config file and an algorithm.
 * Read input from stdin and output to stdout
 * Some messages may be printed to stderr 
 * 
 * @author robert
 */
public class PrefixCheck {
	
	/**
	 * Create new tool instance with a specific algorithm (default to search)
	 * and read input from stdin, check prefix and output to std out
	 * 
	 * @param args command line args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("No filename arguement given");
			System.exit(1);
		}
		
		TrapTools.SearchAlgorithm sa = selectAlgorithm(args);		
		TrapTools tool = newTool(args[0],sa);
		
        Scanner stdin = new Scanner(System.in);
        String line;
        System.out.println("Type exit to quit");
        while(stdin.hasNextLine() && !( line = stdin.nextLine() ).equals( "exit" )) {
        	System.out.println(line+": "+tool.search(line));        	
        }
        stdin.close();		
	}
	
	/**
	 * Create a new instance of trap tools given a specific yaml config file and search algorithm
	 * @param filename
	 * @param algorithm
	 * @return the new instance
	 */	
	private static TrapTools newTool(String filename,TrapTools.SearchAlgorithm algorithm) {
		try {
			return new TrapTools(filename,algorithm);
		} catch (IOException e) {
			System.err.println("Error reading filename: "+filename);
			System.exit(2);
			return null; // will never reach here
		}		
	}	

	/**
	 * Given a string parm, select algorithm from search enum
	 * 
	 * @param args string array
	 * @return selected algorithm
	 */
	private static SearchAlgorithm selectAlgorithm(String[] args) {
		TrapTools.SearchAlgorithm sa = TrapTools.SearchAlgorithm.HALF;
		if (args == null || args.length == 1 || args[1] == null) {
			System.err.println("No algorithm given, defaulting to half");			
		} else if (args[1].equals("half")) {
			sa = TrapTools.SearchAlgorithm.HALF;
		} else if (args[1].equals("linear")) {
			sa = TrapTools.SearchAlgorithm.LINEAR;			
		} else if (args[1].equals("tree")) {
			sa = TrapTools.SearchAlgorithm.TREE;			
		} else {
			System.err.println("Invalid algorithm given, defaulting to half");
		}
		
		return sa;
	}

}
