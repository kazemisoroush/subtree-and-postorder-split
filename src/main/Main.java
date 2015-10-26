package main;

import algorithm.PostOrderSplit;
import algorithm.SubtreeSplit;
import objects.BinaryTrie;
import objects.BucketList;

public class Main {
	/**
	 * @author Soroush
	 * runs project
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTrie trie = new Input().treeFromFile();
		BucketList bucks = new Input().getBucketsFromFile();

		System.out.println(trie);

		new SubtreeSplit(trie, bucks);
//		new PostOrderSplit(trie, bucks);

		System.out.println(bucks);
	}
	
}