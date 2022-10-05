package practice._1005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1194_달이차오른다가자_김혜정 {
	
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static int N,M;
	static char[][] map;
	static boolean[][][] visited;
	
	static int solve(int startR, int startC) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(startR,startC,0,0));
		visited[startR][startC][0] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int[] d : delta) {
				int tr = p.r + d[0];
				int tc = p.c + d[1];
				int tkey = p.key;
				
				if(0<=tr && tr<N && 0<=tc && tc<M && !visited[tr][tc][tkey]) {
					//출구
					if(map[tr][tc]=='1') return p.cnt+1;
					//벽
					else if(map[tr][tc]=='#') continue;
					//키
					else if('a'<=map[tr][tc] && map[tr][tc]<='f') {
						int k = map[tr][tc]-'a';
						//키 추가
						tkey = tkey | 1<<k;
					}
					//문
					else if('A'<=map[tr][tc] && map[tr][tc]<='F') {
						int k = map[tr][tc]-'A';
						//키가 없으면 (비트마스킹 &연산 결과가 0이면) continue
						if((tkey & 1<<k)==0) continue; 
					}
					
					q.add(new Point(tr,tc,tkey,p.cnt+1));
					visited[tr][tc][tkey]=true;
				}
			}
		}
		
		return -1;	
	}
	
	static class Point{
		int r;
		int c;
		int key;
		int cnt;
		
		Point(){}
		public Point(int r, int c, int key, int cnt) {
			this.r = r;
			this.c = c;
			this.key = key;
			this.cnt = cnt;
		}
		
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int startR=0, startC=0;
		map = new char[N][M];
		visited = new boolean[N][M][1<<6];
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]=='0') {
					startR = i;
					startC = j;
				}
			}
		}
		
		System.out.println(solve(startR, startC));
	}	
	

}