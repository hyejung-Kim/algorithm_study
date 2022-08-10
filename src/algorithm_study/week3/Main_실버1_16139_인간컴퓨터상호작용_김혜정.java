package algorithm_study.week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_실버1_16139_인간컴퓨터상호작용_김혜정 {

	public static void main(String[] args) throws Exception {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		//[알파벳][누적합]
		//0번째 인덱스 사용 X
		int[][] sum = new int[26][s.length()+1];
		for(int i = 1; i<=s.length(); i++) {
			for(int j=0; j<26; j++) {
				sum[j][i] = sum[j][i-1];
			}
			sum[s.charAt(i-1)-'a'][i] = sum[s.charAt(i-1)-'a'][i-1] + 1;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0);
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int res = sum[a-'a'][end+1]-sum[a-'a'][start];
			bw.write(res+"\n");
		}
		
		
// 시간초과 코드	
//		StringBuilder sb = new StringBuilder(br.readLine());
//		
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int r = Integer.parseInt(st.nextToken());
//		
//		for(int i=0; i<r; i++) {
//			st = new StringTokenizer(br.readLine());
//			String a = st.nextToken();
//			int start = Integer.parseInt(st.nextToken());
//			int end = Integer.parseInt(st.nextToken());
//			String s = sb.substring(start, end+1);
//			int res = s.length() - s.replace(a, "").length();
//			bw.write(res+"\n");
//		}
		
		
		bw.flush();
		bw.close();
	}
}


//누적합이라는 개념을 떠올리지 못함!
