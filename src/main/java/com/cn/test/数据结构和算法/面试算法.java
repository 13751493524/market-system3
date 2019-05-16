package com.cn.test.数据结构和算法;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 面试算法 {
	public static void main(String[] args) {
		Integer dimension = 6;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//try {
			//String num = br.readLine();
			//if(!"quit".equals(num)){
			//	dimension = Integer.valueOf(num);
			//}
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		
		int a[][]= new int[dimension][dimension];
		for(int i=0;i<dimension;i++){
			for(int j=0;j<dimension;j++){
				if(j==0 || i==dimension-1){
					a[i][j] = i+j+1;
				}else if(j == dimension-1){
					a[i][j] = dimension*3-1-a[i][0];
				}else if(i == 0 && j == 1){
					a[i][j] = dimension*4-4;
				}else if(j == 1 && i != dimension-1){
					a[i][j] = a[0][1]+i;
				}else if(i == 0 && j != 0){
					a[i][j] = a[0][1]-j+1;
				}else if(i == dimension-2 && j != 0 && j != dimension-1){
					a[i][j] = a[dimension-2][j-1]+1;
				}
				System.out.print(a[i][j]+"    ");
			}
			System.out.println();
		}
		
	}
}
