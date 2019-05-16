package com.cn.test.数据结构和算法;

public class 快速排序{
	long[] highArray;
	public 快速排序(long[] highArray){
		this.highArray = highArray;
	}
	
	public void quickSort(){
		recQuickSort(0,highArray.length-1);
	}
	
	public void recQuickSort(int leftPtr,int rightPtr){
		if(leftPtr >= rightPtr){
			return;
		}else{
			long pivot = highArray[rightPtr];
			int partionInt = recPartionInt(leftPtr,rightPtr,pivot);
			recQuickSort(leftPtr,partionInt-1);
			recQuickSort(partionInt+1,rightPtr);
		}
	}
	
	public int recPartionInt(int leftPtr,int rightPtr,long pivot){
		int left = leftPtr-1;
		int right = rightPtr;
		
		while(true){
			while(highArray[++left] < pivot);//���һ����pivot,������������Ȼ���˳�
			
			while(right > 0 && highArray[--right] > pivot);
			
			if(left >= right){
				break;
			}else{
				swap(left,right);
			}
		}
		swap(left,rightPtr);
		return left;
	}
	
	private void swap(int index1,int index2){
		long temp = highArray[index2];
		highArray[index2] = highArray[index1];
		highArray[index1] = temp;
	}
	
	public static void main(String[] args) {
		try {
			long a[] = new long[100];
			for(int i=0;i<a.length;i++){
				a[i] = (long)(Math.random()*100);
			}
			for(int i=0;i<a.length;i++){
				System.out.print(a[i]+" ");
			}

			System.out.println();
			快速排序 quickSort = new 快速排序(a);
			quickSort.quickSort();
			for(int i=0;i<quickSort.highArray.length;i++){
				System.out.print(quickSort.highArray[i]+" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
