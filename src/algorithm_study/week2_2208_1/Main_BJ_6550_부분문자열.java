package algorithm_study.week2_2208_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_6550_부분문자열 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		A:while (true) {
			String line = br.readLine();
			if (line == null || line.equals(""))	break;

			StringTokenizer st = new StringTokenizer(line);
			String s = st.nextToken();
			String t = st.nextToken();
			int cnt=0;
			for(int i = 0; i<t.length(); i++) {
				if(s.charAt(cnt)==t.charAt(i)) cnt++;
				if(cnt==s.length()) {
					sb.append("Yes\n");
					continue A;
				}
			}
			sb.append("No\n");
		}
		
		System.out.println(sb);
	}
}