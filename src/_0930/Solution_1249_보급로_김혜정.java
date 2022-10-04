package _0930;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution_1249_보급로_김혜정 {
	
	static int N;
	static int min = Integer.MAX_VALUE;
	static int[][] delta = {{0,1},{0,-1},{1,0},{-1,0}};
	static int[][] map;
	static int[][] cost;
	
	public static void main(String args[]) throws Exception {

		System.setIn(new FileInputStream("C:\\Users\\SSAFY\\Downloads\\input (1).txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cost = new int[N][N];
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				Arrays.fill(cost[i], Integer.MAX_VALUE);
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(s.charAt(j)+"");
				}
			}
			
			PriorityQueue<Position> q = new PriorityQueue<Position>();
			cost[0][0]=map[0][0];
			q.offer(new Position(0,0,map[0][0]));
			while(!q.isEmpty()) {
				Position p = q.poll();
				cost[p.r][p.c] = Math.min(cost[p.r][p.c], p.cost);
				
				for(int i=0; i<4; i++) {
					int tr = p.r+delta[i][0];
					int tc = p.c+delta[i][1];
					
					if(0<=tr && tr<N && 0<=tc && tc<N && cost[tr][tc]>cost[p.r][p.c]+map[tr][tc]) {
						q.offer(new Position(tr, tc, cost[p.r][p.c]+map[tr][tc]));
					}
				}
			}
			
			System.out.println("#"+test_case+" "+cost[N-1][N-1]);
		}
	}
	
	
	static class Position implements Comparable{
		int r;
		int c;
		int cost;
		
		Position(int r, int c, int value){
			this.r=r;
			this.c=c;
			this.cost=value;
		}
		
		@Override
		public int compareTo(Object o) {
			Position p = (Position) o;
			return this.cost - p.cost;
		}

		@Override
		public String toString() {
			return "["+"("+ r + ", " + c + ") "+cost+"]";
		}
		
	}
}