package seoyoung.day0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2573_G4_빙산_조서영 {
	static int N,M; //N,M: 이차원 배열의 크기
	static int[][] board;
	static int[] dr = {-1,1,0,0}; //상,하,좌,우
	static int[] dc = {0,0,-1,1}; //상,하,좌,우
	static int total;

	public static void melt() {
		int[][] tmp = new int[N][M];
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(board[r][c]==0) tmp[r][c]=0;
				else {
					int cnt = 0;
					for(int i=0; i<4; i++) {
						int nr = r+dr[i];
						int nc = c+dc[i];
						if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
						if(board[nr][nc]==0) cnt++;
					}
					int nextVal = board[r][c]-cnt;
					if(nextVal>0) {
						tmp[r][c]=nextVal;
					}else {
						total--;
						tmp[r][c]=0;
					}
				}
			}
		}
		
		board=tmp;
	}
	
	public static int check() {
		boolean[][] visited = new boolean[N][M];
		
		int cnt = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(!visited[r][c] && board[r][c]!=0) {
					dfs(visited,r,c);
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	public static void dfs(boolean[][] visited, int r, int c) {
		 visited[r][c]=true;
		 for(int i=0; i<4; i++) {
			 int nr = r+dr[i];
			 int nc = c+dc[i];
			 if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
			 if(visited[nr][nc]) continue;
			 if(board[nr][nc]==0) continue;
			 dfs(visited, nr, nc);
		 }
	}
	
	public static void main(String[] args) throws IOException {
		//0. 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		total = 0;
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if(board[r][c]!=0) total++;
			}
		}
		
		//1.
		int result = 0;
		int t = 0;
		while(total>0) {
			int remain = check();
			if(remain>=2) {
				result = t;
				break;
			}
			
			t++;
			melt();
		}
		
		//2. 출력
		System.out.println(result);
	}
}
