package objects;

/**
 * @author Soroush
 * this is the node object that exists in our binary trie.
 * each node has a left child, right child, ip string, mask string, weight integer and if the prefix is full, a true boolean of isNexthop.
 */
public class Node {
	Node left = null;
	Node right = null;
	String ip = "";
	String mask = "";
	int weight = 0;
	boolean isNexthop = false;

	/**
	 * @author Soroush
	 * @param ip
	 * @param mask
	 * @param isNexthop
	 * constructor of object that sets ip and mask strings of node.
	 * if this constructor calls, this node will contain prefix as well.
	 */
	public Node(String ip, String mask, boolean isNexthop) {
		this.ip = ip;
		this.mask = mask;
		this.isNexthop = isNexthop;
	}
	
	/**
	 * @author Soroush
	 * @param ip
	 * @param mask
	 * second constructor for this object. node will be an empty node.
	 */
	public Node(String ip, String mask) {
		this.ip = ip;
		this.mask = mask;
	}
	
	/**
	 * @author Soroush
	 * @return getter for ip.
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * @author Soroush
	 * @return getter for mask.
	 */
	public String getMask() {
		return mask;
	}

	/**
	 * @author Soroush
	 * @return left child node of this node.
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * @author Soroush
	 * @param left
	 * sets left child node of this node.
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

	/**
	 * @author Soroush
	 * @return right child node of this node.
	 */
	public Node getRight() {
		return right;
	}

	/**
	 * @author Soroush
	 * @param right
	 * sets right child node of this node.
	 */
	public void setRight(Node right) {
		this.right = right;
	}
	
	/**
	 * @author Soroush
	 * @return weight of this node
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @author Soroush
	 * @param weight
	 * setter for weight of node.
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * @author Soroush
	 * @return true if the node contains prefix.
	 */
	public boolean isNexthop() {
		return isNexthop;
	}
	
	/**
	 * @author Soroush
	 * @param isNexthop
	 * sets prefix containing of node to in put boolean.
	 */
	public void setNexthop(boolean isNexthop) {
		this.isNexthop = isNexthop;
	}
	
	/**
	 * @author Soroush
	 * @return true if current node is a leaf node in it's trie.
	 */
	public boolean isLeaf() {
		return this.getLeft() == null && this.getRight() == null;
	}
	
	/**
	 * @author Soroush
	 * @return true if current node has a right child node.
	 */
	public boolean hasRight() {
		return this.getRight() != null;
	}
	
	/**
	 * @author Soroush
	 * @return true if current node has a left child node.
	 */
	public boolean hasLeft() {
		return this.getLeft() != null;
	}
	
	/**
	 * @author Soroush
	 * @param node
	 * changes the node ip and mask string into input node's ip and mask strings.
	 * this node will be a prefix containing node.
	 */
	public void setNode(Node node) {
		this.ip = node.ip;
		this.mask = node.mask;
		this.isNexthop = true;
	}
	
	/**
	 * @author Soroush
	 * @param node
	 * @return true if this node and the input node are equal.
	 */
	public boolean equal(Node node) {
		return this.ip.equals(node.ip) && this.mask.equals(node.mask);
	}
	
	/**
	 * @author Soroush
	 * the node will assume null node from now on.
	 */
	public void clear() {
		this.setNexthop(false);
	}
	
	/**
	 * @author Soroush
	 * (for testing purpose)
	 */
	@Override
	public String toString() {
		String ret = "";
		
		for (int i = 0; i < ip.length(); i++) {
			if (mask.charAt(i) == '1') {
				ret += ip.charAt(i);
			} else {
				ret += "*";
				break;
			}
		}
//		if (this.getWeight() > 0) {
//			ret += "(w:" + this.getWeight() + ")";
//		}
		if (this.isNexthop()) {
			ret += "(p)";
		}
		return ret;
	}
	
}
