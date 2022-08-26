package algorithm_study.week5_2208_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_골드5_11000_강의실배정 {
	public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        
        int[][] lecture = new int[N][2];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	lecture[i][0] = Integer.parseInt(st.nextToken());
        	lecture[i][1] = Integer.parseInt(st.nextToken());
        }
        
        //강의 시작시간순 정렬
        Arrays.sort(lecture, Comparator.comparingInt(o->o[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();	//강의실 비는 시간 우선순위 큐

        int cnt=1;
        pq.add(0);
        
        for(int[] l : lecture) {
        	if(l[0] < pq.peek()) cnt++;
        	else pq.poll();
        	pq.add(l[1]);
        }
        
        System.out.println(cnt);
        
	}
}
