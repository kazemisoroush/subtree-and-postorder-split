package objects;

import java.util.ArrayList;

/**
 * @author Soroush
 * this is the bucket object. each bucket has an array-list of nodes, indexes and coverings in it.
 * capacity is number of prefixes that can be added to this bucket.
 * each index, covering and node in this object is a string containing '1', '0' and '*' and the end of it.
 */
public class Bucket {
	ArrayList<String> nodes;
	ArrayList<String> indexes;
	ArrayList<String> covering;
	int capacity = 0;

	/**
	 * @author Soroush
	 * the cunstroctor of object
	 * @param capacity
	 */
	public Bucket(int capacity) {
		this.capacity = capacity;
		this.nodes = new ArrayList<String>();
		this.indexes = new ArrayList<String>();
		this.covering = new ArrayList<String>();
	}

	/**
	 * @author Soroush
	 * @param index
	 * @return true if the bucket has input index.
	 */
	public boolean hasIndex(String index) {
		return this.indexes.contains(index);
	}
	
	/**
	 * @author Soroush
	 * @param index
	 * gets and input index and adds it to indexes array.
	 * it removes an index from index list if the input index string is parent of it in tree.
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
	 * @author Soroush
	 * @param covering
	 * @return true if bucket has input covering node.
	 */
	public boolean hasCovering(String covering) {
		return this.covering.contains(covering);
	}
	
	/**
	 * @author Soroush
	 * @param covering
	 * adds a covering prefix to bucket's covering list.
	 */
	public void addCovering(String covering) {
		if (!hasCovering(covering)) {
			this.covering.add(covering);
		}
	}
	
	/**
	 * @author Soroush
	 * @return number of possible coverings that can be added until the bucket become full.
	 */
	public int getFreeCovering() {
		return this.capacity - this.covering.size();
	}
	
	/**
	 * @author Soroush
	 * @param index
	 * @return string of prefix from input index of prefix.
	 */
	public String getNode(int index) {
		return nodes.get(index) != null ? nodes.get(index) : null;
	}

	/**
	 * @author Soroush
	 * @param node
	 * adds input node into node list of bucket if the node doesn't exists in node list.
	 */
	public void addNode(Node node) {
		if (!this.isFull()) {
			nodes.add(node.toString());
		}
	}

	/**
	 * @author Soroush
	 * @return true if the bucket is full. size of node list is equal to bucket capacity.
	 */
	public boolean isFull() {
		return nodes.size() >= capacity;
	}

	/**
	 * @author Soroush
	 * @param nodes
	 * setter for nodes array in bucket.
	 */
	public void setNodes(ArrayList<String> nodes) {
		this.nodes = nodes;
	}
	
	/**
	 * @author Soroush
	 * @return number of possible nodes that can be added to bucket from now on.
	 */
	public int getFree() {
		return this.capacity - this.nodes.size();
	}

	/**
	 * @author Soroush
	 * prints bucket. (for testing)
	 */
	@Override
	public String toString() {
		return "\n prefixes: " + nodes.toString() + "- indexes:" + indexes.toString() + "- covering:" + covering + "\n";
	}

}
