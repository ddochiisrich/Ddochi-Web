package com.javastudy.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.IntStream;

public class StreamApi01 {

	public static void main(String[] args) {
		
		
		int [] arr = {80, 21, 33, 70, 91, 70, 56, 60, 21, 100, 42, 30, 91};
		
		
		//////////////////////////////////////////////////////////////////////////////
		
		
		ArrayList<Integer> nList = new ArrayList<>();
		for(int n : arr) {
			if(n % 7 == 0) {
			nList.add(n);
			}
		}
		
		HashSet<Integer> set = new HashSet<>(nList);
		ArrayList<Integer> uList = new ArrayList<>(set);
		Collections.sort(uList, Comparator.reverseOrder());
		
		arr = new int[uList.size()];
		
		for(int i = 0 ; i < arr.length; i++) {
			arr[i] = uList.get(i);
		}
//		System.out.println(Arrays.toString(arr));
		
		for(int i = 0 ; i < arr.length ; i++) {
			System.out.print(arr[i]);
			
		}
		System.out.println();
		
		
		//////////////////////////////////////////////////////////////////////////////
		
		
		IntStream intStream = Arrays.stream(arr);
		
		String result = intStream.boxed().distinct().sorted()
				.filter(n->n%7==0)
				.map(n->""+n)
				.reduce("", (a, b) -> b+a);
		
		System.out.println(result);
	}	
}
