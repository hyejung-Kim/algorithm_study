package algorithm_study.week1_2207_5;

import java.util.Scanner;

public class Main_BJ_골드5_2447_별찍기10 {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		// N은 3의 거듭제곱
		N = sc.nextInt();
		isPrinted = new boolean[N + 1][N + 1];

		printStar(1, 1);
		System.out.println(sb);
	}

	static int N;
	static boolean[][] isPrinted;

	static void printStar(int r, int c) {
		if (r == N + 1 && c == 1)
			return;

		// r과 c가 모두 3의i승~3의i+1승 이내라면 공백 출력
		boolean isBlank = false;
		// N의 세제곱근
		int pow = (int) Math.cbrt(N);
		for (int i = 0; i < pow; i++) {
			if (Math.pow(3, i) < r && r < Math.pow(3, i + 1)) {
				for (int j = 0; j < pow; j++) {
					if (!isPrinted[r][c] && Math.pow(3, j) < c && c < Math.pow(3, j + 1)) {
						System.out.print(" ");
						isPrinted[r][c] = true;
						isBlank = true;
					}
				}
			}
		}

		// 공백이 아니면 별찍기
		if (isBlank == false) {
			System.out.print("*");
			isPrinted[r][c] = true;
		}

		// N개 찍을 때마다 줄바꿈
		if (c == N) {
			System.out.println();
			c = 0;
			r++;
		}

		printStar(r,c+1);
	}
}