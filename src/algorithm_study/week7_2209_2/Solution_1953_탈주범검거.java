package algorithm_study.week7_2209_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거{
	
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("C:\\Users\\SSAFY\\Downloads\\sample_input (5).txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	//지도 세로길이
			int M = Integer.parseInt(st.nextToken());	//지도 가로길이
			int R = Integer.parseInt(st.nextToken());	//맨홀뚜껑 r값
			int C = Integer.parseInt(st.nextToken());	//맨홀뚜껑 c값
			int L = Integer.parseInt(st.nextToken());	//탈출 후 소요시간
			
			int[][] map  = new int[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//상하좌우
			int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
			//0번 터널 사용 X
			int[][] tunnel = {{},{0,1,2,3},{0,1},{2,3},{0,3},{1,3},{1,2},{0,2}};
			
			boolean[][] visited = new boolean[N][M];
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {R,C});
			visited[R][C] = true;
			int depth = 0;
			int cnt = 0;
			while(depth<L) {
				int size = q.size();
				for(int i=0; i<size; i++) {
					int[] cur = q.poll();
					cnt++;
					
					for(int d : tunnel[map[cur[0]][cur[1]]]) {
						int tr = cur[0]+delta[d][0];
						int tc = cur[1]+delta[d][1];
						if(0<=tr && tr<N && 0<=tc && tc<M && map[tr][tc]>0 && !visited[tr][tc]) {
							int next = map[tr][tc];
							boolean isOffer = true;
							switch(d){
							case 0: 
								if(next==3 || next==4 || next==7) isOffer = false;
								break;
							case 1:
								if(next==3 || next==5 || next==6) isOffer = false;
								break;
							case 2:
								if(next==2 || next==6 || next==7) isOffer = false;
								break;
							case 3:
								if(next==2 || next==4 || next==5) isOffer = false;
								break;
							}
							if(isOffer) {
								visited[tr][tc] = true;
								q.offer(new int[] {tr,tc});
							}
						}
					}
				}
				depth++;
			}
			
			System.out.println("#"+test_case+" "+cnt);
		}		
               
	}
	
}