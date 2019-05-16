package com.cn.test.数据结构和算法;

public class 选择排序 {
	public static void main(String[] args) {
		int a[] = new int[10000];
		for(int i=0;i<a.length;i++){
			a[i] = (int)(Math.random()*10000);
		}
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		long last = System.currentTimeMillis();
		//ѡ������
		for(int i=0;i<a.length-1;i++){
			int index = i;
			int min = a[index];
			for(int j = i;j<a.length-1;j++){
				if(min > a[j+1]){
					min = a[j+1];
					index = j+1;
				}
			}
			int temp = a[i];
			a[i] = min;
			a[index] = temp;
		}
		long next = System.currentTimeMillis();
		System.out.println("spend time:"+(next-last));
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
		int value = a[0];
		System.out.println(value);
		int lowerBound = 0;
		int upperBound = a.length;
		int currentBound = (lowerBound+upperBound)/2;
		while(true){
			if(upperBound < currentBound){
				System.out.println("can not find it");
				break;
			}else if(value == a[currentBound]){
				System.out.println("currentBound:"+currentBound);
				break;
			}else if(value < a[currentBound]){
				upperBound = currentBound-1;
			}else{
				lowerBound = currentBound+1;
			}
			currentBound = (lowerBound+upperBound)/2;
		}
		
	}
}
