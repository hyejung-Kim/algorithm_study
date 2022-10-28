package practice._1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_보호필름_김혜정
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());	//보호필름두께
			W = Integer.parseInt(st.nextToken());	//가로 크기
			K = Integer.parseInt(st.nextToken());	//합격 기준
			
			film = new int[D][W];
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//입력 끝

			min = Integer.MAX_VALUE;
			if(K==1) min = 0;
			else check(film,0,0);
			
			System.out.println("#"+test_case+" "+min);
		}
	}
	
	static int D,W,K,min;
	static int[][] film;
	static final int A = 0;
	static final int B = 1;
	
	static void check(int[][] film, int num, int cnt) {
		if(num>=D) {
			
			A:for(int i=0; i<W; i++) {
				int count = 1;
				for(int j=1; j<D; j++) {
					if(film[j-1][i]!=film[j][i]) count = 1;
					else count++;
					if(count>=K) continue A;
				}
				return;
			}
		
			min = Math.min(min, cnt);
		}
		
		if(cnt>=min) return;
		
		//약품투입 X
		check(film, num+1, cnt);
		
		//배열 복사
		int[][] newFilm = new int[D][W];
		for(int i=0; i<D; i++) newFilm[i] = film[i].clone();
		
		//A약품 투입
		for(int j=0; j<W; j++) newFilm[num][j] = A;
		check(newFilm, num+1, cnt+1);
		
		//B약품 투입
		for(int j=0; j<W; j++) newFilm[num][j] = B;
		check(newFilm, num+1, cnt+1);
	}
}