package algorithm_study.week3_2208_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_실버1_16926_배열돌리기1_김혜정 {
	static int N, M;
	
	// 우, 하, 좌, 상
	static int delta[][] = {{0,1},{1,0},{0,-1},{-1,0}};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for(int i =0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
			}
		}

		solve(arr,0,R);
		
		for(int i =0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void solve(int[][] arr, int depth, int R) {
		int n = N-depth*2;
		int m = M-depth*2;
		if(n<1 || m<1) return;
		
		Queue<Integer> q = new LinkedList<>();
		
		//배열에 있는 값 큐에 넣기
		int r=depth, c=depth, rd=0;
		for(int i=0; i<(n*2+(m-2)*2); i++) {
			q.offer(arr[r][c]);
			
			//한칸 앞 체크
			int nr = r + delta[rd][0];
			int nc = c + delta[rd][1];
			
			//경계를 넘으면 방향 변경
			if(nr<0+depth || nr>=N-depth || nc<0+depth || nc>=M-depth)	rd = (rd+1)%4;
			
			//한칸 앞으로 이동
			r += delta[rd][0];
			c += delta[rd][1];
		}
		
		//큐 돌리기
		for(int i=0; i<R % q.size(); i++) {
			q.offer(q.poll());
		}
		
		//큐에 있는 값 배열에 다시 넣기
		r=depth;
		c=depth;
		rd=0;
		while(!q.isEmpty()) {
			arr[r][c] = q.poll();
			
			int nr = r + delta[rd][0];
			int nc = c + delta[rd][1];
			
			if(nr<0+depth || nr>=N-depth || nc<0+depth || nc>=M-depth)	rd = (rd+1)%4;
			
			r += delta[rd][0];
			c += delta[rd][1];
		}
		solve(arr, depth+1, R);
	}
}
