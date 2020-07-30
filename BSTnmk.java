package ACSL4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.opensourcephysics.numerics.Root;

public class BSTnmk {
	public static void main(String[] args) throws FileNotFoundException {
		String Sentence = "";
		File file = new File("Data.txt"); // takes in file
		Scanner scan = new Scanner(file); // scanner to scan the file
		while (scan.hasNextLine()) { // while there is more data in the file
			BST tree = new BST();
			int numchars = 0;
			Sentence = scan.nextLine(); // scan first line of the file
			String[] CharSplit = Sentence.split(""); // split of the first line of the file into strings
			for (int k = 0; k < CharSplit.length; k++) {
				if ((CharSplit[k].charAt(0) >= 65 && CharSplit[k].charAt(0) <= 90)
						|| (CharSplit[k].charAt(0) >= 97 && CharSplit[k].charAt(0) <= 122)) {
					numchars++;
				}
			}
			char[] NodeChars = new char[numchars];
			ArrayList<Node> NodeRoots = new ArrayList<Node>();
			int NodeNum = 0;
			boolean add = true;
			int k = 0;
			for (int i = 0; i < CharSplit.length; i++) {
				if ((CharSplit[i].charAt(0) >= 65 && CharSplit[i].charAt(0) <= 90)
						|| (CharSplit[i].charAt(0) >= 97 && CharSplit[i].charAt(0) <= 122)) {
					NodeChars[k] = CharSplit[i].toUpperCase().charAt(0);
					Node newNode = new Node(NodeChars[k], 1);
					tree.insertNode(newNode);
					for (int j = 0; j < NodeRoots.size(); j++) {
						if (NodeRoots.get(j).c == newNode.c) {
							add = false;
						}
					}
					if (add == true) {
						NodeRoots.add(NodeNum, newNode);
						NodeNum++;
					}
					add = true;
					k++;
				}
			}
			int singleMingle = 0;
			// System.out.println(NodeNum);
			for (int j = 0; j < NodeRoots.size(); j++) {
				if (tree.SingleTest(NodeRoots.get(j)) == true) {
					singleMingle = singleMingle + NodeRoots.get(j).getCount();
					// System.out.println(NodeRoots.get(j).c + ": " + NodeRoots.get(j).getCount());
					// System.out.println("");
				}
			}
			System.out.println(singleMingle);

			// now that i have the letters, I need to arrange them in the bst format
			// this format should be the regular thing, but i need to be able to consolidate
			// i could create a tree first and then search, delete, add
			// or i could check when i have added and make sure it's not that number

		}
	}
}

class BST {
	Node root;
	int NodeNum;
	int Single;

	public void insertNode(Node addedNode) {
		char c = addedNode.c;
		double counter = addedNode.counter;

		// public void insertNode(char c, int counter) {
		// Node addedNode = new Node(c, counter);

		// if we don't have a root, then we make that one the root
		if (root == null) {
			root = addedNode;
			addedNode.setCount(1);
			NodeNum++;
			Single++;
		} else {
			// original node
			Node newRoot = root;
			Node parentNode;
			while (true) {
				// we want the parent node to be like the root for our node
				parentNode = newRoot;
				if (c == newRoot.c) {
					parentNode.setCount(parentNode.getCount() + 1);
					return;
				}
				// is the node on the left or the right?

				// IF LEFT, the char will be smaller
				if (c < newRoot.c) {
					NodeNum++;
					// so now the node to brand off is left
					newRoot = newRoot.left;
					// the loop stops if it does not have any branching off of it
					if (newRoot == null) {
						parentNode.left = addedNode;
						Single++;
						return;
					}
				}
				// IF RIGHT, the char will be bigger
				else {
					NodeNum++;
					// now the node is on the right
					newRoot = newRoot.right;
					// if the node has no children, then we can add this as the new node
					if (newRoot == null) {
						Single++;
						parentNode.right = addedNode;
						return;
					}
				}
			}
		}
	}

	public boolean SingleTest(Node node) {
		if ((node.left == null && node.right != null) || (node.right == null && node.left != null)) {
			return true;
		} else {
			return false;
		}
	}
}

class Node {
	char c;
	int counter;
	Node left;
	Node right;

	// gets the character
	public double getChar() {
		return c;
	}

	// sets the character
	public void setChar(char c) {
		this.c = c;
	}

	// gets the character
	public int getCount() {
		return counter;
	}

	// sets the character
	public void setCount(int counter) {
		this.counter = counter;
	}

	// the node type
	public Node(char c, int counter) {
		this.c = c;
		this.counter = counter;
		left = null;
		right = null;
	}

}
