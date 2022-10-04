package _0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1149_RGB거리_김혜정 {
	/*
	 * RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다. 집은 빨강, 초록, 파랑 중
	 * 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는
	 * 비용의 최솟값을 구해보자. - 1번 집의 색은 2번 집의 색과 같지 않아야 한다. - N번 집의 색은 N-1번 집의 색과 같지 않아야
	 * 한다. - i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
	 * 
	 * 첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이
	 * 1번 집부터 한 줄에 하나씩 주어진다. 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.
	 * 
	 * 모든 집을 칠하는 비용의 최솟값을 출력한다.
	 */

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][3];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) dp[0][i] = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				int curCost = Integer.parseInt(st.nextToken());
				dp[i][j] = Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]) + curCost;
			}
			
		}
		
		int result = Math.min(dp[N-1][0], dp[N-1][1]);
		result = Math.min(dp[N-1][2], result);
		
		System.out.println(result);
	}
	
//	static int paint(int index, int color) {
//		if(dp[index][color]!=0) return dp[index][color];
//		dp[index][color] = Math.min(paint(index-1, (color+1)%3), paint(index-1, (color+2)%3)) + cost[index][color];
//		return dp[index][color];
//	}

}
