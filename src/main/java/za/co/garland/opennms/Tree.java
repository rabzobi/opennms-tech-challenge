package za.co.garland.opennms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tree {
    private final List<Tree> children;
    private int value;

    public Tree(int data) {
        this.children = new ArrayList<>();
        this.value = data;
    }

    public Tree(int data, Tree parent) {
        // new node with a given parent
        this.children = new ArrayList<>();
        this.value = data;
        //this.depth = (parent.getDepth() + 1);
        parent.addChild(this);
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

    public Tree addChild(int data) {
        Tree child = new Tree(data);
        this.children.add(child);
        return child;
    }

    public void addChild(Tree child) {
        this.children.add(child);
    }

    public int getData() {
        return this.value;
    }

    public boolean isLeafNode() {
        return (this.children.size() == 0);
    }

}
