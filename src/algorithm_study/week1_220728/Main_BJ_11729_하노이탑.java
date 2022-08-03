package algorithm_study.week1_220728;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main_BJ_11729_하노이탑 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		hanoi(N,1,2,3);
		
		s.insert(0, res+"\n");
		System.out.println(s);

	}
	
	static int res = 0;
	static StringBuilder s = new StringBuilder();
	static void hanoi(int n, int start, int temp, int end) {
		if(n==0) {
			return;
		}
		else {
			// 위에있는 것들 다 임시기둥으로 다 옮기기
			hanoi(n-1,start,end,temp);
			// 맨 밑에 있는 것 목적지로 옮기기
			res++;
			s.append(start+" "+end+"\n");
			// 임시기둥에 있던 것들 목적지로 옮기기
			hanoi(n-1,temp,start,end);
		}
	}
}