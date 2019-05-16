package com.cn.test.数据结构和算法.链表.双向链表;

public class Link {
	public long dData;
	public Link next;
	public Link previous;
	
	public Link(long dData){
		this.dData = dData;
	}
	
	public void display(){
		System.out.print(dData+" ");
	}
}
