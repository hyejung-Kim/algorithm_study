package practice._1005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17143_낚시왕_김혜정 {
	
	static int[][] delta = {{},{-1,0},{1,0},{0,1},{0,-1}};
	static int R,C,M;
	static Map<Integer,Shark> sharks= new HashMap<>();
	static int[][] map;
	
	static int solve() {
		int sum = 0;
		
		//낚시왕은 가장 오른쪽 칸에 이동하면 이동을 멈춘다.
		//낚시왕이 오른쪽으로 한 칸 이동한다. 
		for(int i=1; i<=C; i++) {
			if(sharks.isEmpty()) break;
			
			//낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
			for(int j=1; j<=R; j++) {
				if(map[j][i]!=0) {
					int sharkNum = map[j][i];
					map[j][i] = 0;
					sum += sharks.get(sharkNum).size;
					sharks.remove(sharkNum);
					break;
				}
			}

			
			Queue<Integer> removeKeys = new LinkedList<>();
			int[][] newMap = new int[R+1][C+1];
			//상어가 이동한다.
			for(int key : sharks.keySet()) {
				
				Shark shark = sharks.get(key);
				int r = shark.r;
				int c = shark.c;
				int d = shark.dir;
				int speed = 0;
				
				//의미없는 왕복 제거
				if(d==1 || d==2) speed = shark.speed % ((R-1)*2);
				else if(d==3 || d==4) speed = shark.speed % ((C-1)*2);
				
				map[r][c]=0;
				for(int j=0; j<speed; j++) {
					int tr = r+delta[d][0];
					int tc = c+delta[d][1];
					
					//상어가 이동하려고 하는 칸이 격자판의 경계를 넘는 경우에는 방향을 반대로 바꿔서 속력을 유지한채로 이동한다.
					if(0>=tr || tr>R || 0>=tc || tc>C) {
						if(d==1 || d==3) shark.dir=d+1;
						else if(d==2 || d==4) shark.dir=d-1;
						d = shark.dir;
						tr = r+delta[d][0];
						tc = c+delta[d][1];
					}
					
					r=tr;
					c=tc;
				}
				
				shark.r = r;
				shark.c = c;
				
				//상어가 이동을 마친 후에 한 칸에 상어가 두 마리 이상 있을 수 있다. 이때는 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.
				if(newMap[r][c]==0) newMap[r][c]=key;
				else {
					Shark s2 = sharks.get(newMap[r][c]);
					if(shark.size > s2.size) {
						removeKeys.add(newMap[r][c]);
						newMap[r][c]=key;
					} else {
						removeKeys.add(key);
					}
				}
			}
			
			while(!removeKeys.isEmpty()) {
				int key = removeKeys.poll();
				sharks.remove(key);
			}
			
			map = newMap;
		}
		
		return sum;
		
	}
	
	static class Shark{
		int r;
		int c;
		int speed;
		int dir;
		int size;
		public Shark(int r, int c, int speed, int dir, int size) {
			super();
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks.put(i,new Shark(r,c,s,d,z));
			map[r][c] = i;
		}
		
		System.out.println(solve());
	}	
	

}