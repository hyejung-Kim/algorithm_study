package algorithm_study.week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_실버4_10866_덱_김혜정 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		MyDeque d = new MyDeque();
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			switch(command) {
			case "push_front":
				d.pushFront(Integer.parseInt(st.nextToken()));
				break;
			case "push_back":
				d.pushBack(Integer.parseInt(st.nextToken()));
				break;
			case "pop_front":
				bw.write(String.valueOf(d.popFront())+"\n");
				break;
			case "pop_back":
				bw.write(String.valueOf(d.popBack())+"\n");
				break;
			case "size":
				bw.write(String.valueOf(d.size())+"\n");
				break;
			case "empty":
				bw.write(String.valueOf(d.empty())+"\n");
				break;
			case "front":
				bw.write(String.valueOf(d.front())+"\n");
				break;
			case "back":
				bw.write(String.valueOf(d.back())+"\n");
				break;	
			}		
		}
		
		bw.flush();
		bw.close();	
		
	}
	
}


class MyDeque {
	
	LinkedList<Integer> list  = new LinkedList<>();
	
	public void pushFront(int i) {
		list.add(0, i);
	}
	public void pushBack(int i) {
		list.add(i);
	}
	
	public int popFront() {
		if(list.size()==0) return -1;
		else {
			int res = list.get(0);
			list.remove(0);
			return res;
		}
	}
	public int popBack() {
		if(list.size()==0) return -1;
		else {
			int res = list.get(list.size()-1);
			list.remove(list.size()-1);
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


