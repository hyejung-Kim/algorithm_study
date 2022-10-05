package practice._1005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2636_치즈_김혜정2 {
	
	static int R,C,cheese;
	static int[][] map;
	static boolean[][] visited;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static Queue<int[]> q = new LinkedList<>();
	static Queue<int[]> melt = new LinkedList<>();
	
	static void melt() {
		while(!q.isEmpty()) {
			int[] p = q.poll();
			for(int[] d: delta) {
				int tr = p[0] + d[0];
				int tc = p[1] + d[1];
				if(0<=tr && tr<R && 0<=tc && tc<C && !visited[tr][tc]) {
					if(map[tr][tc]==0) {
						q.add(new int[] {tr,tc});
						visited[tr][tc] = true;
					}
					else melt.add(new int[] {tr,tc});
				}
			}
		}
		
		while(!melt.isEmpty()) {
			int[] m = melt.poll();
			if(map[m[0]][m[1]]==1) {
				map[m[0]][m[1]]=0;
				cheese--;
			}
			q.add(m);
		}
	}	
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		visited = new boolean[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) cheese++;
			}
		}
		
		////////////////입력 끝

		int cnt=0;
		int before1hour=0;
		q.add(new int[] {0,0});
		while(cheese>0) {
			before1hour = cheese;
			melt();
			cnt++;
		}
		
		System.out.println(cnt);
		System.out.println(before1hour);
		
	}	
}