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

	/*
	 * Tree search tests on some simple data
	 */
	public void test1_basic() throws IOException {
		TrapTools data = new TrapTools("junit.yaml",SearchAlgorithm.HALF);
		assertTrue(data.search(".1.5"));
	}
	
	@Test
	public void test2_basic() throws IOException {
		TrapTools data = new TrapTools("junit.yaml",SearchAlgorithm.HALF);
		assertTrue(data.search(".11.2"));
	}	
	
	@Test
	public void test3_basic() throws IOException {
		TrapTools data = new TrapTools("junit.yaml",SearchAlgorithm.HALF);
		assertFalse(data.search(".11.3"));
	}		

	/* Tree search tests on sample data */
	@Test
	public void test1_sample() throws IOException {
		TrapTools data = new TrapTools("snmp.yaml",SearchAlgorithm.HALF);
		assertTrue(data.search(".1.3.6.1.4.1.9.9.117.2.0.1"));
	}

	@Test
	public void test2_sample() throws IOException {
		TrapTools data = new TrapTools("snmp.yaml",SearchAlgorithm.HALF);
		assertTrue(data.search(".1.3.6.1.4.1.9.9.117"));
	}

	@Test
	public void test3_sample() throws IOException {
		TrapTools data = new TrapTools("snmp.yaml",SearchAlgorithm.HALF);
		assertFalse(data.search(".1.3.6.1.4.1.9.9.118.2.0.1"));
	}
}
