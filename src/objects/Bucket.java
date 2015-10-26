package objects;

import java.util.ArrayList;

/**
 * This is the bucket object. each bucket has an array-list of nodes, indexes
 * and coverings in it. Capacity is number of prefixes that can be added to
 * this bucket. Each index, covering and node in this object is a string
 * containing '1', '0' and '*' and the end of it.
 *
 * @author Soroush
 */
public class Bucket {
	ArrayList<String> nodes;
	ArrayList<String> indexes;
	ArrayList<String> covering;
	int capacity = 0;

	/**
     * Cunstroctor of object.
     *
	 * @author Soroush
	 * @param capacity
	 */
	public Bucket(int capacity) {
		this.capacity = capacity;
		this.nodes = new ArrayList<String>();
		this.indexes = new ArrayList<String>();
		this.covering = new ArrayList<String>();
	}

	/**
     * Check if bucket has a specific index. Returns true if the bucket has input index.
     *
	 * @author Soroush
	 * @param index
	 * @return
	 */
	public boolean hasIndex(String index) {
		return this.indexes.contains(index);
	}
	
	/**
     * Add a specific index to bucket. Get and input index and adds it to
     * indexes array. It removes an index from index list if the input
     * index string is parent of it in tree.
     *
	 * @author Soroush
	 * @param index
	 *
	 */
	public void addIndex(String index) {
		if (!hasIndex(index)) {
			ArrayList<String> newArray = new ArrayList<String>();
			newArray.add(index);
			
			for (String in : indexes) {
				String parent = index.substring(0, index.indexOf("*"));
				String child = in.substring(0, in.indexOf("*"));
				if ((child).indexOf(parent) != 0 && parent.length() != 0) {
					newArray.add(in);
				}
			}
			this.indexes.add(index);
			this.indexes = newArray;
		}
	}
	
	/**
     * Check if bucket has a covering set. Returns true if bucket has input covering node.
	 * @author Soroush
	 * @param covering
	 * @return
	 */
	public boolean hasCovering(String covering) {
		return this.covering.contains(covering);
	}
	
	/**
     * Add a covering prefix to bucket's covering list.
     *
	 * @author Soroush
	 * @param covering
	 */
	public void addCovering(String covering) {
		if (!hasCovering(covering)) {
			this.covering.add(covering);
		}
	}
	
	/**
     * Get number of possible coverings that can be added until the bucket become full.
     *
	 * @author Soroush
	 * @return
	 */
	public int getFreeCovering() {
		return this.capacity - this.covering.size();
	}
	
	/**
     * Get string of prefix from input index of prefix.
	 * @author Soroush
	 * @param index
	 * @return
	 */
	public String getNode(int index) {
		return nodes.get(index) != null ? nodes.get(index) : null;
	}

	/**
     * Add input node into node list of bucket if the node doesn't exists in node list.
     *
	 * @author Soroush
	 * @param node
	 */
	public void addNode(Node node) {
		if (!this.isFull()) {
			nodes.add(node.toString());
		}
	}

	/**
     * Return true if the bucket is full. Size of node list is equal to bucket capacity.
	 * @author Soroush
	 * @return
	 */
	public boolean isFull() {
		return nodes.size() >= capacity;
	}

	/**
     * Setter for nodes array in bucket.
     *
	 * @author Soroush
	 * @param nodes
	 */
	public void setNodes(ArrayList<String> nodes) {
		this.nodes = nodes;
	}
	
	/**
     * Get number of possible nodes that can be added to bucket from now on.
     *
	 * @author Soroush
	 * @return
	 */
	public int getFree() {
		return this.capacity - this.nodes.size();
	}

	/**
     * Bucket to string function.
     *
	 * @author Soroush
	 */
	@Override
	public String toString() {
		return "\n prefixes: " + nodes.toString() + "- indexes:" + indexes.toString() + "- covering:" + covering + "\n";
	}

}
