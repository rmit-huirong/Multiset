import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T extends Comparable<T>> extends Multiset<T>
{
	protected Node<T> head;
	protected Node<T> tail;
	protected int length;
	
	public SortedLinkedListMultiset() {
		head = null;
		tail = null;
		length = 0;
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {//D
		Node<T> newNode = new Node<T>(item);
		Node<T> currNode = head;
		// If head is empty, then the list is empty.
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
		else {
			for (int i = 0; i < length; ++i) {
				if (currNode.getItem().equals(item)) {
					currNode.setCount(currNode.getCount() + 1);
					return;
				}
				currNode = currNode.getNext();
			}
			currNode = head;
			for (int i = 0; i < length; ++i) {
				if (currNode.getItem().compareTo(item) > 0) {
					if (currNode.getPrev() == null) {
						currNode.setPrev(newNode);
						newNode.setNext(currNode);
						head = newNode;
					}
					else {
						currNode.getPrev().setNext(newNode);
						newNode.setPrev(currNode.getPrev());
						currNode.setPrev(newNode);
						newNode.setNext(currNode);
					}
					length++;
					return;
				}
				currNode = currNode.getNext();
			} 
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		length++;
	} // end of add()
	
	
	public int search(T item) {		
		Node<T> currNode = head;
		if (head == null) {}
		else {
			for (int i = 0; i < length; ++i) {
				if (currNode.getItem().equals(item)) {
					return currNode.getCount();
				}
				currNode = currNode.getNext();
			}
		}
		return 0;
	} // end of search()
	
	
	public void removeOne(T item) {
		Node<T> currNode = head;
		if (head == null) {}
		else{
			for (int i = 0; i < length; ++i) {
				if (currNode.getItem().equals(item)) {
					currNode.setCount(currNode.getCount() - 1);
					// If the count of the item is 0, the item will be removed
					if (currNode.getCount() == 0) {
						if (length == 1) {
							head = tail = null;
						}
						else {
							if (currNode == head) {
								head = head.getNext();
								head.setPrev(null);
							}
							if (currNode == tail) {
								tail = tail.getPrev();
								tail.setNext(null);
							}
							if (currNode.getPrev() != null && currNode.getNext() != null) {
								currNode.getPrev().setNext(currNode.getNext());
								currNode.getNext().setPrev(currNode.getPrev());
							}
						}
						currNode = null;
						length--;
					}
				return;
				}
			currNode = currNode.getNext();
			}
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {//D
		Node<T> currNode = head;
		if (head == null) {}
		else{
			for (int i = 0; i < length; ++i) {
				if (currNode.getItem().equals(item)) {
					if (length == 1) {
						head = tail = null;
					}
					else {
						if (currNode == head) {
							head = head.getNext();
							head.setPrev(null);
						}
						if (currNode == tail) {
							tail = tail.getPrev();
							tail.setNext(null);
						}
						if (currNode.getPrev() != null && currNode.getNext() != null) {
							currNode.getPrev().setNext(currNode.getNext());
							currNode.getNext().setPrev(currNode.getPrev());
						}
					}
					currNode = null;
					length--;
				return;
				}
			currNode = currNode.getNext();
			}
		}
	} // end of removeAll()
	
	
	public void print(PrintStream out) {//D
		String multiset = "";
		Node<T> currNode = head;
		for (int i = 0; i < length; ++i) {
			multiset += currNode.getItem() + printDelim + currNode.getCount() + "\n";
			currNode = currNode.getNext();
		}
		out.print(multiset);
	} // end of print()
	
	
	/**
     * Node type, inner private class.
     */
	private class Node<T> {
		protected T sllItem;
		private Node<T> sllNext;
		private Node<T> sllPrev;
		private int sllCount;
		
		public Node (T item) {
			sllItem = item;
			sllNext = null;
			sllPrev = null;
			sllCount = 1;
		}
		
		public T getItem() {
			return sllItem;
		}
		
		public Node<T> getNext() {
			return sllNext;
		}
		
		public Node<T> getPrev() {
			return sllPrev;
		}
		
		public int getCount() {
			return sllCount;
		}
		
		public void setNext(Node<T> next) {
			sllNext = next;
		}
		
		public void setPrev(Node<T> prev) {
			sllPrev = prev;
		}
		
		public void setCount(int count) {
			sllCount = count;
		}
	}
} // end of class SortedLinkedListMultiset