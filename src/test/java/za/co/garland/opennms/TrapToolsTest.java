package za.co.garland.opennms;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

/**
 * Unit tests for trap tools
 */
public class TrapToolsTest {
	
	TrapTools snmp_yaml;
	
	public TrapToolsTest() throws IOException {
		snmp_yaml = new TrapTools("snmp.yaml");
	}

    @Test
    public void testSnmpDotYaml1() throws IOException {    	
        assertTrue( snmp_yaml.binSearch(".1.3.6.1.4.1.9.9.117.2.0.1") );    	
    }
    
    @Test
    public void testSnmpDotYaml2() throws IOException {
    	assertTrue( snmp_yaml.binSearch(".1.3.6.1.4.1.9.9.117") );
    }
    
    @Test
    public void testSnmpDotYaml3() throws IOException {
    	assertFalse( snmp_yaml.binSearch(".1.3.6.1.4.1.9.9.118.2.0.1") );
    }
   
}
