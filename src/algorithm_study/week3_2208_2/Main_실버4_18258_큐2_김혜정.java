package algorithm_study.week3_2208_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_실버4_18258_큐2_김혜정 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		MyQueue q = new MyQueue();
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			switch(command) {
			case "push":
				q.push(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				bw.write(String.valueOf(q.pop())+"\n");
				break;
			case "size":
				bw.write(String.valueOf(q.size())+"\n");
				break;
			case "empty":
				bw.write(String.valueOf(q.empty())+"\n");
				break;
			case "front":
				bw.write(String.valueOf(q.front())+"\n");
				break;
			case "back":
				bw.write(String.valueOf(q.back())+"\n");
				break;	
			}		
		}
		
		bw.flush();
		bw.close();	
		
	}
	
}


class MyQueue {
	
	LinkedList<Integer> list  = new LinkedList<>();
	
	public void push(int i) {
		list.add(i);
	}
	
	public int pop() {
		if(list.size()==0) return -1;
		else {
			int res = list.get(0);
			list.remove(0);
			return res;
		}
	}
	
	public int size() {
		return list.size();
		
	}
	
	public int empty() {
		if(list.size()==0) return 1;
		else return 0;
	}
	
	public int front() {
		if(list.size()==0) return -1;
		else return list.get(0);
	}
	
	public int back() {
		if(list.size()==0) return -1;
		else return list.get(list.size()-1);
	}
	
}


