package algorithm_study.week1_220728;

import java.util.Scanner;

public class Main_BJ_골드5_2447_별찍기10 {
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		//N은 3의 거듭제곱
		//int N = sc.nextInt();
		
		printStar1(1,1,"*",27,0);
		System.out.println(sb);
	}
	
	static void printStar1(int r, int c, String star, int num, int cnt) {
		int n = num;
		if(c>num) {
			r++;
			c=1;
			if(num==cnt) {
				sb.append("\n");
				cnt=0;
			}
		}
		if(r>num)	return;

		if(r>num/3 && r<=num-num/3 && c>num/3 && c<=num-num/3) sb.append(" ");
		else if(r%3==2 && c%3==2) sb.append(" ");
		else sb.append(star);
		printStar1(r,c+1,star,num,cnt+1);
	}
}