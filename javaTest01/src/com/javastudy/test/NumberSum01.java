package com.javastudy.test;

import java.util.Scanner;

public class NumberSum01 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);		
		int N = scan.nextInt();
		
		String strNums = scan.next();
		char[] charNums = strNums.toCharArray();
		
		int sum = 0;
		for(int i = 0 ; i < charNums.length; i++) {
			sum += charNums[i] - '0';
		}
		System.out.println(sum);
	}

}
