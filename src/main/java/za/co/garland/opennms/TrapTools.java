package za.co.garland.opennms;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Determine if a trap matches a prefix from a YAML file. This class provides
 * two implementations 1. Linear search for unsorted data 2. Binary search for
 * sorted data 3. Tree search
 * 
 * @author robert
 */
public class TrapTools {

	enum SearchAlgorithm {
		LINEAR, HALF, TREE
	}

	private Traps traps;
	private Tree tree;
	private SearchAlgorithm algorithm;

	/**
	 * Construct a tools object and load a datafile
	 * 
	 * @throws IOException
	 */
	public TrapTools(String filename, SearchAlgorithm algorithm) throws IOException {
		this.algorithm = algorithm;
		loadData(filename);
	}
	
	public TrapTools(String filename) throws IOException {
		this.algorithm = SearchAlgorithm.HALF;
		loadData(filename);	
	}

	public boolean search(String s) {
		switch (algorithm) {
		case LINEAR:
			return linearSearch(s);
		case HALF:
			return halfSearch(s);
		case TREE:
			return tree.treeSearch(s);
		}
		return false;
	}

	/**
	 * Load the data from a yaml file
	 * 
	 * @param filename
	 * @throws IOException
	 */
	private void loadData(String filename) throws IOException {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		traps = mapper.readValue(new File(filename), Traps.class);
		if (algorithm == SearchAlgorithm.HALF || algorithm == SearchAlgorithm.TREE) {
			traps.trimAndSort();
		}
		if (algorithm == SearchAlgorithm.TREE) {
			buildTree();
		}
	}

	/**
	 * Build a tree from the traps which have been loaded from yaml
	 */
	private void buildTree() {

		tree = new Tree(0,0);

		Iterator<String> iter = traps.getTrapTypeOidPrefix().iterator();
		while (iter.hasNext()) {
			String trap = iter.next();
			String[] s = trap.split(Pattern.quote("."));
			Tree current = tree;
			int i; // we use i again below
			for (i = 1; i < s.length; i++) { // skip first
				int val = Integer.parseInt(s[i]);
				if (!current.containsChildValue(val)) {
					break; // we're going to build a new sub tree
				}
				current = current.getChild(val);
			}
			// build new sub tree
			for (; i < s.length; i++) {
				int val = Integer.parseInt(s[i]);
				current = current.addChild(val,i);
			}
		}		
	}
	
	/**
	 * Run a linear search on unsorted data
	 * 
	 * @param oid to search for
	 * @return true if found
	 */
	public boolean linearSearch(String oid) {

		if (traps.getTrapTypeOidPrefix().size() == 0) {
			System.out.println("Trap prefix list is empty");
			return false;
		}
		
		if (!checkOidIsValid(oid)) {
			return false;
		}		

		Iterator<String> iter = traps.getTrapTypeOidPrefix().iterator();
		oid = oid.trim();
		while (iter.hasNext()) {
			String trap = iter.next();
			if (oid.startsWith(trap)) {
				return true;
			} /*else if (oid.length() < trap.length() && trap.startsWith(oid)) {
				return true;
			}*/
		}
		return false;
	}
	
	private boolean checkOidIsValid(String oid) {
		if (oid.length() <= 1) {
			return false;
		}
		
		if (oid.charAt(0) != '.') {
			return false;
		}
		
		if (!oid.matches("^[0-9.]+$")) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Run a half interval search on sorted data
	 * 
	 * @param oid to search for
	 * @return true if found
	 */
	public boolean halfSearch(String oid) {
		if (traps.getTrapTypeOidPrefix().size() == 0) {
			System.out.println("Trap prefix list is empty");
			return false;
		}
		
		if (!checkOidIsValid(oid)) {
			return false;
		}

		int left = 0;
		int right = traps.getTrapTypeOidPrefix().size() - 1;
		while (left <= right) {
			// set current to middle of left and right
			int current = (int) Math.floor((left + right) / 2);
			String trap = traps.getTrapTypeOidPrefix().get(current).trim();
			if (oid.startsWith(trap)) {
				return true;
			} /* Changed criteria
				else if (oid.length() < trap.length() && trap.startsWith(oid)) {
				return true;
			} */
			// move our position in the list
			if (trap.compareTo(oid) < 0) {
				left = current + 1;
			} else if (trap.compareTo(oid) > 0) {
				right = current - 1;
			}
		}
		return false;
	}

}
