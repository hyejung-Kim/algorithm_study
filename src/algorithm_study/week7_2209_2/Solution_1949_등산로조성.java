package algorithm_study.week7_2209_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1949_등산로조성{
	
	static int N,K;
	static int[][] map;
	static List<int[]> high;
	static int max;
	static int[][] delta = {{0,1},{0,-1},{1,0},{-1,0}};
	static boolean[][] visited;
	static boolean isCut;
	
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("C:\\Users\\SSAFY\\Downloads\\sample_input (4).txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			max = 0; 
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//지도 한변의 크기
			K = Integer.parseInt(st.nextToken());	//최대 공사 가능 깊이
			
			map = new int[N][N];
			int maxHeight = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>maxHeight) {
						maxHeight = map[i][j];
						high = new ArrayList<>();
					}
					if(map[i][j]==maxHeight) high.add(new int[] {i,j});
				}
			}
			
			for(int[] h : high) {
				visited = new boolean[N][N];
				isCut = false;
				visited[h[0]][h[1]] = true;
				dfs(maxHeight, h[0], h[1], 1);
			}
			
			System.out.println("#"+test_case+" "+max);
			
		}		
               
	}
	
	static void dfs(int height, int r, int c, int length) {
		for(int[] d : delta) {
			int tr = r+d[0];
			int tc = c+d[1];
			if(0<=tr && tr<N && 0<=tc && tc<N && !visited[tr][tc]) {
				visited[tr][tc] = true;
				int nextHeight = map[tr][tc];
				//다음 봉우리가 더 작은 경우
				if(nextHeight<height) dfs(nextHeight, tr,tc,length+1);
				//다음 봉우리가 같거나 크지만, 깎을 수 있는 경우
				else if(!isCut && nextHeight>=height && nextHeight-K<height) {
					isCut = true;
					dfs(height-1, tr,tc, length+1);
					isCut = false;
				}
				visited[tr][tc] = false;
			}
		}
		
		//더 이상 갈수 있는 곳이 없음
		max = Math.max(length, max);
	}
}