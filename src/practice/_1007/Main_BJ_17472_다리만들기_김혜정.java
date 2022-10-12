package practice._1007;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17472_다리만들기_김혜정 {
	
//1. bfs로 섬 구분하기
//    1. 처음에 받을 때 1인 좌표들 받기
//    2. 방문하지 않았다면 bfs 돌리면서 섬번호 넣어주기
//
//2. 섬 잇기
//   중간에 섬이 있으면 다리 못놓음
//
//3. union-find 이용하여 최소신장트리 만들기
	
	private static int N, M, cnt=1;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[][] delta = {{0,1},{0,-1},{1,0},{-1,0}};
	private static List<List<int[]>> islands = new LinkedList<>();
	private static int[][] adjMatrix;
	private static List<int[]> adjList = new LinkedList<>();
	private static int[] parent;
	
	private static int findRoot(int a) {
		if(parent[a]==a) return a;
		else {
			return parent[a] = findRoot(parent[a]);
		}
	}
	
	private static void union(int a, int b) {
		int pA = findRoot(a);
		int pB = findRoot(b);
		if(pA<pB) parent[pB]=pA;
		else parent[pA]=pB;
	}
	
	private static void bfs(int[] start) {
		List<int[]> island = new LinkedList<>();
		islands.add(island);
		
		Queue<int[]> q = new LinkedList<>();
		q.add(start);
		island.add(start);
		visited[start[0]][start[1]]=true;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			map[p[0]][p[1]] = cnt;
			
			for(int[] d : delta) {
				int tr = p[0]+d[0];
				int tc = p[1]+d[1];
				if(0<=tr && tr<N && 0<=tc && tc<M && !visited[tr][tc] && map[tr][tc]==1) {
					int[] next = new int[] {tr,tc};
					visited[tr][tc] = true;
					island.add(next);
					q.add(next);
				}
			}
		}
		cnt++;
	}
	
	private static int kruskal() {
		Collections.sort(adjList, (o1, o2)->Integer.compare(o1[2], o2[2]));
		
		int sum = 0;
		for(int[] adj : adjList) {
			int from = adj[0];
			int to = adj[1];
			int distance = adj[2];
			
			if(findRoot(from)==findRoot(to)) continue;
			union(from, to);
			sum += distance;
			//System.out.println(Arrays.toString(adj));
		}
		
		//System.out.println(Arrays.toString(parent));
		for(int i=1; i<cnt-1; i++) {
			if(findRoot(i)!=findRoot(i+1)) return -1;
		}
		return sum;
	}
	
	
	private static void makeAdj() {
		for(int i=0; i<cnt; i++) Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
		makeBridge();
		
		//for(int i=0; i<cnt; i++) System.out.println(Arrays.toString(adjMatrix[i]));
		
		for(int i=0; i<cnt; i++) {
			for(int j=0; j<cnt; j++) {
				if(adjMatrix[i][j]!=Integer.MAX_VALUE) adjList.add(new int[] {i,j,adjMatrix[i][j]});
			}
		}
	}
	
	private static void makeBridge() {
		for(int i=1; i<cnt; i++) {
			for(int[] p : islands.get(i)) {
				int r = p[0];
				int c = p[1];
				for(int[] d : delta) {
					int tr = r;
					int tc = c;
					int bridge = 0;
					while(true) {
						tr += d[0];
						tc += d[1];
						if(!checkRange(tr,tc) || map[tr][tc]==i) break;
						if(map[tr][tc]==0) bridge++;
						else {
							if(bridge>1) adjMatrix[i][map[tr][tc]] = Math.min(adjMatrix[i][map[tr][tc]], bridge);
							break;
						}
						
					}
				}
			}
		}
	}
		
	private static boolean checkRange(int r, int c) {
		if(0<=r && r<N && 0<=c && c<M) return true;
		else return false;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		List<int[]> island = new LinkedList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) island.add(new int[] {i,j});
			}
		}
		
		islands.add(new LinkedList<int[]>());	//0번 섬(사용X)
		//섬에 번호 매겨주기
		for(int[] p : island) {
			if(!visited[p[0]][p[1]]) bfs(p);
		}
		
		//for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
		
		//집합 만들기
		parent = new int[cnt];
		for(int i=0; i<cnt; i++) {
			parent[i] = i; 
		}
		
		//만들 수 있는 다리 모두 놓기
		adjMatrix = new int[cnt][cnt];
		makeAdj();

		System.out.println(kruskal());
	}	
	
}