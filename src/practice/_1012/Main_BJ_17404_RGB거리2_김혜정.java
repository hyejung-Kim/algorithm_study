package practice._1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17404_RGB거리2_김혜정 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
//		final int RED = 0;
//		final int GREEN = 1;
//		final int BLUE = 2;
		int[][] house = new int[N+1][3];
		int[][] dp = new int[N+1][3];	//1번 집의 색이 0,1,2일 때 각 집을 칠하는 최소비용
		int[][] color = new int[N+1][3]; //1번 집의 색이 0,1,2일 때 각 집을 칠한 색
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) house[i][j] = Integer.parseInt(st.nextToken()); 
			for (int j = 0; j < 3; j++) {
				int c = color[i-1][j];	//이전 집의 색
				if(house[N][(c+1)%3] < house[N][(c+2)%3]) color[i][j] = (c+1)%3;
				else color[i][j] = (c+2)%3;
				dp[i][j] = Math.min(house[N][(c+1)%3],house[N][(c+2)%3]) + dp[i-1][j];
			}
		}
		
		
		int min = Integer.MAX_VALUE;

		
		System.out.println(min);
		
		
	}	
	
}