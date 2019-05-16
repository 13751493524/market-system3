package com.cn.test.数据结构和算法;

public class 归并排序{
	public static int[] merge(int a[],int b[]){
		int c[] = new int[a.length+b.length];
		int aDex=0,bDex=0,cDex=0;
		while(aDex < a.length && bDex < b.length){
			if(a[aDex] < b[bDex]){
				c[cDex++] = a[aDex];
				aDex++;
			}else{
				c[cDex++] = b[bDex];
				bDex++;
			}
		}
		if(aDex < a.length){
			for(int i=aDex;i<a.length;i++){
				c[cDex++] = a[i];
			}
		}else{
			for(int i=bDex;i<b.length;i++){
				c[cDex++] = b[i];
			}
		}
		return c;
	}
	
	public static void main(String[] args) {
		int[] a = {2,5,8,9};
		int[] b = {3,4,7,10,11,12};
		int[] c = 归并排序.merge(a, b);
		for(int i=0;i<c.length;i++){
			System.out.print(c[i]+"\t");
		}
	}
}
