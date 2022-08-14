package algorithm_study.week3;

import java.util.Scanner;

public class Main_실버3_14501_퇴사_김혜정 {
	
	static int N;
	static boolean isSelected[];
	static int[][] arr;
	static int max = 0;
	
	
	static void consulting(int cnt) {
		if(cnt>=N) {
			int sum = 0;
			for(int i=0; i<N; i++) {
				//N일을 넘어가면 상담 못함
				if(arr[i][0]>N-i) isSelected[i] = false;
				if(isSelected[i]) {
					sum += arr[i][1];
				}		
			}
			max = Math.max(sum, max);
			return;
		}
		
		//cnt번째 상담을 한다면 상담이 끝날때까지 다른 상담은 선택 못함! 
		isSelected[cnt] = true;
		consulting(cnt+arr[cnt][0]);
		
		//상담을 안한다면 다음날로 재귀 넘김
		isSelected[cnt] = false;
		consulting(cnt+1);
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		isSelected = new boolean[N];
		arr = new int[N][2];
		for(int i=0; i<N; i++) {
			//상담에 걸리는 시간
			arr[i][0] = sc.nextInt();
			//상담 완료시 수익
			arr[i][1] = sc.nextInt();
		}
		
		consulting(0);
		System.out.println(max);
		
	}
	
}
