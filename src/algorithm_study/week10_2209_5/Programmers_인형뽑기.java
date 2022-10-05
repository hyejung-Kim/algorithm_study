package algorithm_study.week10_2209_5;

import java.util.Arrays;
import java.util.Stack;

public class Programmers_인형뽑기 {

	public static void main(String[] args) {
		
		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int moves[] = {1,5,3,5,1,2,1,4};
		
		System.out.println(solution(board, moves));
	}
	
	static int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        for(int i[] : board) System.out.println(Arrays.toString(i));
        
        
        Stack<Integer> stack = new Stack<>();
        for(int m : moves){
            for(int i=0; i<board.length; i++){
                if(board[i][m]!=0) {
                    if(!stack.isEmpty() && stack.peek()==board[i][m]){
                    	System.out.println(stack.pop());
                        answer++;
                        
                    } else stack.push(board[i][m]);
                    board[i][m] = 0;
                    break;
                }
            }
        }
        
        return answer;
    }

}
