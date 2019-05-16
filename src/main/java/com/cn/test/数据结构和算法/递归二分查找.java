package com.cn.test.数据结构和算法;

public class 递归二分查找{
	private long[] a;
	private int nElems;
	
	public 递归二分查找(int maxSize){
		a = new long[maxSize];
		nElems = 0;
	}
	
	public void insert(long value){
		if(nElems == 0){
			a[0] = value;
		}else{
			int i=0;
			for(;i<nElems;i++){
				if(value < a[i]){
					for(int j=nElems-1;j>=i;j--){
						a[j+1] = a[j];
					}
					break;
				}
			}
			a[i] = value;
		}
		nElems++;
	}
	
	public int find(long value){
		int lowerBound = 0;
		int upperBound = nElems-1;
		return recFind(value,lowerBound,upperBound);
	}
	
	public int recFind(long value,int lowerBound,int upperBound){
		int curIndex = (lowerBound + upperBound)/2;
		if(a[curIndex] == value){
			return curIndex;
		}else if(lowerBound > upperBound){
			return -1;
		}else{
			if(a[curIndex] > value){
				return recFind(value,lowerBound,curIndex-1);
			}else{
				return recFind(value,curIndex-1,upperBound);
			}
		}
	}
	
	public void display(){
		for(int i=0;i<nElems;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		递归二分查找 efSearch= new 递归二分查找(100);
		efSearch.insert(44);
		efSearch.insert(33);
		efSearch.insert(55);
		efSearch.insert(77);
		efSearch.insert(66);
		efSearch.display();
		System.out.println("index:"+efSearch.find(55));
		System.out.println("index:"+efSearch.find(8));
	}
}
