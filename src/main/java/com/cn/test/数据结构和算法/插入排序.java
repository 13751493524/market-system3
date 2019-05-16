package com.cn.test.数据结构和算法;

public class 插入排序 {
	public static void main(String[] args) {
		int a[] = new int[15];
		for(int i=0;i<a.length;i++){
			a[i] = (int)(Math.random()*100);
		}
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		//��������
		for(int out = 1;out < a.length;out++){
			int temp = a[out];
			int in = out;
			while(in > 0 && a[in-1] >= temp){
				a[in] = a[in-1];
				--in;
			}
			a[in] = temp;
		}
		System.out.println();
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}
}
