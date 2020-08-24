//$Id$
/**
 * 
 */
package com.project.lpm;

/**
 * @author Andy
 *
 */
public class PrefixTableAttributes {
	
	private String prefix;
	private String nextHop;
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getNextHop() {
		return nextHop;
	}
	public void setNextHop(String nextHop) {
		this.nextHop = nextHop;
	}
	
	public PrefixTableAttributes(String prefix, String nextHop) {
		this.prefix = prefix;
		this.nextHop = nextHop;
	}
	
}
