package com.cn.test.数据结构和算法;

public class 冒泡排序{

	public static void main(String[] args) {
		int a[] = new int[100];
		for(int i=0;i<a.length;i++){
			a[i] = (int)(Math.random()*100);
		}
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		//ð������
		for(int i=0;i<a.length-1;i++){
			for(int j=0;j<a.length-1-i;j++){
				if(a[j] > a[j+1]){
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
		System.out.println();
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}

}
