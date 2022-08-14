package algorithm_study.week3;
import java.util.Scanner;

public class Main_브론즈2_13458_시험감독 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//시험장 수
		int N = sc.nextInt();
		//i번 시험장의 응시자 수
		int[] A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = sc.nextInt();
		}
		//총감독관 1명이 감시할 수 있는 응시자 수
		int B = sc.nextInt();
		//부감독관 1명(여러명 있어도 됨)이 감시할 수 있는 응시자 수
		int C = sc.nextInt();
		
		long result=0;
		for(int i=0; i<N; i++) {
			//총감독관 1명 필수
			int res = 1;
			//부감독관이 필요한 경우
			if(A[i]>B) {
				res += (A[i]-B)/C;
				if((A[i]-B)%C!=0)	res++;
			}
			result+=res;
		}
		
		System.out.println(result);
	}
}

// 틀린 이유 : 답이 int 범위를 벗어날 수도 있음!!!
