import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T extends Comparable<T>> extends Multiset<T>
{
	protected Node<T> root;
	protected boolean isInTree = false;
	protected String multiset = "";
	
	public BstMultiset() {
		root = null;
	} // end of BstMultiset()

	public void add(T item) {
		root = add(root, item);
	} // end of add()

	protected Node<T> add(Node<T> root, T item) {
		Node<T> newNode = new Node<T>(item);
		// If root is empty, then the tree is empty. 
		if (root == null) {
			root = newNode;
		}
		else if (item.compareTo(root.getItem()) < 0){
			root.setLeftChild(add(root.getLeftChild(), item));
		}
		else if (item.compareTo(root.getItem()) > 0){
			root.setRightChild(add(root.getRightChild(), item));
		}
		else {
			root.setCount(root.getCount() + 1);
			}
		return root;
	} // end of add()
	
	public int search(T item) {
		isInTree = false;
		Node<T> currNode = root;
		currNode = search(root, item);
		if (isInTree) {
			return currNode.getCount();
		}
		else {
			return 0;
		}
	} // end of search()
	
	protected Node<T> search(Node<T> root, T item) {
		if (root == null) {}
		else if (item.compareTo(root.getItem()) < 0) {
			return search(root.getLeftChild(), item);
		}
		else if (item.compareTo(root.getItem()) > 0) {
			return search(root.getRightChild(), item);
		}
		else if (root.getItem().equals(item)) {
			isInTree = true;
		}
		return root;
	} // end of search()
	
	public void removeOne(T item) {
		root = removeOne(root, item);
	} // end of removeOne()
	
	protected Node<T> removeOne(Node<T> root, T item) {
		if (root == null) {}
		else if (item.compareTo(root.getItem()) < 0){
			root.setLeftChild(removeOne(root.getLeftChild(), item));
		}
		else if (item.compareTo(root.getItem()) > 0){
			root.setRightChild(removeOne(root.getRightChild(), item));
		}
		else {
			if (root.getCount() == 1) {
				if (root.getLeftChild() == null) {
					return root.getRightChild();
				}
				else if (root.getRightChild() == null) {
					return root.getLeftChild();
				}
				root.setItem(min(root.getRightChild()).getItem());
				root.setCount(min(root.getRightChild()).getCount());
				root.setRightChild(removeAll(root.getRightChild(), root.getItem()));
			}
			else {
				root.setCount(root.getCount() - 1);
			}
		}
		return root;
	} // end of removeOne()
	
	public void removeAll(T item) {
		root = removeAll(root, item);
	} // end of removeAll()

	protected Node<T> removeAll(Node<T> root, T item) {
		if (root == null) {}
		else if (item.compareTo(root.getItem()) < 0){
			root.setLeftChild(removeAll(root.getLeftChild(), item));
		}
		else if (item.compareTo(root.getItem()) > 0){
			root.setRightChild(removeAll(root.getRightChild(), item));
		}
		else {
			if (root.getLeftChild() == null) {
				return root.getRightChild();
			}
			else if (root.getRightChild() == null) {
				return root.getLeftChild();
			}
			root.setItem(min(root.getRightChild()).getItem());
			root.setCount(min(root.getRightChild()).getCount());
			root.setRightChild(removeAll(root.getRightChild(), root.getItem()));
		}
		return root;
	} // end of removeAll()

	public Node<T> min(Node<T> root) {
		if (root.getLeftChild() != null) {
			while (root.getLeftChild().getLeftChild() != null) {
				root = root.getLeftChild();
			}
		}
		return root;
	}
	
	public void print(PrintStream out) {
		this.multiset = "";
		out.print(print(root));
	} // end of print()

	protected String print(Node<T> root) {
		if (root != null)
		{
			print(root.getLeftChild());
			this.multiset += root.getItem() + printDelim + root.getCount() + "\n";
			print(root.getRightChild());
		}
		return this.multiset;
	}
	
	/**
     * Node type, inner private class.
     */
	class Node<T> {
		protected T bstItem;
		private Node<T> bstLeft;
		private Node<T> bstRight;
		private int bstCount;
		
		public Node (T item) {
			bstItem = item;
			bstLeft = null;
			bstRight = null;
			bstCount = 1;
		}
		
		public T getItem() {
			return bstItem;
		}
		
		public Node<T> getLeftChild() {
			return bstLeft;
		}
		
		public Node<T> getRightChild() {
			return bstRight;
		}
		
		public int getCount() {
			return bstCount;
		}
		
		public void setItem(T item) {
			bstItem = item;
		}
		
		public void setLeftChild(Node<T> left) {
			bstLeft = left;
		}
		
		public void setRightChild(Node<T> right) {
			bstRight = right;
		}
		
		public void setCount(int count) {
			bstCount = count;
		}
	}
} // end of class BstMultiset
