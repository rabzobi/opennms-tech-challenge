package za.co.garland.opennms;

import java.io.IOException;
import java.util.Scanner;

/**
 * Instantiate a new tool with a specified yaml config file and an algorithm.
 * Read input from stdin and output to stdout
 * Some messages may be printed to stderr 
 * 
 * @author robert
 */
public class PrefixCheck {
	
	private static TrapTools newTool(String filename,TrapTools.SearchAlgorithm algorithm) {
		try {
			return new TrapTools(filename,algorithm);
		} catch (IOException e) {
			System.err.println("Error reading filename: "+filename);
			System.exit(2);
		}
		return null;
	}

	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("No filename arguement given");
			System.exit(1);
		}
		TrapTools.SearchAlgorithm sa = TrapTools.SearchAlgorithm.BINARY;
		if (args.length == 1) {
			System.err.println("No algorithm given, defaulting to bin");			
		} else if (args[1].equals("bin")) {
			sa = TrapTools.SearchAlgorithm.BINARY;
		} else if (args[1].equals("linear")) {
			sa = TrapTools.SearchAlgorithm.LINEAR;			
		} else if (args[1].equals("tree")) {
			sa = TrapTools.SearchAlgorithm.TREE;			
		} else {
			System.err.println("Invalid algorithm given, defaulting to bin");
		}
		
		TrapTools tool = newTool(args[0],sa);
		
        Scanner stdin = new Scanner(System.in);
        String line;
        System.out.println("Type exit to quit");
        while(stdin.hasNextLine() && !( line = stdin.nextLine() ).equals( "exit" )) {
        	System.out.println(line+": "+tool.search(line));        	
        }
        stdin.close();		
	}

}
