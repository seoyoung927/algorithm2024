package seoyoung.day0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//404ms
public class Main_17136_G2_색종이붙이기_조서영 {
	static int[][] board;
	static int[] cnts;
	static int ans;
	
	public static boolean check(int r, int c, int n) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int nr = r+i;
				int nc = c+j;
				if(nr<0 || nr>=10 || nc<0 || nc>=10) return false;
				if(board[nr][nc]!=1) return false;
			}
		}
		return true;
	}
	
	public static void change(int r, int c, int n) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int nr = r+i;
				int nc = c+j;
				board[nr][nc] = 0;
			}
		}
	}
	
	public static void reset(int r, int c, int n) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int nr = r+i;
				int nc = c+j;
				board[nr][nc] = 1;
			}
		}
	}
	
	public static void dfs(int r, int c, int cnt) {
		if(r>=9 && c>=10) {
			boolean flag = true;
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++){
					if(board[i][j]==1) {
						flag=false;
						break;
					}
				}
			}
			
			if(flag) ans = Math.min(ans, cnt);
			
			return;
		}
		if(cnt>=ans) return;	
		if(c>=10) {
			dfs(r+1, 0, cnt);
			return;
		}
		if(board[r][c]==1) {
			for(int i=5; i>0; i--) {
				if(check(r,c,i) && cnts[i]<5) {
					change(r,c,i);
					cnts[i]+=1;
					dfs(r,c+1,cnt+1);
					reset(r,c,i);
					cnts[i]-=1;
				}
			}
		}else {
			dfs(r,c+1,cnt);
		}
	}
	
	public static void main(String[] args) throws IOException {
		//1. 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		board = new int[10][10];
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		//2. 색종이 채우기
		cnts = new int[6];
		ans = Integer.MAX_VALUE;
		dfs(0,0,0);
		
		//3. 출력
		if(ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}
}
