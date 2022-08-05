package algorithm_study.week2_220803;

import java.util.Scanner;

public class Main_BJ_브론즈2_2231_분해합 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int digit = (int) (Math.log10(N)+1); //자릿수 구하기
		int min = (N-(9*digit));
		
		for(int i=min; i<N; i++) {
			int result = i;
			int temp = i;
			for(int j=0; j<digit; j++) {
				result += temp%10;
				temp /= 10;
			}
			if(result==N) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
	}
}