package com.cn.test.数据结构和算法;

public class 希尔排序 {
	long[] hignArray;
	
	public 希尔排序(long[] hignArray){
		this.hignArray = hignArray;
	}
	
	public void shellSort(){
		int h = 1;
		while(h <= hignArray.length/3){
			h = h*3 + 1;
		}
		System.out.println("h:"+h);
		while(h > 0){	
			for(int outer=h;outer<hignArray.length;outer++){
				long temp = hignArray[outer];
				int inner = outer;
				while(inner > h-1 && hignArray[inner-h] >= temp){
					hignArray[inner] = hignArray[inner-h];
					inner -= h;
				}
				hignArray[inner] = temp;
			}
			h = (h-1)/3;
		}
		
	}
	
	public static void main(String[] args) {
		long a[] = new long[10000];
		for(int i=0;i<a.length;i++){
			a[i] = (long)(Math.random()*10000);
		}
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}

		long last = System.currentTimeMillis();
		希尔排序 shellSort = new 希尔排序(a);
		shellSort.shellSort();
		long next = System.currentTimeMillis();
		System.out.println("spend time:"+(next-last));
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}
}
