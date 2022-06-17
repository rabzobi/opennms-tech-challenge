package za.co.garland.opennms;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import za.co.garland.opennms.TrapTools.SearchAlgorithm;

/**
 * Unit tests for data searches
 *  - basic tests use very simple data
 *  - sample uses examples provided
 *  
 *  Performance testing done with bash script
 * 
 * @author robert 
 */
public class HalfSearchTests {

	@Test
	public void test1_sample2() throws IOException {
		TrapTools data = new TrapTools("snmp2.yaml",SearchAlgorithm.HALF);
		assertTrue(data.search(".1.3.6.1.4.1.9.9.117.2.0.1"));
	}
	
	@Test
	public void test2_sample2() throws IOException {
		TrapTools data = new TrapTools("snmp2.yaml",SearchAlgorithm.HALF);
		assertFalse(data.search(".1.3.6.1.4.1.9.9.117"));
	}
	
	@Test
	public void test3_sample2() throws IOException {
		TrapTools data = new TrapTools("snmp2.yaml",SearchAlgorithm.HALF);
		assertFalse(data.search(".1.3.6.1.4.1.9.9.118.2.0.1"));
	}
	
	@Test
	public void test4_sample2() throws IOException {
		TrapTools data = new TrapTools("snmp2.yaml",SearchAlgorithm.HALF);
		assertTrue(data.search(".1.3.6.1.6.3.1.1.5"));
	}
	
	@Test
	public void test5_sample2() throws IOException {
		TrapTools data = new TrapTools("snmp2.yaml",SearchAlgorithm.HALF);
		assertTrue(data.search(".1.3.6.1.6.3.1.1.5.1.1.1.1.1"));
	}
	
	@Test
	public void test6_sample2() throws IOException {
		TrapTools data = new TrapTools("snmp2.yaml",SearchAlgorithm.HALF);
		assertFalse(data.search(".1.3.6.1.6.3.1.1.5.1.1.1.1.1.sflkdsjlfkj"));
	}
	
	@Test
	public void test7_sample2() throws IOException {
		TrapTools data = new TrapTools("snmp2.yaml",SearchAlgorithm.HALF);
		assertFalse(data.search(".1.3.6.1.6.3.1.1"));
	}
	
	@Test
	public void test8_sample2() throws IOException {
		TrapTools data = new TrapTools("snmp2.yaml",SearchAlgorithm.HALF);
		assertFalse(data.search(".1.3.6.1.6.3.1.1.flskdjflksdjflksd"));
	}
	
	@Test
	public void test9_sample2() throws IOException {
		TrapTools data = new TrapTools("snmp2.yaml",SearchAlgorithm.HALF);
		assertFalse(data.search("sflkdsjlfkj"));
	}
	
	@Test
	public void test10_sample2() throws IOException {
		TrapTools data = new TrapTools("snmp2.yaml",SearchAlgorithm.HALF);
		assertFalse(data.search("."));
	}

}
