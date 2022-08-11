package algorithm_study.week3_2208_2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main_BJ_실버5_7568_덩치 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] person = new int[N][3];
		int[][] rank = new int[N][2];
		
		for(int i=0; i<N; i++) {
			//번호, 몸무게, 키
			person[i][0] = i;
			person[i][1] = sc.nextInt();
			person[i][2] = sc.nextInt();		
		}
		
		//몸무게 내림차순으로 정렬
		Arrays.sort(person, Comparator.comparingInt((int[] o) -> o[1]).reversed());
		
		int r=0;
		int h=Integer.MAX_VALUE, w=Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			//몸무게, 키가 모두 작으면 등수++
			if(person[i][1]<w && person[i][2]<h) {
				r = i+1;
				w = person[i][1];
				h = person[i][2];
			}
			rank[i][0]=person[i][0];
			rank[i][1]=r;
		}
		
		//번호대로 다시 정렬
		Arrays.sort(rank, Comparator.comparingInt((int[] o) -> o[0]));
		for(int i=0; i<N; i++) {
			System.out.print(rank[i][1]+" ");
		}
	}
}