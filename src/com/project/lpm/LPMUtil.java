//$Id$
/**
 * 
 */
package com.project.lpm;

/**
 * @author Andy
 *
 */
public class LPMUtil {
	
	public static int decimalToBinary( String decimal ){

		int i = 0;
		int binary = 0;
		while( i < decimal.length()) {
			binary *= 2;
			binary += decimal.charAt(i++) - '0'; //minus the ASCII code of '0' to get the value of the charAt(i++)
		}
		return binary;
	}
	
	public static String binaryToDecimal(int binary) {

	    int c;
	    char m;
	    StringBuilder decimal = new StringBuilder();
	    // convert the String to int
	    while (binary > 0) {
	        c = binary % 2;
	        binary = binary / 2;
	        m = (char) ('0' + c);
	        decimal.append(m);
	    }
	    return decimal.reverse().toString();
	}
}
