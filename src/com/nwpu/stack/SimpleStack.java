package com.nwpu.stack;



public class SimpleStack<E> implements IStack<E> {
	private  Node<E> head;
	private int size;
	
	@Override
	public void push(E e) {
//		Node<E> newNode = new Node<>(e, null);
//		Node<E> first = head;
//
//		head =newNode;
//		if(first!=null) {
//			newNode.next = first;
//		}
//
//		size++;


		Node<E> newNode = new Node<E>(e, head);
		head = newNode;
		size++;
	}

	@Override
	public E pop() {
//		Node<E> first = head;
//		E e = first.data;
//		if(size!=0) {
//			head = first.next;
//			first = null;
//		}
//
//		return e;
		E e = head.data;
		if (size != 0) {
			head = head.next;
		}
		return e;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<E> tmp = head;
		
		while(tmp!=null) {
			sb.append(tmp.data+",");
			tmp = tmp.next;
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	private static class Node<E>{
		E data;
		Node<E> next;
		public Node(E data, Node<E> next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		
	}

}
