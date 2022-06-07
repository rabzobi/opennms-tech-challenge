package za.co.garland.opennms;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests for tree data structure
 * 
 * @author robert
 */
public class TreeTest {

	Tree rootNode;
	Tree firstNode;
	Tree secondNode;
	Tree thirdNode;
	Tree fourthNode;
	Tree fifthNode;

	public TreeTest() {
		rootNode = new Tree(0);
		firstNode = new Tree(11, rootNode);
		secondNode = new Tree(22, rootNode);
		thirdNode = new Tree(21, secondNode);
		fourthNode = new Tree(31, thirdNode);
		fifthNode = new Tree(5);
	}

	@Test
	public void testThirdNode() {
		assertFalse(thirdNode.isLeafNode());
	}

	@Test
	public void testFifthNode() {
		assertTrue(fifthNode.isLeafNode());
	}
}
