package algorithm_study.week3_2208_2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main_실버5_1361_그룹단어체커_김혜정 {
	public static void main(String[] args) throws Exception {
		
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		List<Character> list = new ArrayList<>();
		
		int cnt=0;
	 A: for(int i=0; i<N; i++) {
			list.clear();
			String s = "A"+sc.next();
			for(int j=1; j<s.length(); j++) {
				if(s.charAt(j)!=s.charAt(j-1)) {
					if(list.contains(s.charAt(j)))	continue A;
					else list.add(s.charAt(j));
				}								
			}
			cnt++;
		}
		System.out.println(cnt);
	}
	
}



