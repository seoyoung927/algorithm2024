package seoyoung.day0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//292ms
public class Main_17144_G4_미세먼지안녕_조서영 {
	static int R,C,T;
	static int[][] board;
	static int[][] cleaners;
	static int[] airCleaner1;
	static int[] airCleaner2;
	static int[] dr = {-1,1,0,0};	//상하좌우
	static int[] dc = {0,0,-1,1};	//상하좌우
	static int[] counterCircle = {3,0,2,1}; //우상좌하
	static int[] circle = {3,1,2,0}; //우하좌상
	
	public static int[][] spread() {
		int[][] tmp = new int[R][C];
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(board[r][c]!=0) {
					int cur = board[r][c]; //초기양
					int cnt = 0; //확산된 방향의 개수
					for(int i=0; i<4; i++) { //4방향으로 확산
						int nr = r+dr[i];
						int nc = c+dc[i];
						if(nr<0 || nr>=R || nc<0 || nc>=C) continue;
						if(board[nr][nc]==-1) continue;
						tmp[nr][nc]+=Math.floor(cur/5);
						cnt+=1;
					}
					tmp[r][c]+= cur-Math.floor(cur/5)*cnt;
				}
			}
		}
		return tmp;
	}
	
	public static void clean(int cleanerIdx, int[] directions, int r1, int r2) {
		int cr = cleaners[cleanerIdx][0]+dr[directions[0]];
		int cc = cleaners[cleanerIdx][1]+dc[directions[0]];
		int preVal = 0;
		int idx = 0;
		while(idx<4) {
			if(cr==cleaners[cleanerIdx][0] && cc==cleaners[cleanerIdx][1]) break;
			int nr = cr + dr[directions[idx]];
			int nc = cc + dc[directions[idx]];
			if(nr<r1 || nr>r2 || nc<0 || nc>=C) {
				idx+=1;
				continue;
			}
			int tmpVal = board[cr][cc];
			board[cr][cc]=preVal;
			preVal = tmpVal;
			cr = nr;
			cc = nc;
		}
	}
	
	public static void main(String[] args) throws IOException {
		//1. 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		cleaners = new int[2][2];
		for(int i=0; i<2; i++) Arrays.fill(cleaners[i], -1);
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				board[r][c]=Integer.parseInt(st.nextToken());
				if(board[r][c]==-1) {
					if(cleaners[0][0]==-1) cleaners[0] = new int[] {r,c};
					else cleaners[1] = new int[] {r,c};
				}
			}
		}
		

		//2. t초 동안 반복
		for(int t=0; t<T; t++) {
			//2-1. 확산
			board=spread();
			//2-3. 공기청정기2의 순환
			clean(0, new int[] {3,0,2,1}, 0, cleaners[0][0]);
			clean(1, new int[] {3,1,2,0}, cleaners[0][0], R-1);
		}
		
		
		//3. 남아있는 미세먼지의 양을 세고, 출력
		int result = 0;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(board[r][c]==-1) continue;
				result+=board[r][c];
			}
		}
		System.out.println(result);
	}
}
