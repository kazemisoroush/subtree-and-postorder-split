package algorithm;

import objects.BinaryTrie;
import objects.Bucket;
import objects.BucketList;
import objects.Node;

/**
 * @author Soroush
 * This object applys sub-tree split algorithm to a binary tie and builds bucket-list, containing buckets of prefixes.
 * the trie will be empty at the end of the algorithm. this object has integer 'prefixes' that is number of prefixes per bucket.
 * as we mentioned bucket-list named 'bucks' will be list of buckets full of prefixes.
 */
public class SubtreeSplit {
	int prefixes;
	BucketList bucks;
	BinaryTrie tree;
	
	/**
     * This function is the cunstructor of object gets trie and bucket-list and fills them using algorithms.
	 * @author Soroush
	 * @param tree
	 * @param bucks
	 */
	public SubtreeSplit(BinaryTrie tree, BucketList bucks) {
		this.bucks = bucks;
		this.tree = tree;
		this.prefixes = bucks.getPrefixes();
		int b = 0; // index of bucket
		
		while (!this.tree.isEmpty()) { // algorith must empty the trie, so we continue until the trie is empty
			bucks.add(new Bucket(this.prefixes)); // in each iteration we make another new bucket and add it to our bucket-list
			weight(this.tree.getRoot()); // corrects weight of trie with weight function.
			Node node = subtree(this.tree.getRoot(), this.prefixes); // finds a sub-tree that contains enough prefixes to fill bucket not more than bucket's capacity
			if (node == null) { // no prefix in [b/2, b]
				node = this.tree.getRoot(); // if such node doesn't found we choose root node.
			}
			bucks.get(b).addIndex(node.toString()); // the parent of sub-tree must be added to indexes of bucket
			
			String ancestor = this.tree.getAncestor(node); // we find sub-tree parent node's ancestor prefix
			if (ancestor != null) {
				bucks.get(b).addCovering(ancestor.toString()); // we add the ancestor to covering array of current bucket
			}
			
			split(node, b); // we split the sub-tree and add it to current bucket
			b++; // move to next bucket
		}
	}

	/**
     * Remove sub-tree with parent node "n" from it's trie and adds it's prefixes to bucket with index "b".
     * This function splits sub-tree, using recursive LRV algorithm.
     *
	 * @author Soroush
	 * @param n
	 * @param b
	 */
	private void split(Node n, int b) {
		if (n.hasLeft()) {
			split(n.getLeft(), b); // splits left child of cursor
		}
		if (n.hasRight()) {
			split(n.getRight(), b); // splits right child of cursor
		}
		if (n.getWeight() <= prefixes) {
			if (bucks.get(b).isFull()) {
				return; // algorithm finishes if the bucket is full
			}
			if (n.isNexthop()) {
				bucks.get(b).addNode(n); // adds cursor to bucket if it contains prefix
			}
			tree.remove(n); // removes cursor from trie
		}
	}

	/**
     * This function gets a node in a trie and weights the sub-tree.
     *
	 * @author Soroush
	 * @param node
	 */
	private void weight(Node node) {
		int left = 0, right = 0;
		if (node.hasLeft()) { // recursively gets left node's weight
			weight(node.getLeft());
			left = node.getLeft().getWeight();
		}
		if (node.hasRight()) { // recursively gets right node's weight
			weight(node.getRight());
			right = node.getRight().getWeight();
		}
		if (!node.isLeaf()) { // non-leaf nodes.
			if (node.isNexthop()) {
				node.setWeight(1 + left + right); // prefix node is weight 1 + left child's weight + right child's weight.
			} else {
				node.setWeight(left + right); // null node is weight left child's weight + right child's weight.
			}
		} else {
			node.setWeight(1); // leafs are weighted 1.
		}
	}
	
	/**
     * Parent of a sub-tree for sub-tree split function. The parent's weight must in [b/2 , b].
     * This function finds sub-tree recursively.
     *
	 * @author Soroush
	 * @param node
	 * @param b
	 * @return
	 */
	private Node subtree(Node node, int b) {
		if (node.getWeight() <= b && node.getWeight() >= b / 2) {
			return node;
		}
		if (node.hasLeft()) { // search left child of cursor node
			Node n = subtree(node.getLeft(), b);
			if (n != null) return n; // if you didn't found sub-tree in left, do nothing. otherwise return the value.
		}
		if (node.hasRight()) { // search right child of cursor node
			Node n = subtree(node.getRight(), b);
			if (n != null) return n; // if you didn't found sub-tree in right, do nothing. otherwise return the value.
		}
		return null; // no sub-tree found in [b/2 , b]
	}
	
}
