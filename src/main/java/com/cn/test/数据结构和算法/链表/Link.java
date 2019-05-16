package com.cn.test.数据结构和算法.链表;

public class Link {
	public int iData;
	public double dData;
	public Link next;
	
	public Link(int iData,double dData){
		this.iData = iData;
		this.dData = dData;
	}
	
	public void display(){
		System.out.println("{"+iData+","+dData+"}");
	}
}
