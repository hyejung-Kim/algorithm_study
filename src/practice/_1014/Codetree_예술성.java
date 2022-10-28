package practice._1014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Codetree_예술성 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		color = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				color[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		setGroups();
		comb(0,0,0);
		
		rotate1(1);
		rotate1(2);
		rotate1(3);
		
		int sum = 0;
		for(int score : scores) sum += score;
		System.out.println(sum);
	}
	
	static int N;
	static int[][] delta = {{0,1},{0,-1},{1,0},{-1,0}};
	static int[][] color;
	static boolean[][] visited;
	static int[][] groupNum;
	static List<List<int[]>> groups;
	static int[] pick = new int[2];
	static int[] scores = new int[4];
	
	static void bfs(int r, int c) {
		int num = groups.size();
		List<int[]> g = new LinkedList<>();
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		visited[r][c] = true;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			g.add(p);
			groupNum[p[0]][p[1]] = num;
			for(int[] d : delta) {
				int tr = p[0]+d[0];
				int tc = p[1]+d[1];
				if(0<=tr && tr<N && 0<=tc && tc<N && !visited[tr][tc] && color[p[0]][p[1]]==color[tr][tc]) {
					q.add(new int[] {tr,tc});
					visited[tr][tc] = true;
				}
			}
		}
		
		groups.add(g);
	}
	
	static void setGroups() {
		visited = new boolean[N][N];
		groupNum = new int[N][N];
		groups = new LinkedList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) bfs(i,j);
			}
		}
	}
	
	static void comb(int cnt, int start, int scoreNum) {
		if(cnt>=2) {
			List<int[]> g1 = groups.get(pick[0]);
			List<int[]> g2 = groups.get(pick[1]);
			
			int num1 = color[g1.get(0)[0]][g1.get(0)[1]];
			int num2 = color[g2.get(0)[0]][g2.get(0)[1]];
			int adj = countAdj(pick[0],pick[1]);
			int score = (g1.size()+g2.size()) * num1 * num2 * adj;
			scores[scoreNum] += score;
			
			return;
		}
		
		for(int i=start; i<groups.size(); i++) {
			pick[cnt] = i;
			comb(cnt+1, i+1, scoreNum);
		}
	}
	
	
	static int countAdj(int n1, int n2) {
		int cnt = 0;
		List<int[]> g1 = groups.get(n1);
		for(int[] p : g1) {
			for(int[] d : delta) {
				int tr = p[0]+d[0];
				int tc = p[1]+d[1];
				if(0<=tr && tr<N && 0<=tc && tc<N && groupNum[tr][tc]==n2) cnt++;
			}
		}
		return cnt;
	}
	
	static void rotate1(int cnt) {
		int[][] newArr = new int[N][N];
		for(int i=0; i<N; i++) {			
			newArr[N/2][i] = color[i][N/2];
			newArr[N-1-i][N/2] = color[N/2][i];
		}
		
		rotate2(0,0,newArr);
		rotate2(0,N/2+1,newArr);
		rotate2(N/2+1,0,newArr);
		rotate2(N/2+1,N/2+1,newArr);
		
		color = newArr;
		setGroups();
		comb(0,0,cnt);
	}
	
	static void rotate2(int r, int c, int[][] arr) {
		int[][] newArr = new int[N/2][N/2];
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<N/2; j++) {
				newArr[i][j] = color[r+i][c+j];
			}
		}
		
		int[][] newArr2 = new int[N/2][N/2];
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<N/2; j++) {
				newArr2[j][i] = newArr[N/2-1-i][j];
			}
		}
		
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<N/2; j++) {
				arr[r+i][c+j] = newArr2[i][j];
			}
		}
	}
}
