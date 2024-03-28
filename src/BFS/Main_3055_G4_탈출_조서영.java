package seoyoung.day0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3055_G4_탈출_조서영 {
	static int R,C;
	static int[][] board;
	static int[] dr = {-1,1,0,0}; //상하좌우
	static int[] dc = {0,0,-1,1}; //상하좌우
	static int startR, startC;
	static int endR, endC;
	static int result;
	
	static class Point{
		int r,c,t;
		
		public Point(int r, int c, int t) {
			this.r=r;
			this.c=c;
			this.t=t;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		Queue<Point> queue = new ArrayDeque<>();
		for(int r=0; r<R; r++) {
			String tmp = br.readLine();
			for(int c=0; c<C; c++) {
				board[r][c]=tmp.charAt(c);
				if(board[r][c]=='*') {
					queue.add(new Point(r,c,0));
					board[r][c]=0;
				}else if(board[r][c]=='.'){
					board[r][c]=98765432;
				}else if(board[r][c]=='S'){
					board[r][c]=-1;
					startR=r;
					startC=c;
				}else if(board[r][c]=='D') {
					board[r][c]=-1;
					endR=r;
					endC=c;
				}else if(board[r][c]=='X') {
					board[r][c]=-2;
				}
			}
		}
		
		//물이차는 시간 board에 표시
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nextR = cur.r+dr[i];
				int nextC = cur.c+dc[i];
				
				if(nextR<0 || nextR>=R || nextC<0 || nextC>=C) continue;
				if(board[nextR][nextC]!=98765432) continue;
				
				board[nextR][nextC] = cur.t+1;
				queue.add(new Point(nextR, nextC, cur.t+1));
			}
		}
		
//		for(int r=0; r<R; r++) {
//			System.out.println(Arrays.toString(board[r]));
//		}
		
		//start 지점부터 이동
		queue.clear();
		queue.add(new Point(startR, startC, 0));
		result = -1;
		boolean[][] visited = new boolean[R][C];
		visited[startR][startC]=true;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(cur.r==endR && cur.c==endC) {
				result = cur.t;
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nextR = cur.r+dr[i];
				int nextC = cur.c+dc[i];
				
				if(nextR<0 || nextR>=R || nextC<0 || nextC>=C) continue;
				if(cur.t+1>=board[nextR][nextC] && board[nextR][nextC]!=-1) continue; //이미 물이 
				if(board[nextR][nextC]==-2) continue;
				if(visited[nextR][nextC]) continue;
				
				visited[nextR][nextC]=true;
				queue.add(new Point(nextR, nextC, cur.t+1));
			}
		}
		
		if(result==-1) System.out.println("KAKTUS");
		else System.out.println(result);
	}
}
