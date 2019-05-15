package com.cn.test.二进制测试;

public class EjzTest {
	public static void main(String[] args) {
		//<<      :     左移运算符，num << 1,相当于num乘以2
		//>>      :     右移运算符，num >> 1,相当于num除以2
		//>>>    :     无符号右移，忽略符号位，空位都以0补齐
		System.out.println(Integer.toBinaryString(15));
		int c = 7;
		System.out.println(Integer.toBinaryString(c));
		c = c << 1;
		System.out.println(Integer.toBinaryString(c));
		c = c << 1;
		System.out.println(Integer.toBinaryString(c));
		System.out.println(c);
	}
}
