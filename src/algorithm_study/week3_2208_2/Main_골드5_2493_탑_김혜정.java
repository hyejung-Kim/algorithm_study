package algorithm_study.week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_골드5_2493_탑_김혜정 {

	public static void main(String[] args) throws Exception {
		/**
		 * 모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사하고, 
		 * 탑의 기둥 모두에는 레이저 신호를 수신하는 장치가 설치되어 있다. 
		 * 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능하다. 
		 * 예를 들어 높이가 6, 9, 5, 7, 4인 다섯 개의 탑이 수평 직선에 일렬로 서 있고, 
		 * 모든 탑에서는 주어진 탑 순서의 반대 방향(왼쪽 방향)으로 동시에 레이저 신호를 발사한다고 하자. 
		 * 그러면, 높이가 4인 다섯 번째 탑에서 발사한 레이저 신호는 높이가 7인 네 번째 탑이 수신을 하고, 
		 * 높이가 7인 네 번째 탑의 신호는 높이가 9인 두 번째 탑이, 
		 * 높이가 5인 세 번째 탑의 신호도 높이가 9인 두 번째 탑이 수신을 한다. 
		 * 높이가 9인 두 번째 탑과 높이가 6인 첫 번째 탑이 보낸 레이저 신호는 어떤 탑에서도 수신을 하지 못한다.
		 * 탑들의 개수 N과 탑들의 높이가 주어질 때, 
		 * 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내는 프로그램을 작성하라. 
		 */

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//탑의 수
		int N = Integer.parseInt(br.readLine());
		//탑들의 높이
		int[] tower = new int[N];
		int[] result = new int[N];
		
		Stack<Integer[]> stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			tower[i] = Integer.parseInt(st.nextToken());
		}
		
//		for(int i=N-1; i>=0; i--) {
//			for(int j=i-1; j>=0; j--) {
//				if(tower[i]<tower[j]) {
//					result[i]=j+1;
//					break;
//				}
//			}
//		}
		
		//
		
		for(int i=0; i<N; i++) {
			System.out.print(result[i]+" ");
		}
			
	}
}
