package algorithm_study.week5_2208_4;

import java.util.Scanner;

public class Main_BJ_1712_손익분기점 {
	/**
	 * 고정비용 A만원(판매대수 상관X), 노트북 한대당 B만원, 노트북 판매가 C만원
	 * 손익분기점의 판매량을 구하라. 존재하지 않으면 -1
	 */
	
	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		
		if(B>=C) System.out.println(-1);
		else {
			int profit = C-B;
			int result = A/profit + 1;
			System.out.println(result);
		}
	
	}

}
