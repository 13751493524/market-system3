package com.cn.test.数据结构和算法;

public class 递归归并排序 {
	static int array[];
	public static int[] mergeSort(){
		int[] workspace = new int[array.length];
		recMergeSort(workspace,0,workspace.length-1);
		return workspace;
	}
	
	public static void recMergeSort(int[] workspace,int leftPtr,int rightPtr){
		if(leftPtr == rightPtr){
			return;
		}else{
			int mid = (leftPtr+rightPtr)/2;
			recMergeSort(workspace,leftPtr,mid);
			recMergeSort(workspace,mid+1,rightPtr);
			merge(workspace,leftPtr,mid+1,rightPtr);
		}
	}
	
	private static void merge(int[] workspace,int leftPtr,int highptr,int rightPtr){
		int j = 0;
		int lowerPtr = leftPtr;
		int mid = highptr - 1;
		int n = rightPtr-leftPtr+1;
		while(lowerPtr <= mid && highptr <= rightPtr){
			if(array[lowerPtr] < array[highptr]){
				workspace[j++]=array[lowerPtr];
				lowerPtr++;
			}else{
				workspace[j++]=array[highptr];
				highptr++;
			}
		}
		while(lowerPtr <= mid){
			workspace[j++] = array[lowerPtr++];
		}
		
		while(highptr <= rightPtr){
			workspace[j++] = array[highptr++];
		}
		
		for(int i=0;i<n;i++){
			array[leftPtr+i]=workspace[i];
		}
	}
	
	public static void main(String[] args) {
		递归归并排序.array = new int[10000];
		for(int i=0;i<array.length;i++){
			array[i] = (int)(Math.random()*10000);
		}
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		long last = System.currentTimeMillis();
		System.out.println();
		递归归并排序.mergeSort();
		long next = System.currentTimeMillis();
		System.out.println("spend time:"+(next-last));
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}
}
