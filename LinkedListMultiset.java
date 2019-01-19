import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	protected Node<T> head;
	protected Node<T> tail;
	protected int length;
	
	
	public LinkedListMultiset() {
		head = null;
		tail = null;
		length = 0;
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		Node<T> newNode = new Node<T>(item);
		Node<T> currNode = head;
		// If head is empty, then the multiset is empty.
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
	}// end of removeOne()
	
	
	public void removeAll(T item) {
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
	
	
	public void print(PrintStream out) {
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
		protected T llItem;
		private Node<T> llNext;
		private Node<T> llPrev;
		private int llCount;
		
		public Node (T item) {
			llItem = item;
			llNext = null;
			llPrev = null;
			llCount = 1;
		}
		
		public T getItem() {
			return llItem;
		}
		
		public Node<T> getNext() {
			return llNext;
		}
		
		public Node<T> getPrev() {
			return llPrev;
		}
		
		public int getCount() {
			return llCount;
		}
		
		public void setNext(Node<T> next) {
			llNext = next;
		}
		
		public void setPrev(Node<T> prev) {
			llPrev = prev;
		}
		
		public void setCount(int count) {
			llCount = count;
		}
	}
} // end of class LinkedListMultiset