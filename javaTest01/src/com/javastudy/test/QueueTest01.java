package com.javastudy.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueueTest01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		Queue<Integer> cardQueue = new LinkedList<>();
		
		// 카드를 큐에 저장
		
		for(int i = 1 ; i <= N ; i++) {
			cardQueue.add(i);
		}
		
		while(cardQueue.size() > 1) {
			cardQueue.poll();
			cardQueue.add(cardQueue.poll());
		}
		System.out.println(cardQueue.peek());
		scan.close();
	}

}
