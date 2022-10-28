package practice._1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_3197_백조의호수_김혜정 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		water = new LinkedList<>();
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]=='L') {
					if(swan1==null) swan1 = new int[]{i,j};
					else swan2 = new int[]{i,j};
					map[i][j]='.';
				}
				if(map[i][j]=='.') {
					visited[i][j]=true;
					water.add(new int[] {i,j});
				}
				
			}
		}
		
		
	}	
	
	static int R, C;
	static char[][] map;
	static boolean visited[][];
	static int[] swan1, swan2;
	static Queue<int[]> water;
	
	static void bfs(int[] swan1, int[] swan2) {
		Queue<int[]> q = new LinkedList<>();
		while(!water.isEmpty()) q.add(water.poll());
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				
			}
			size++;
		}	
	}
}