package practice._1014;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_홈방범서비스 {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					max = Math.max(max, bfs(i,j));
				}
			}
			
			System.out.println("#"+test_case+" "+max);
		}
	}
	
	static int N, M, max;
	static int[][] map;
	static int[][] delta = {{0,1},{0,-1},{1,0},{-1,0}};
	
	static int bfs(int r, int c) {
		int result = Integer.MIN_VALUE;
		int cnt = 0;
		int K = 0;
		int sum = 0;
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		visited[r][c] = true;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				int[] p = q.poll();
				if(map[p[0]][p[1]]==1) {
					sum += M;
					cnt++;
				}
				for(int[] d : delta) {
					int tr = p[0]+d[0];
					int tc = p[1]+d[1];
					if(0<=tr && tr<N && 0<=tc && tc<N && !visited[tr][tc]) {
						q.add(new int[] {tr,tc});
						visited[tr][tc] = true;
					}
				}
			}
			K++;
			int cost = K * K + (K - 1) * (K - 1);
			if(sum-cost >= 0) result = Math.max(result, cnt);	
		}
		
		return result;
	}
}