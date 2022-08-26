package algorithm_study.week5_2208_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_2740_행렬곱셈 {
	public static void main(String[] args) throws Exception {

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] A = new int[N][M];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
        		A[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] B = new int[M][K];
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<K; j++) {
        		B[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int[][] AB = new int[N][K];
        for(int n=0; n<N; n++) {
        	for(int k=0; k<K; k++) {
        		int sum=0;
        		for(int m=0; m<M; m++) {
        			sum += A[n][m] * B[m][k];
        		}
        		AB[n][k] = sum;
        	}
        }
        
        for(int n=0; n<N; n++) {
        	for(int k=0; k<K; k++) {
        		bw.write(AB[n][k]+ " ");
        	}
        	bw.write("\n");
        }
        
        bw.flush();
        bw.close();
        
	}

}

// 행렬 곱셈법을 몰라서 찾아봄...