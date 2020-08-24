//$Id$
/**
 * 
 */
package com.project.lpm;


/**
 * @author Andy
 * 
 *
 */
public class MultibitTrie {
	
	private static final int STRIDE = 3; //initial stride size
	private Node root;

	private Node LMP;	//Longest Matching Prefix

	private int numOfNodesVisited = 0; 
	private int numOfNodeCreated = 0; 


	private static class Node {
		private String nextHop = null;
		private Node[] pointer = new Node[(int)Math.pow(2, STRIDE)];

	}


	public void insert(String prefix, String nextHop) {

		if (prefix.length() % STRIDE == 0)
			root = insert(root, prefix, nextHop, 0);
		else { 
			int expansion = STRIDE - (prefix.length()%STRIDE);
			for (int i=0; i<(int)Math.pow(2,expansion); i++) 
				root = insert(root, prefix + paddZeros(LPMUtil.binaryToDecimal(i),expansion), nextHop, 0);
		}
	}

	private Node insert(Node node, String prefix, String nextHop, int b) {

		if (node == null) {
			node = new Node();
			numOfNodeCreated ++;
		}

		if(b == prefix.length()) {
			//

			node.nextHop = nextHop;
			return node;
		}
		node.pointer[LPMUtil.decimalToBinary(prefix.substring(b, b+STRIDE))] = insert(node.pointer[LPMUtil.decimalToBinary(prefix.substring(b, b+STRIDE))], 
				prefix, nextHop, b+STRIDE) ;


		return node;
	}

	public String longestPrefixOf(String destinationAddress) {
		numOfNodesVisited = 0;
		LMP = searchPrefix (root, destinationAddress, 0);
		if (LMP == null) {
			return null;
		}
		return  LMP.nextHop;
	}
	

	
	private Node searchPrefix(Node x, String destinationAddress, int b) {

		if(x == null) 
			return LMP;

		if (x.nextHop != null) 
			LMP = x;

		if( (b + STRIDE) > destinationAddress.length() ) 
			return LMP;
		numOfNodesVisited ++;
		return  searchPrefix(x.pointer[LPMUtil.decimalToBinary(destinationAddress.substring(b, b+STRIDE))], destinationAddress, b+STRIDE);	

	}

	private String paddZeros(String str, int expansion) {
		if(str.length()==expansion) 
			return str;

		else {
			String zeros = "";
			for (int i=0; i<expansion-str.length(); i++)
				zeros += '0';
			return zeros.concat(str);
		}

	}

	public int getNumOfNodesVisited () {
		return numOfNodesVisited;
	}

	public int getNumOfNodeCreated () {
		return numOfNodeCreated;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultibitTrie multibitTrie = new MultibitTrie();
		multibitTrie.insert("", "0.0.0.0");
		multibitTrie.insert("100011100001010110001","1.1.1.1");
		multibitTrie.insert("1001100001111101101110","2.2.2.2");
		System.out.println("============ Test Results =============");
		System.out.println("10001110000101011000100111000 ==>>>> "+ multibitTrie.longestPrefixOf("10001110000101011000100111000"));
		System.out.println("10011000011111011011101001011011 ==>> "+multibitTrie.longestPrefixOf("10011000011111011011101001011011"));
		System.out.println("00011000011111011011101001011011 ==>> "+multibitTrie.longestPrefixOf("00011000011111011011101001011011"));
	}

}
