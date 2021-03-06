package za.co.garland.opennms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Tree {
    private final List<Tree> children;
    private int value;
	private String trap;

    public Tree(int data, int depth) {
        this.children = new ArrayList<>();
        this.value = data;
    }
  
    public boolean containsChildValue(int val) {
    	Iterator<Tree> iter = children.iterator();
		while (iter.hasNext()) {
			Tree n = iter.next();
			if (n.getData() == val) {
				return true;
			}
		}
		return false;    	
    }
    
    		
	/**
	 * Run a recursive search for valid prefixes
	 * @param array to be searched for
	 * @return true if found
	 */
	public boolean treeSearch(String s) {
		if (!TrapTools.checkOidIsValid(s)) {
			return false;
		}
		return treeSearch (("0"+s).trim().split("\\."),s, 0);
	}  
	
	private boolean treeSearch(String[] s,String ss,int depth) {
		int val = 0;
		try {
			val = Integer.parseInt(s[depth+1]);
		} catch (Exception e) {
			return false;
		}
		
		if (trap!=null && s!=null && trap.equals(ss)) {
			return true;
		}		
		Tree child = getChild(val);
		// if we can't find a child and we're a leaf node we have a valid prefix
		if (child == null && isLeafNode()) {
			return true;
		}
		if (child == null) {			
			return false;
		}
		return child.treeSearch(s,ss, depth+1);		
	}    

	public Tree getChild(int val) {
    	Iterator<Tree> iter = children.iterator();
		while (iter.hasNext()) {
			Tree n = iter.next();
			if (n.getData() == val) {
				return n;
			}
		}
		return null;
    }    

    public Tree addChild(int val,int depth) {
    	
    	Tree c = getChild(val);
    	Tree child = null;
    	if (c == null) {
    		child = new Tree(val,depth);
    		this.children.add(child);
    	}
        return child;
    }

    public int getData() {
        return this.value;
    }

    public boolean isLeafNode() {
        return (this.children.size() == 0);
    }
    
    public void printTree(int depth) {
    	Iterator<Tree> iter = children.iterator();
    	String spaces = "";
    	for (int i=0;i<depth;i++) {
    		spaces += ".";
    	}
		while (iter.hasNext()) {
			Tree n = iter.next();
			System.out.println(spaces+value);
			n.printTree(depth+1);
		}
    }

	public void setTrapString(String trap2) {
		this.trap = trap2;		
	}
   
}
