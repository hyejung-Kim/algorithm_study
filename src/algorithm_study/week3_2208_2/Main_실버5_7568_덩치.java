package algorithm_study.week3_2208_2;
import java.util.Scanner;

public class Main_실버5_7568_덩치 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		//몸무게, 키
		int[][] person = new int[N][2];
		
		for(int i=0; i<N; i++) {
			person[i][0] = sc.nextInt();
			person[i][1] = sc.nextInt();
		}

		
		for(int i=0; i<N; i++) {
			int rank = 1;
			for(int j=0; j<N; j++) {
				if(person[j][0]>person[i][0] && person[j][1]>person[i][1]) {
					rank++;
				}
			}
			System.out.print(rank+" ");
		}
	}
}

// 오래걸린 이유 : 문제 파악을 잘못함
// 몸무게와 키가 모두 다 커야 큰 덩치인데 하나 같고 하나 큰 경우도 큰 덩치라고 잘못 해석