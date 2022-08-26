package algorithm_study.week5_2208_4;

import java.util.Scanner;

public class Main_BJ_2914_크로아티아알파벳 {
	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
        String alpha = sc.nextLine();
        String[] cro = {"lj","nj","c=","c-","dz=","d-","s=","z="};
        
        for(int i=0; i<cro.length; i++) {
        	alpha = alpha.replace(cro[i], "A");
        }
        System.out.println(alpha.length());
		
	}

}
