package za.co.garland.opennms;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Class to store the trap data read from a yaml file
 * 
 * @author Rob Ellis
 *
 */
public class Traps {
	
	@JsonProperty("trap-type-oid-prefix")
	private List<String> trapTypeOidPrefix;	
	
	public void setTrapTypeOidPrefix(List<String> trapTypeOidPrefix) {
		this.trapTypeOidPrefix = trapTypeOidPrefix;
	}
	
	public List<String> getTrapTypeOidPrefix() {
		return trapTypeOidPrefix;
	}
	
	@Override
	public String toString() {
		return "List size="+ trapTypeOidPrefix.size();
	}
	
	/**
	 * Sort list of traps prefixes;
	 * Also trim whitespace which might have landed up on the list of traps.
	 */
	public void trimAndSort() {
		trapTypeOidPrefix = trapTypeOidPrefix.stream().map(String :: trim).collect(Collectors.toList());
		// We need Java 8 for the trim expression
		Collections.sort(trapTypeOidPrefix);
	}	
}
