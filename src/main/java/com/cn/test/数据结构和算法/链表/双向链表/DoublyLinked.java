package com.cn.test.数据结构和算法.链表.双向链表;

public class DoublyLinked {
	public Link first;
	public Link last;
	
	public DoublyLinked(){
		first = null;
		last = null;
	}
	public boolean isEmpty(){
		return first == null;
	}
	public void insertFirst(long dData){
		Link newLink = new Link(dData);
		if(isEmpty()){
			last = newLink;
		}else{
			first.previous = newLink;
		}
		newLink.next = first;
		first = newLink;
	}
	
	public void insertLast(long dData){
		Link newLink = new Link(dData);
		if(isEmpty()){
			first = newLink;
		}else{
			last.next = newLink;
		}
		newLink.previous = last;
		last = newLink;
	}
	
	public void deleteKey(long dData){
		if(!isEmpty()){
			Link current = first;
			while(current != null){
				if(current.dData == dData){
					if(current == first){
						first = current.next;
						current.previous = null;
					}else if(current == last){
						last = current.previous;
						last.next = null;
					}else{
						current.previous.next = current.next;
						current.next.previous = current.previous;
					}
					break;
				}
				current = current.next;
			}
		}
	}
	
	public void insertAfter(long key,long dData){
		Link newLink = new Link(dData);
		if(first == null){
			first = newLink;
			last = newLink;
			return;
		}
		Link current = first;
		while(current != null){
			if(current.dData == key){
				if(current == last){
					last.next = newLink;
					newLink.previous = last;
					last = newLink;
				}else{
					current.next.previous = newLink;
					newLink.next = current.next;
					newLink.previous = current;
					current.next = newLink;
				}
				break;
			}
			current = current.next;
		}
	}
	
	public void display(){
		Link current = first;
		while(current != null){
			current.display();
			current = current.next;
		}
	}
	
	public static void main(String[] args) {
		DoublyLinked dl = new DoublyLinked();
		dl.insertFirst(44);
		dl.insertFirst(22);
		dl.insertFirst(33);
		dl.insertLast(55);
		dl.insertFirst(66);
		dl.display();
		System.out.println();
		dl.deleteKey(22);
		dl.deleteKey(33);
		dl.display();
		System.out.println();
		dl.insertAfter(55,10);
		dl.insertAfter(10,12);
		dl.display();
	}
}
