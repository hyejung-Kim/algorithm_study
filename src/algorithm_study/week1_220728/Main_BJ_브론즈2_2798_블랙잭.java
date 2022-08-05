package algorithm_study.week1_220728;

import java.util.Scanner;

public class Main_BJ_브론즈2_2798_블랙잭 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] cards = new int[N];
		for(int i=0; i<N; i++) {
			cards[i] = sc.nextInt();
		}
		
		int[] arr = new int[3];
		combi(M,cards,arr,0,N,3,0);
		System.out.println(res);
		
	}
	
	static int res = 0;
	static void combi(int M, int[] cards, int[] arr, int index, int n, int r, int start) {
		// nCr = n-1Cr-1 + n-1Cr
		if(r==0) {
			//System.out.println(Arrays.toString(arr));
			int sum=0;
			for(int x:arr) {
				sum += x;
			}
			if(sum>M) return;
			else {
				res = Math.max(sum, res);
				return;
			}
		}
		else if(start==n) return;
		else {
			arr[index]=cards[start];
			combi(M, cards, arr, index+1, n, r-1, start+1);
			combi(M, cards, arr, index, n, r, start+1);
		}
	}
}