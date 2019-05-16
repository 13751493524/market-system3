package com.cn.test.数据结构和算法;

public class HighArray {
	private long[] a;
	private int nElems;
	
	public HighArray(int max){
		a = new long[max];
		nElems = 0;
	}
	
	public int find(long value){
		int i = 0;
		for(;i<nElems;i++){
			if(a[i] == value){
				return i;
			}
			continue;
		}
		return -1;
	}
	
	public void insert(long value){
		a[nElems] = value;
		nElems++;
	}
	
	public boolean delete(long value){
		int index = find(value);
		if(index != -1){
			for(int i=index;i<nElems;i++){
				a[i]=a[i+1];
			}
			nElems--;
			return true;
		}
		return false;
	}
	
	public void display(){
		for(int i=0;i<nElems;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		HighArray array = new HighArray(100);
		array.insert(12);
		array.insert(15);
		array.insert(8);
		array.insert(99);
		System.out.println(array.find(8));
		array.display();
		array.delete(8);
		System.out.println();
		array.display();
	}
}