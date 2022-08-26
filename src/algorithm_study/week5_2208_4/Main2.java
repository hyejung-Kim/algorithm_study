package algorithm_study.week5_2208_4;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main2{
		
	static int N, M, K, C; //맵 크기, 박멸년수, 제초제 범위, 제초제 유지년수
	static int[][] map;
	static int[][] deltaTree = {{-1,0},{0,-1},{1,0},{0,1}};
	static int[][] deltaKill = {{-1,-1},{1,-1},{-1,1},{1,1}};
	static int result;
	
	private static int solve() {
		for(int m=1; m<=M; m++) {
			checkHerbcide(m);
			
			int[][] newMap = new int[N][N]; 
			int[][] cnt = new int[N][N];
			
			//1.인접한 네 개의 칸 중 나무가 있는 칸의 수만큼 나무가 성장합니다.
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]>0) {
						int r = i;
						int c = j;
						for(int k=0; k<4; k++) {
							int tr = r+deltaTree[k][0];
							int tc = c+deltaTree[k][1];
							if(checkRange(tr,tc)) {
								if(map[tr][tc]>0) map[r][c]++;	//인접칸에 나무가 있으면 -> 나무 성장
								else if(map[tr][tc]==0) cnt[i][j]++;	//인접칸이 빈칸이면 -> 나무 번식
							}
						}
					}
					
					newMap[i][j] = map[i][j];
				}
			}
			
			for(int i=0; i<N; i++) System.out.println(Arrays.toString(newMap[i]));
			System.out.println();
			
			//2.기존에 있었던 나무들은 인접한 4개의 칸 중 벽, 다른 나무, 제초제 모두 없는 칸에 번식을 진행합니다. 
			 for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]>0) {
						int a = map[i][j];
						for(int k=0; k<4; k++) {
							int tr = i+deltaTree[k][0];
							int tc = j+deltaTree[k][1];
							//이때 각 칸의 나무 그루 수에서 총 번식이 가능한 칸의 개수만큼 나누어진 그루 수만큼 번식이 되며, 나눌 때 생기는 나머지는 버립니다. 
							if(checkRange(tr,tc) && map[tr][tc]==0) newMap[tr][tc] += map[i][j]/cnt[i][j];
						}
					}
				}
			}
			 
			map = newMap;
			
			
			for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
			System.out.println();
			
			if(!kill(m)) break;
			
			for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
			System.out.println();

		}
		
		return result;
	}
	
	private static boolean kill(int m) {
		int max = 0;	//박멸시킬 나무 수 중 가장 큰 값
		List<int[]> maxPoints = new ArrayList<>();
		
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		if(map[i][j]<=0) continue;
        		int cnt = map[i][j];
        		
        		for(int[] d : deltaKill) {
            		int r = i;
            		int c = j;
        			for(int k=0; k<K; k++) {
        				int tr = r+d[0];
        				int tc = c+d[1];
        				if(checkRange(tr,tc)) {
        					r = tr;
        					c = tc;
	        				//전파되는 도중 벽이 있거나 나무가 아예 없는 칸이 있는 경우, 그 이후의 칸으로는 제초제가 전파되지 않습니다.
	        				if(map[tr][tc]==0 || map[tr][tc]<=-1) break;
	        				else if(map[tr][tc]>0) cnt += map[tr][tc];
        				}
        			}
        		}
        		
        		if(max<cnt) {
        			max = cnt;
        			maxPoints = new ArrayList<>();
        			maxPoints.add(new int[] {i,j});
        		}
        		else if(max==cnt) {
        			maxPoints.add(new int[] {i,j});
        		}
        	}
        }
        
        
       //만약 박멸시키는 나무의 수가 동일한 칸이 있는 경우에는 행이 작은 순서대로, 만약 행이 같은 경우에는 열이 작은 칸에 제초제를 뿌리게 됩니다.
        Collections.sort(maxPoints, (o1, o2)->{
        	if(o1[0]==o2[0]) return o1[1]-o2[1];
        	else return o1[0]-o2[0];
        });
        
        if(maxPoints.size()==0) return false;
        int[] mp = maxPoints.get(0);
        result += max;
        
        
        
		//3. 각 칸 중 제초제를 뿌렸을 때 나무가 가장 많이 박멸되는 칸에 제초제를 뿌립니다. 제초제를 뿌릴 때 4개의 대각선 방향으로 k칸만큼 전파되게 됩니다.
		int r = mp[0];
		int c = mp[1];
		map[r][c] = m*-10;
	
		for(int[] d : deltaKill) {
			r = mp[0];
			c = mp[1];
			for(int k=0; k<K; k++) {
				int tr = r+d[0];
				int tc = c+d[1];
				//전파되는 도중 벽이 있거나 나무가 아예 없는 칸이 있는 경우 그 이후의 칸으로는 제초제가 전파되지 않습니다.
				//제초제가 뿌려진 곳에 다시 제초제가 뿌려지는 경우에는 새로 뿌려진 해로부터 다시 c년동안 제초제가 유지됩니다.
				if(checkRange(tr,tc) && map[tr][tc]!=-1) {
					r = tr;
					c = tc;
					map[r][c] = m*-10; //-1인 벽과 구분하기 위해 10 곱해줌. 1년째에 뿌린 제초제는 -10, 2년째는 -20,...
					if(map[r][c]==0 || map[r][c]<-1) break;	//빈칸이거나 제초제가 뿌려진 칸인 경우 그 칸까지만 제초제 뿌림
				}
    			else break;
			}
		}
		
		return true;
	
	}
	
	private static boolean checkRange (int r, int c) {
		if(0<=r && r<N && 0<=c && c<N) return true;
		else return false;
	}
	
	//map[r][c]에 뿌려진 제초제가 아직 유효한지 체크해서 0으로 바꿔주는 메소드
	private static void checkHerbcide(int m) {
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	if(map[i][j] < -1 && Math.abs(map[i][j]/10)+C < m) map[i][j]=0;
		    }
		}

	}
	
	
	public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 맵 크기
        M = Integer.parseInt(st.nextToken()); // 박멸 년수
        K = Integer.parseInt(st.nextToken()); // 제초제 범위
        C = Integer.parseInt(st.nextToken()); // 제초제 유지년수
        
        map = new int[N][N];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
		
        System.out.println(solve());
        
	}

}