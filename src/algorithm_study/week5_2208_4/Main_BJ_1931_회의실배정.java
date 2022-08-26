package algorithm_study.week5_2208_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_BJ_1931_회의실배정 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int[][] meeting = new int[N][2];
        for(int i =0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	meeting[i][0] = Integer.parseInt(st.nextToken());
        	meeting[i][1] = Integer.parseInt(st.nextToken());
        }
        
        //끝나는 시간 오름차순 정렬
        Arrays.sort(meeting, Comparator.comparingInt(o->o[1]));
        int cnt=1;
        int temp = meeting[0][1];
        for(int i =1; i<N; i++) {
        	if(meeting[i][0]>=temp) {
        		cnt++;
        		temp = meeting[i][1];
        	}
        }
        
        System.out.println(cnt);
	}

}
