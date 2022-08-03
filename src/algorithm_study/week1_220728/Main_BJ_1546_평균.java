package algorithm_study.week1_220728;

import java.util.Scanner;

public class Main_BJ_1546_평균 {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] test = new int[N];
		int max = 0;
		double sum = 0;

		
		for (int i = 0; i < N; i++) {
			test[i]=sc.nextInt();
			max = Math.max(max, test[i]);
		}

		if (max != 0) {
			for (int i = 0; i < N; i++) {
				sum += (double) test[i] / max * 100;
			}
		}

		System.out.println(sum / N);
	}

}