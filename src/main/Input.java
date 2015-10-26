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
	 * @author Soroush
	 * creates and returns a binary trie from prefixes in "input.txt" file
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
	 * @author Soroush
	 * creates bucket-list. reads prefixes per bucket from file
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
	
//	public BinaryTrie treeFromConsole() {
//		System.out.println("please enter prefixes, type in 'done' when done:");
//
//		BinaryTrie tree = new BinaryTrie();
//		Scanner input = new Scanner(System.in);
//		String prefix = "";
//		
//		boolean done = false;
//
//		while (!done) {
//			prefix = input.next();
//			String mask = "";
//			String ip = prefix.substring(0, prefix.length() - 1);
//			
//			if (prefix.toLowerCase().equals("done")) {
//				done = true;
//				continue;
//			}
//			
//			for (int i = 0; i < ip.length(); i++) {
//				mask += "1";
//			}
//			for (int i = ip.length(); i < 32; i++) {
//				mask += "0";
//				ip += "0";
//			}
//			tree.add(new Node(ip, mask));
//		}
//		return tree;
//	}
//	
//	public BucketList getBucketsFromConsole() {
//		System.out.println("please enter number of buckets:");
//		
//		BucketList bucks = new BucketList();
//		Scanner input = new Scanner(System.in);
//		int buckNo = Integer.parseInt(input.next());
//		
//		System.out.println("please enter number of prefixes in one bucket:");
//		int prefixes = Integer.parseInt(input.next());
//		bucks.setPrefixes(prefixes);
//		
//		for (int i = 0; i < buckNo; i++) {
//			ArrayList<Node> nodes = new ArrayList<Node>();
//			Bucket buck = new Bucket(prefixes);
//			buck.setNodes(nodes);
//			bucks.add(buck);
//		}
//		
//		return bucks;
//	}
	
}
