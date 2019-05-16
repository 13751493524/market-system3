package com.cn.test.数据结构和算法.链表;
/**
 * ��������
 * @author GJB
 *
 */
public class SortedList {
	public Link first;
	
	public SortedList(){
		first = null;
	}
	
	//public Link find(){}
	
	public void insert(int iData,double dData){
		Link newLink = new Link(iData,dData);
		Link current = first;
		Link previous = null;
		
		while(current != null && current.iData < iData){
			previous = current;
			current = current.next;
		}
		if(previous == null){
			first = newLink;
		}else{
			previous.next = newLink;
		}
		newLink.next = current;
	}
	
	public int remove(int key){
		Link current = first;
		Link previous = null;
		
		while(current != null){
			if(current.iData < key){
				previous = current;
				current = current.next;
				continue;
			}else if(current.iData > key){
				break;
			}else{
				if(previous == null){
					first = first.next;
				}else{
					previous.next = current.next;
				}
				return 1;
			}
		}
		return -1;
	}
	
	public void display(){
		Link current = first;
		while(current != null){
			current.display();
			current = current.next;
		}
	}
	
	public static void main(String[] args) {
		SortedList sl = new SortedList();
		sl.insert(8, 1.0);
		sl.insert(9, 1.0);
		sl.insert(10, 1.0);
		sl.insert(12, 1.0);
		sl.insert(11, 1.0);
		sl.display();
		System.out.println("==========================");
		sl.remove(8);
		sl.display();
	}
}
