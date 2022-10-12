package practice._1007;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_17471_게리맨더링_김혜정 {
	
	private static int N, check;
	private static int[] population;
	private static List<Integer>[] adjList;
	private static List<Integer> list1 = new LinkedList<>(); 
	private static List<Integer> list2 = new LinkedList<>(); 
	private static int min = Integer.MAX_VALUE;
	
	private static void dfs(int cnt) {
		if(cnt>N) {
			if(list1.isEmpty() || list2.isEmpty() || list1.size()+list2.size()!=N) return; 
			
			check=0;
			checkConnected(list1.get(0), list1, new boolean[N+1]);
			if(list1.size()>1 && check!=list1.size()) return;

			check=0;
			checkConnected(list2.get(0), list2, new boolean[N+1]);
			if(list2.size()>1 && check!=list2.size()) return;

			int sum1=0, sum2=0;
			for(int num : list1) sum1 += population[num];
			for(int num : list2) sum2 += population[num];
			min = Math.min(Math.abs(sum2-sum1), min);
			return;
		}
		
		//cnt 구역을 1번 선거구에 넣기
		list1.add(cnt);
		dfs(cnt+1);
		list1.remove(list1.size()-1);
		
		//cnt 구역을 2번 선거구에 넣기
		list2.add(cnt);
		dfs(cnt+1);
		list2.remove(list2.size()-1);
		
	}

	private static void checkConnected(int from, List<Integer> list, boolean[] visited) {
		if(check >= list.size()) return;
		
		for(int to : list) {
			if(!visited[to] && adjList[from].contains(to)) {
				visited[to] = true;
				check++;
				checkConnected(to, list, visited);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		population = new int[N+1];
		for(int i=1; i<=N; i++) population[i] = Integer.parseInt(st.nextToken());

		adjList = new List[N+1];
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while(st.hasMoreTokens()) adjList[i].add(Integer.parseInt(st.nextToken()));
		}
		
		dfs(1);
		if(min==Integer.MAX_VALUE) min=-1;
		System.out.println(min);
	}	
	

}