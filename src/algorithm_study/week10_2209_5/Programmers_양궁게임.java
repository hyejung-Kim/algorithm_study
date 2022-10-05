package algorithm_study.week10_2209_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Programmers_양궁게임 {

	public static void main(String[] args) {
		
		int n = 9;
		int[] info = {0,0,1,2,0,1,1,1,1,1,1};
		
		System.out.println(Arrays.toString(solution(n, info)));
	}
	
	static int[] solution(int n, int[] info) {       
        N = n;
        apeachInfo = info;
        dfs(0,0);
        
        //이길 수 없는 경우 return -1;
        if(minAnswers.size()==0) return new int[]{-1};
        //가장 낮은 점수를 더 많이 맞춘 순서로 정렬
        Collections.sort(minAnswers, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				for(int i=10; i>=0; i--) {
					if(o1[i]!=o2[i]) return o2[i]-o1[i];
				}
				
				return 0;
			}	
        });
        
        return minAnswers.get(0);
    }
	
	
	static int N, max, apeachScore;
	static int[] apeachInfo;
	static List<int[]> minAnswers = new ArrayList<>();
	static int[] ryanInfo = new int[11];
	
	static void dfs(int cnt, int arrow) {
		if(arrow>N) return;
		if(cnt>=10 || arrow==N) {
			
			ryanInfo[10] = N-arrow;
			
			//System.out.println("어피치: "+Arrays.toString(apeachInfo));
			//System.out.println("라이언: "+Arrays.toString(ryanInfo));
			
			int ryanScore = 0;
			int apeachScore = 0;
	        for(int i=0; i<11; i++){
	        	if (ryanInfo[i]>apeachInfo[i]) ryanScore += 10-i;
	        	else if(apeachInfo[i]!=0) apeachScore += 10-i;
	        	
	        }
	        
	        if(max < ryanScore-apeachScore) {
	        	minAnswers.clear();
	        	minAnswers.add(ryanInfo.clone());
	        	max = ryanScore-apeachScore;
	        }
	        else if(max == ryanScore-apeachScore) minAnswers.add(ryanInfo.clone());
	        
	        return;
		}
		
		//점수 먹는 경우
		ryanInfo[cnt]=apeachInfo[cnt]+1;
		dfs(cnt+1, arrow+ryanInfo[cnt]);
		
		//점수 안먹는 경우
		ryanInfo[cnt]=0;
		dfs(cnt+1, arrow);
		
	}
}
