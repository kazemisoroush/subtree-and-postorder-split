package objects;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Soroush
 * this object is out binary trie. binary trie contains a single root node and nothing more.
 */
public class BinaryTrie {
	Node root;

	/**
	 * @author Soroush
	 * constructor of binary trie object. this function builds a root node and sets it's ip and mask to "00...0".
	 */
	public BinaryTrie() {
		String ip = "", mask = "";
		for (int i = 0; i < 32; i++) {
			ip += "0";
			mask += "0";
		}
		this.root = new Node(ip, mask);
	}

	/**
	 * @author Soroush
	 * @param root
	 * setter for root node.
	 */
	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * @author Soroush
	 * @return root node of trie
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * @author Soroush
	 * @return true if the trie is empty.
	 */
	public boolean isEmpty() {
		return !this.getRoot().isNexthop() && !this.getRoot().hasLeft() && !this.getRoot().hasRight();
	}

	/**
	 * @author Soroush
	 * @param node
	 * @return true if the input node is added to trie and false if the node is already exists in trie or can't be added.
	 * this add function uses a VLR (visit-left-right) algorithm for searchin in trie.
	 */
	public boolean add(Node node) {
		Node parent = this.getRoot(); // a cursor node for visiting nodes

		for (int i = 0; i < node.ip.length(); i++) { // starts from left character of mask and checks if mask is '1'
			if (node.mask.charAt(i) == '1') { // if mask is still '1'
				String mask = parent.mask.substring(0, i) + "1" + parent.mask.substring(i + 1);
				if (node.ip.charAt(i) == '1') { // right
					if (!parent.hasRight()) { // ip has '1' char, so we must move to right from current node
						String ip = parent.ip.substring(0, i) + "1" + parent.ip.substring(i + 1);
						parent.setRight(new Node(ip, mask)); // makes a null node and adds it to our cursor node
					}
					parent = parent.getRight();
				} else { // left (comments same as right)
					if (!parent.hasLeft()) {
						String ip = parent.ip.substring(0, i) + "0" + parent.ip.substring(i + 1);
						parent.setLeft(new Node(ip, mask));
					}
					parent = parent.getLeft();
				}
			} else { // the mask is '0' from now on. the node will be inserted in current node
				parent.setNode(node);
				return true;
			}
		}
		return false; // couldn't add node
	}

	/**
	 * @author Soroush
	 * @param node
	 * @return true if the node exists in trie, otherwise false.
	 * this function is not used in this project. so we will not comment in it.
	 */
	public boolean search(Node node) {
		Node parent = this.getRoot();

		for (int i = 0; i < node.ip.length(); i++) {
			if (parent.equal(node)) {
				return true;
			}
			if (node.ip.charAt(i) == '1') { // right
				if (parent.hasRight()) {
					parent = parent.getRight();
				} else {
					return false;
				}
			} else { // left
				if (parent.hasLeft()) {
					parent = parent.getLeft();
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * @author Soroush
	 * @param node
	 * @return true if input node is deleted from trie successfully. otherwise returns false.
	 * just unsets the isNexthop of node in trie if it finds the node. then it calls clear function.
	 * uses VLR (visit-left-right) algorithm.
	 */
	public boolean remove(Node node) {
		if (!this.search(node)) { // node must be in trie, otherwise removing is unsuccessful.
			return false;
		}

		Node parent = this.getRoot(); // cursor node for vlr

		for (int i = 0; i < node.ip.length(); i++) { // same as adding algorithm
			if (parent.equal(node)) {
				parent.clear(); // algorithm finds the node. it sets isNexthop of node to false by this function.
				break; // removing is done.
			}
			if (node.ip.charAt(i) == '1') { // right
				if (parent.hasRight()) {
					parent = parent.getRight(); // cursor moves to right
				}
			} else { // left
				if (parent.hasLeft()) {
					parent = parent.getLeft(); // cursor moves to left
				}
			}
		}
		this.clear(this.getRoot(), null); // clears trie
		return true;
	}

	/**
	 * @author Soroush
	 * @param node
	 * @param parent
	 * clears null leafs from trie. a null leaf is a leaf that does not contains a prefix.
	 * it calls after deletion. this function uses a LRV algorithm. this is a recursive algorithm.
	 */
	private void clear(Node node, Node parent) {
		if (node.hasRight()) {
			this.clear(node.getRight(), node); // clears right node of cursor.
		}

		if (node.hasLeft()) {
			this.clear(node.getLeft(), node); // clears left node of cursor.
		}

		if (!node.hasRight() && !node.hasLeft() && !node.isNexthop()) { // node has no right and left child plus it is not prefix node
			if (parent == null) { // root node
				this.getRoot().setNexthop(false);
				return;
			}

			if (parent.getRight() == node) {
				parent.setRight(null);
			} else if (parent.getLeft() == node) {
				parent.setLeft(null);
			}
		}
	}
	
	/**
	 * @author Soroush
	 * @param node
	 * @return prefix string of ancestor of input node in this trie. this function used in finding covering prefixes in our algorithms.
	 * this function uses VLR algorithm.
	 */
	public String getAncestor(Node node) {
		if (!this.search(node)) {
			return null;
		}
		
		Node parent = this.getRoot(); // a cursor node
		String ancestor = null; // returning string, this must be ancestor of input node at the end of function.
		
		for (int i = 0; i < node.getMask().length(); i++) { // same as adding function
			if (parent.isNexthop()) { // if the cursor node is contains prefix, we copy it into our returning value. 
				ancestor = parent.toString();
			}
			if (node.getMask().charAt(i) == '1') {
				if (node.getIp().charAt(i) == '1') { // cursor moves to right.
					if (parent.hasRight()) {
						parent = parent.getRight();
					} else { // no more right childs for cursor, no update found for aur ancestor string
						return null;
					}
				} else { // cursor moves to left.
					if (parent.hasLeft()) {
						parent = parent.getLeft();
					} else { // no more left childs for cursor, no update found for aur ancestor string
						return null;
					}
				}
			} else { // our mask is done. we must return the returning value
				return ancestor;
			}
		}
		return ancestor; // not needed code.
	}
	
	/**
	 * @author Soroush
	 * @param node
	 * @return gets parent node of input node in this trie. we do not store the address of parent node in the node.
	 * we must search the trie to find it's parent node. this function is not used in our project.
	 */
	public Node getParent(Node node) {
		Node parent = this.root;
		
		for (int i = 0; i < node.ip.length(); i++) {
			if (node.mask.charAt(i) == '1') {
				if (node.ip.charAt(i) == '1') {
					if (parent.getRight().equal(node)) {
						return parent;
					}
					parent = parent.getRight();
				} else {
					if (parent.getLeft().equal(node)) {
						return parent;
					}
					parent = parent.getLeft();
				}
			} else {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * @author Soroush
	 * for testing purposes.
	 */
	@Override
	public String toString() {
		String ret = "";
		Queue<Node> fifo = new LinkedList<Node>();

		fifo.add(this.getRoot());

		while (!fifo.isEmpty()) {
			Node node = fifo.remove();
			ret += node.toString() + " ";
			if (node.hasLeft()) {
				fifo.add(node.getLeft());
			}
			if (node.hasRight()) {
				fifo.add(node.getRight());
			}
		}

		return ret;
	}
	
//	public static void main(String[] args) {
//		Node n1 = new Node("10111", "10000"); // 1*
//		Node n2 = new Node("01011", "11000"); // 01*
//		Node n3 = new Node("00011", "11100"); // 000*
//		Node n4 = new Node("11001", "11000"); // 11*
//		Node n5 = new Node("00111", "11110"); // 0011*
//
//		BinaryTrie b = new BinaryTrie();
//		b.add(n1);
//		b.add(n2);
//		b.add(n3);
//		b.add(n4);
//		b.add(n3);
//		b.add(n5);
//		System.out.println(b);
//		b.remove(n1);
//		b.remove(n2);
//		b.remove(n3);
//		b.remove(n4);
//		b.remove(n5);
//		System.out.println(b);
//	}
}
