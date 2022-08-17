package algorithm_study.week4_2208_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_골드5_21608_상어초등학교 {
	/**
	 * N*N 자리에 학생들을 배치하려고 한다.
	 * 상하좌우를 인접한 칸이라고 한다.
	 * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다. 
	 * 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다. 
	 * 3. 2를 만족하는 칸도 여러 개인 경우에는 행렬번호가 가장 먼저인 칸으로 자리를 정한다.
	 * 자리 배치 후 인접 칸에 좋아하는 학생이 0명이면 학생의 만족도는 0, 1명이면 1, 2명이면 10, 3명이면 100, 4명이면 1000이다.
	 * 학생의 만족도의 총 합을 구해보자.
	 **/

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int[] order = new int[N * N];
		studentFriends = new int[N * N+1][4];
		studentSeat = new int[N * N+1][2];
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			order[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				studentFriends[order[i]][j] = Integer.parseInt(st.nextToken());
			}
		}

		seat = new int[N][N];

		for (int i = 0; i < N * N; i++) {
			//착석
			int[] myseat = searchSeat(studentFriends[order[i]]);
			studentSeat[order[i]] = myseat;
			seat[myseat[0]][myseat[1]] = order[i];
		}

		for(int i=0; i<N; i++) System.out.println(Arrays.toString(seat[i]));
		
		System.out.println(getSatisfaction());

	}

	static int N;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우
	static int[][] seat;
	static int[][] studentFriends;
	static int[][] studentSeat;

	// 좋아하는 친구가 많이 인접한 자리를 찾고, 여러개일 경우 인접 빈칸 많은 자리 리턴
	static int[] searchSeat(int[] student) {
		int[][] friendseat = new int[N][N];	// 좋아하는 친구 몇명이 인접해있는지
		List<int[]> mySeatList = new ArrayList<>();	//친구가 많이 인접한 자리 목록
		List<int[]> blankSeatList = new ArrayList<>(); // 빈자리 목록
		int max=0;
		for(int i=0; i<N; i++) {
	        for(int j=0; j<N; j++) {
	        	if(seat[i][j]!=0) continue;	// 이미 누가 앉아있다면 다음자리 탐색
	        	
	        	int cnt=0;
	        	for(int[] d : delta) {
	        		int tr = i+d[0];
	        		int tc = j+d[1];
	        		if(0<=tr && tr<N && 0<=tc && tc<N) {
	        			for(int k=0; k<4; k++) {
	        				if(seat[tr][tc]==student[k]) cnt++;
	        			}
	        		}	
	        	}
	        	friendseat[i][j] = cnt;
	        	max = Math.max(max, cnt);
	        }
		}
		
		for(int i=0; i<N; i++) {
	        for(int j=0; j<N; j++) {
	        	if(seat[i][j]==0) {
	        		blankSeatList.add(new int[] {i,j});
	        		if(friendseat[i][j]==max) mySeatList.add(new int[] {i,j});
	        	}
	        }
		}
		
		if(mySeatList.size()==1) return mySeatList.get(0);
		else if(mySeatList.size()==0) return searchBlankSeat(blankSeatList);
		else return searchBlankSeat(mySeatList);
	}

	// 인접빈칸이 가장 많은 첫번째 자리 찾기
	static int[] searchBlankSeat(List<int[]> mySeatList) {
		int size = mySeatList.size();
		int maxR = mySeatList.get(0)[0], maxC = mySeatList.get(0)[1];
		int max = 0;
		for (int i = 0; i < size; i++) {
			int r = mySeatList.get(i)[0];
			int c = mySeatList.get(i)[1];

			int cnt = 0;
			for (int[] d : delta) {
				int tr = r + d[0];
				int tc = c + d[1];
				if (0 <= tr && tr < N && 0 <= tc && tc < N && seat[tr][tc] == 0)
					cnt++;
			}
			if (max < cnt) {
				if (cnt == 4)
					return new int[] { r, c };
				max = cnt;
				maxR = r;
				maxC = c;
			}

		}
		
		return new int[] {maxR, maxC};
	}
	
	//만족도 총합
	static int getSatisfaction() {
		int sum = 0;
		for(int i=1; i<=N*N; i++) {
			int cnt=0;
        	for(int[] d : delta) {
        		int tr = studentSeat[i][0]+d[0];
        		int tc = studentSeat[i][1]+d[1];
        		if(0<=tr && tr<N && 0<=tc && tc<N) {
        			for(int j=0; j<4; j++) {
        				if(seat[tr][tc]==studentFriends[i][j]) cnt++;
        			}
        		}	
        	}
        	sum += Math.pow(10, cnt-1);
		}
		
		return sum;
	}

}
