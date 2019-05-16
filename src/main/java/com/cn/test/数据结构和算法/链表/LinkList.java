package com.cn.test.数据结构和算法.链表;

public class LinkList {
	private Link first;
	
	public LinkList(){
		first = null;
	}
	
	public boolean isEmpty(){
		return (first == null);
	}
	
	public void insertFirst(int id,double dd){
		Link newLink = new Link(id,dd);
		newLink.next = first;
		first = newLink;
	}
	
	public Link deleteFirst(){
		Link temp = first;
		first = first.next;
		return first;
	}
	
	public void displayList(){
		Link current = first;
		while(current != null){
			current.display();
			current = current.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		LinkList linkList = new LinkList();
		linkList.insertFirst(11, 11.0);
		linkList.insertFirst(18, 18.0);
		linkList.insertFirst(17, 17.0);
		linkList.insertFirst(19, 19.0);
		linkList.insertFirst(15, 15.0);
		linkList.insertFirst(13, 13.0);
		linkList.insertFirst(11, 8.0);
		
		linkList.displayList();
		linkList.deleteFirst();
		linkList.displayList();
	}
}
