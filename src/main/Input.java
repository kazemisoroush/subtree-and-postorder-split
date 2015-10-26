package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import objects.BinaryTrie;
import objects.BucketList;
import objects.Node;

public class Input {

	BinaryTrie tree;

	/**
     * Create and returns a binary trie from prefixes in "input.txt" file.
     *
	 * @author Soroush
	 * @return
	 */
	public BinaryTrie treeFromFile() {
		BinaryTrie tree = new BinaryTrie();
		String prefix = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
		    for (String line; (line = br.readLine()) != null; ) {
		    	if (line.contains("b")) {
		    		continue;
		    	}
		    	prefix = line;
		    	String mask = "";
				String ip = prefix.substring(0, prefix.length() - 1);
				
				for (int i = 0; i < ip.length(); i++) {
					mask += "1";
				}
				for (int i = ip.length(); i < 32; i++) {
					mask += "0";
					ip += "0";
				}
				tree.add(new Node(ip, mask, true));
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tree;
	}
	
	/**
     * Create bucket-list, Reads prefixes per bucket from file.
     *
	 * @author Soroush
	 * @return
	 */
	public BucketList getBucketsFromFile() {
		BucketList bucks = new BucketList();
		int b = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
		    for(String line; (line = br.readLine()) != null;) {
		    	if (line.contains("b")) {
		    		b = Integer.parseInt(line.substring(line.indexOf("=") + 1));
		    	}
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		bucks.setPrefixes(b);

		return bucks;
	}
	
}
