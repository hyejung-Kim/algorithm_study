package algorithm_study.week5_2208_4;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
		
	static int N, M, K, C; //맵 크기, 박멸년수, 제초제 범위, 제초제 유지년수
	static int[][] map;
	static int[][] herbcide;	//제초제
	static int[][] deltaTree = {{-1,0},{0,-1},{1,0},{0,1}};
	static int[][] deltaKill = {{-1,-1},{1,-1},{-1,1},{1,1}};
	static int result;
	
	
	private static void kill() {
		//제초제 유효기간 1년 down
		herbcideDown();
		
		//상하좌우에 번식할 수 있는 칸이 몇칸인지
		int[][] cnt = new int[N][N];
		
		//상하좌우에 나무가 있는 칸의 수만큼 나무 성장
		for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		// 나무가 있다면 상하좌우 체크
        		if(map[i][j]>0) {
	        		for(int[] d : deltaTree) {
	        			int tr = i + d[0];
	        			int tc = j + d[1];
	        			//map[tr][tc]가 범위 안이면
	        			if(checkRange(tr,tc)) {
	        				//인접칸에 나무가 있으면 나무수 +1
	        				if(map[tr][tc]>0) map[i][j]++;
	        				//인접칸이 빈칸이고 제초제도 없으면 번식할 수 있는 곳
	        				else if(map[tr][tc]==0 && herbcide[tr][tc]==0) cnt[i][j]++;
	        			}
	        			
	        		}
        		}
        	}
		}
		
		//상하좌우 중 빈칸이고 제초제가 없는 칸에 번식
		int[][] newMap = new int[N][N];		//번식이 동시에 일어나므로 새로운 맵에 저장
		for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		if(map[i][j]>0) {
        			newMap[i][j] = map[i][j];
        			for(int[] d : deltaTree) {
        				int tr = i + d[0];
	        			int tc = j + d[1];
	        			//map[tr][tc]가 범위 안이고 빈칸이며 제초제가 뿌려져있지 않을 때 번식
	        			if(checkRange(tr,tc) && map[tr][tc]==0 && herbcide[tr][tc]==0) {
	        				newMap[tr][tc] += map[i][j]/cnt[i][j];
	        			}
        			}
        		}
        	}
		}
		map = newMap;
		
		//박멸되는 나무 수 중 가장 큰 값과 자리 구하기
		int[][] killCount = new int[N][N];
		int max = 0;
		for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		int killcnt = 0;
        		//자기자리에 제초제 뿌리기
        		if(map[i][j]>0) killcnt += map[i][j];
				//빈칸이나 벽이면 그 칸까지만 제초제 뿌리고 멈춤
				if(map[i][j]<=0) continue;
        		//사방 대각선에 k만큼 제초제 뿌리기
    			for(int[] d : deltaKill) {
    				int r = i;
        			int c = j;
        			for(int k=0; k<K; k++) {
    					int tr = r+d[0];
    					int tc = c+d[1];
    					if(checkRange(tr,tc)) {
    						killcnt += map[tr][tc];
    						//빈칸이나 벽이면 그 칸까지만 제초제 뿌리고 멈춤
    						if(map[tr][tc]<=0) break;
    					}	
    				}
    			}
        		
        		killCount[i][j] = killcnt;
        		max = Math.max(max, killcnt);
        	}
		}
		
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(killCount[i]));
		}
		
		
		//가장 많이 박멸시키는 자리 중 행열번호가 작은 칸에 제초제 뿌리기
		for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		// 가장 많이 박멸시키는 칸이라면
        		if(killCount[i][j]==max) {
        			System.out.println(i+" "+j);
        			
            		//자기자리에 제초제 뿌리기
            		herbcide[i][j] = C;
    				//빈칸이나 벽이면 그 칸까지만 제초제 뿌리고 멈춤
    				if(map[i][j]<=0) break;
            		//사방 대각선에 k만큼 제초제 뿌리기
        			for(int[] d : deltaKill) {
        				int r = i;
            			int c = j;
            			for(int k=0; k<K; k++) {
        					int tr = r+d[0];
        					int tc = c+d[1];
        					if(checkRange(tr,tc)) {
        						herbcide[tr][tc] = C; //제초제 뿌리기
        						//빈칸이나 벽이면 그 칸까지만 제초제 뿌리고 멈춤
        						if(map[tr][tc]<=0) break;
        					}	
        				}
        			}
        			
        			//가장 먼저 만나는 max칸이 행렬번호가 가장 작으므로 break;
        			break;
        		}
        	}
		}
		
		result += max;	
	}
	
	
	private static void herbcideDown() {
		for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		//제초제가 뿌려져있으면 (양수라면) 유효기간 1년 깎기
        		if(herbcide[i][j]>0) herbcide[i][j]--;
        	}
		}
	}
	
	private static boolean checkRange(int r, int c) {
		if(0<= r && r < N && 0<= c && c < N) return true;
		else return false;
	}
	
	public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 맵 크기
        M = Integer.parseInt(st.nextToken()); // 박멸 년수
        K = Integer.parseInt(st.nextToken()); // 제초제 범위
        C = Integer.parseInt(st.nextToken()); // 제초제 유지년수
        
        map = new int[N][N];
        herbcide = new int[N][N];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        for(int i=0; i<M; i++) {
        	kill();
        }
        
        System.out.println(result);
        
	}

}