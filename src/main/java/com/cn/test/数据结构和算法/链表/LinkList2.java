package com.cn.test.数据结构和算法.链表;
/**
 * ���Һ�ɾ��ָ���Ľڵ�
 * @author GJB
 *
 */
public class LinkList2 {
	public Link first;
	
	public LinkList2(){
		first = null;
	}
	
	public void insertFirst(int iData,double dData){
		Link newLink = new Link(iData,dData);
		newLink.next = first;
		first = newLink;
	}
	
	public Link find(int key){
		Link current = first;
		while(current != null){
			if(current.iData == key){
				return current;
			}
			current = current.next;
		}
		return null;
	}
	
	public Link delete(int key){
		Link current = first;
		Link previous = first;
		while(current != null){
			if(current.iData == key){
				break;
			}else{
				previous = current;
				current = current.next;
			}
		}
		if(current == first){
			first = first.next;
		}else{
			previous.next = current.next;
		}
		return current;
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
		LinkList2 linkList = new LinkList2();
		linkList.insertFirst(11, 11.0);
		linkList.insertFirst(18, 18.0);
		linkList.insertFirst(17, 17.0);
		linkList.insertFirst(19, 19.0);
		linkList.insertFirst(15, 15.0);
		linkList.insertFirst(13, 13.0);
		
		linkList.displayList();
		linkList.delete(18);
		linkList.displayList();
	}
}
