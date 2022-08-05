package algorithm_study.week1_220728;

import java.util.Scanner;

public class Main_BJ_브론즈2_10870_피보나치 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println(fibonacci(sc.nextInt()));
	}
	
	public static int fibonacci(int n) {
		if (n < 2) return n;
		else return fibonacci(n-1) + fibonacci(n-2);
	}
}