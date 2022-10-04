package _0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17070_파이프옮기기1_김혜정 {

	static int N;
	static int[][] house;
	static int cnt;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		house = new int[N + 1][N + 1];

		// 빈 칸은 0, 벽은 1로 주어진다. (1, 1)과 (1, 2)는 항상 빈 칸이다.
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}

		}

		int[][][] dp = new int[N + 1][N + 1][3]; // 한쪽 끝이 (N,N)까지 이동하는 방법의 개수 => 가로 세로 대각선 모양으로 도착할 때 각각

		for (int i = 1; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				if (i == 1 && j == 2)
					dp[i][j][0] = 1;
				else {
					if (house[i][j] == 0) {
						if (house[i][j - 1] == 0)
							dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
						if (house[i - 1][j] == 0)
							dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
						if (house[i - 1][j] == 0 && house[i][j - 1] == 0 && house[i - 1][j - 1] == 0)
							dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
					}
				}
			}
		}

		cnt = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];

		// dfs(1,2,0);
		System.out.println(cnt);

	}

//	dfs
	
//	private static void dfs(int r, int c, int dir) {
//		if(r==N && c==N) {
//			cnt++;
//			return;
//		}
//		
//		switch(dir) {
//		case 0:
//			if(check(r, c, 0)) dfs(r,c+1,0);
//			if(check(r, c, 2)) dfs(r+1,c+1,2);
//			break;
//		case 1:
//			if(check(r, c, 1)) dfs(r+1,c,1);
//			if(check(r, c, 2)) dfs(r+1,c+1,2);
//			break;
//		case 2:
//			if(check(r, c, 0)) dfs(r,c+1,0);
//			if(check(r, c, 1)) dfs(r+1,c,1);
//			if(check(r, c, 2)) dfs(r+1,c+1,2);
//			break;			
//		}
//	}
//	
//	private static boolean check(int r, int c, int dir) {
//		boolean res = false;
//		switch(dir) {
//		case 0:	//가로
//			if(rangeCheck(r,c+1) && house[r][c+1]==0) res = true;
//			break;
//		case 1:	//세로
//			if(rangeCheck(r+1,c) && house[r+1][c]==0) res = true;
//			break;
//		case 2:	//대각선
//			if(rangeCheck(r,c+1) && rangeCheck(r+1,c) && rangeCheck(r+1,c+1) && 
//					house[r][c+1]==0 && house[r+1][c]==0 && house[r+1][c+1]==0) res = true;
//			break;
//		}
//		return res;
//	}
//	
//	private static boolean rangeCheck(int r, int c) {
//		if(0<r && r<=N && 0<c && c<=N) return true;
//		else return false;
//	}

}
