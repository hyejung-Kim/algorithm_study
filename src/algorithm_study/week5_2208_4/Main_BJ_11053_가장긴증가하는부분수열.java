package algorithm_study.week5_2208_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_11053_가장긴증가하는부분수열 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int[] num = new int[N];
        int[] dp = new int[N];
        int max = 1;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	num[i] = Integer.parseInt(st.nextToken());
        	dp[i] = 1; //최소
        	for(int j=0; j<i; j++) {
        		if(num[j]<num[i] && dp[i]<dp[j]+1) {
        			dp[i] = dp[j]+1;
        			max = Math.max(max, dp[i]);
        		}
        	}
        }
        
        System.out.println(max);
	}

}

//문제를 잘못 이해
//1423 수열이 있다면 가장 긴 증가하는 부분 수열은 123이다
//DP로 풀어야 함