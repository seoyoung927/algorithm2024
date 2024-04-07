package seoyoung.day0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//407ms
public class Solution_2112_모의_보호필름_조서영 {
	static int D, W, K; //D: 보호 필름의 두께, W: 가로 크기, K: 합격 기준
	static int[][] board;
	static int[] isChanged;
	static int result;
	
	public static void change(int depth, int cnt) {
		if(depth>=D) {
			if(check()) {
				result = Math.min(result, cnt);
			}
			return;
		}
		
		if(cnt>=result) return;
		
		isChanged[depth]=-1;
		change(depth+1, cnt);
		isChanged[depth]=0;
		change(depth+1, cnt+1);
		isChanged[depth]=1;
		change(depth+1, cnt+1);
		
	}
	
	public static boolean check() {

		for(int c=0; c<W; c++) {
			int cnt = 1;
			int prev = board[0][c];
			if(isChanged[0]==0) prev = 0;
			else if(isChanged[0]==1) prev = 1;
			for(int r=1; r<D; r++) {
				if(cnt>=K) break;
				int cur = board[r][c];
				if(isChanged[r]==0) cur = 0;
				else if(isChanged[r]==1) cur = 1;
				
				if(cur==prev) {
					cnt++;
				}else {
					prev = cur;
					cnt = 1;
				}
			}
			if(cnt<K) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			board = new int[D][W];
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(K==1) {
				result=0;
			}else {
				isChanged = new int[D];
				result = Integer.MAX_VALUE;
				change(0,0);
			}
			
			System.out.printf("#%d %d%n",test_case,result);
		}
	}
}
