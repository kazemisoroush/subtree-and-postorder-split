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
     * Constructor of object that sets ip and mask strings of node.
     * If this constructor calls, this node will contain prefix as well.
     *
	 * @author Soroush
	 * @param ip
	 * @param mask
	 * @param isNexthop
	 */
	public Node(String ip, String mask, boolean isNexthop) {
		this.ip = ip;
		this.mask = mask;
		this.isNexthop = isNexthop;
	}
	
	/**
     * Constructor for this object. Node will be an empty node.
	 * @author Soroush
	 * @param ip
	 * @param mask
	 */
	public Node(String ip, String mask) {
		this.ip = ip;
		this.mask = mask;
	}
	
	/**
     * Getter for ip.
     *
	 * @author Soroush
	 * @return
	 */
	public String getIp() {
		return ip;
	}
	
	/**
     * Getter for mask.
     *
	 * @author Soroush
	 * @return
	 */
	public String getMask() {
		return mask;
	}

	/**
     * Get left child node of this node.
     *
	 * @author Soroush
	 * @return
	 */
	public Node getLeft() {
		return left;
	}

	/**
     * Set left child node of this node.
     *
	 * @author Soroush
	 * @param left
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

	/**
     * get right child node of this node.
	 * @author Soroush
	 * @return
	 */
	public Node getRight() {
		return right;
	}

	/**
     * Sets right child node of this node.
     *
	 * @author Soroush
	 * @param right
	 */
	public void setRight(Node right) {
		this.right = right;
	}
	
	/**
     * Get weight of node.
     *
	 * @author Soroush
	 * @return
	 */
	public int getWeight() {
		return weight;
	}

	/**
     * Setter for weight of node.
     *
	 * @author Soroush
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
     * Return true if the node contains prefix.
     *
	 * @author Soroush
	 * @return
	 */
	public boolean isNexthop() {
		return isNexthop;
	}
	
	/**
     * Set prefix containing of node to in put boolean.
     *
	 * @author Soroush
	 * @param isNexthop
	 */
	public void setNexthop(boolean isNexthop) {
		this.isNexthop = isNexthop;
	}
	
	/**
     * Return true if current node is a leaf node in it's trie.
     *
	 * @author Soroush
	 * @return
	 */
	public boolean isLeaf() {
		return this.getLeft() == null && this.getRight() == null;
	}
	
	/**
     * Check if current node has a right child node.
     *
	 * @author Soroush
	 * @return
	 */
	public boolean hasRight() {
		return this.getRight() != null;
	}
	
	/**
     * Check if current node has a left child.
     *
	 * @author Soroush
	 * @return
	 */
	public boolean hasLeft() {
		return this.getLeft() != null;
	}
	
	/**
     * Changes the node ip and mask string into input node's ip and mask strings.
     * this node will be a prefix containing node.
     *
	 * @author Soroush
	 * @param node
	 */
	public void setNode(Node node) {
		this.ip = node.ip;
		this.mask = node.mask;
		this.isNexthop = true;
	}
	
	/**
     * Check if this node and the input node are equal.
	 * @author Soroush
	 * @param node
	 * @return
	 */
	public boolean equal(Node node) {
		return this.ip.equals(node.ip) && this.mask.equals(node.mask);
	}
	
	/**
     * The node will assume null node from now on.
     *
	 * @author Soroush
	 */
	public void clear() {
		this.setNexthop(false);
	}
	
	/**
     * Retrun string encoded value of node.
     *
	 * @author Soroush
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
		if (this.isNexthop()) {
			ret += "(p)";
		}
		return ret;
	}
	
}
