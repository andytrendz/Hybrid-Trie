//$Id$
/**
 * 
 */
package com.project.lpm;

/**
 * @author Andy
 *
 */
public class BinaryTrie {

	private Node root;

	private Node LPM;	//Longest Prefix Match

	private int numOfNodesVisited = 0; 
	private int numOfNodeCreated = 0;  

	private static class Node {
		private String nextHop = null;	//If it is a prefix node
		private Node left = null ;		//Left node location if address bit is 0
		private Node right = null ;		//Right node location if address bit 1 
	}


	public void insert(String prefix, String nextHop) {
		root = insert(root, prefix, nextHop, 0);
	}

	private Node insert(Node x, String prefix, String nextHop, int b) {
		if (x == null) {
			x = new Node();
			numOfNodeCreated ++; 
		}
		if(b == prefix.length()) {
			x.nextHop = nextHop;
			return x;
		}
		if (prefix.charAt(b) == '0') 
			x.left = insert(x.left, prefix, nextHop, b+1);
		else 	
			x.right = insert(x.right, prefix, nextHop, b+1);
		return x;
	}

	public String longestPrefixOf(String destinationAddress) {
		numOfNodesVisited = 0;
		LPM = search (root, destinationAddress, 0);
		if (LPM == null) 
			return null;
		return  LPM.nextHop;
	}

	private Node search(Node x, String destinationAddress, int b) {
		if(x == null) 
			return LPM;
		if (x.nextHop != null) 
			LPM = x;
		if(b == destinationAddress.length()) 
			return LPM;
		if (destinationAddress.charAt(b) == '0') {
			numOfNodesVisited ++;
			return  search(x.left, destinationAddress, b+1);
		}
		else {
			numOfNodesVisited ++;
			return search(x.right, destinationAddress, b+1);
		}	
	}

	public int getNumOfNodesVisitedCount() {
		return numOfNodesVisited;
	}

	public int getNumOfNodesCreatedCount() {
		return numOfNodeCreated;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BinaryTrie binaryTrie = new BinaryTrie();
		binaryTrie.insert("1011","77.94.128.1");
	}

}
