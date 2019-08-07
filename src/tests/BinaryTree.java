package tests;
/** Class for a binary tree that stores type E objects. */
public class BinaryTree<E> {

	/** Class to encapsulate a tree node. */
	protected static class Node<E> {
		protected E data;

		protected Node<E> right;

		protected Node<E> left;

		public Node(E data) {
			this.data = data;
			right = null;
			left = null;
		}

		/** Return a string representation of the node.
		 @return A string representation of the data fields
		 */
		public String toString () {
			return data.toString();
		}
	}

	protected Node<E> root;

	public BinaryTree() {
		root = null;
	}

	protected BinaryTree(Node<E> root) {
		this.root = root;
	}

	/** Constructs a new binary tree with data in its root leftTree
	 as its left subtree and rightTree as its right subtree.
	 */
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new Node<>(data);
		if (leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}

		if (rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}
	}





	/*
	 * Return the left subtree.
	 * @return The left subtree or null if either the root or the left subtree isnull
	 *
	 */
	public BinaryTree<E> getLeftSubtree() {
		if (root != null && root.left != null) {
			return new BinaryTree(root.left);
		} else {
			return null;
		}
	}

	public BinaryTree<E> getRightSubtree() {
		if (root != null && root.right != null) {
			return new BinaryTree(root.right);
		} else {
			return null;
		}
	}


	public E getData() {

		return root.data;
	}

	public boolean isLeaf() {

		return (root != null && root.left == null && root.right == null);
	}

	public boolean isEmpty() {

		return root == null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}

	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {


	}

	public int numOfNodes() {
		return numOfNodes(root);
	}

	private int numOfNodes(Node<E> root) {
		if (root == null) {
			return 0;
		}
		return 1 + numOfNodes(root.left) + numOfNodes(root.right);
	}
}







	