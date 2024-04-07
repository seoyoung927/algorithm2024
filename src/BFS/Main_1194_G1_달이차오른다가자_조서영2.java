package seoyoung.day0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//148ms
public class Main_1194_G1_달이차오른다가자_조서영2 {
	static int N,M;
	static char[][] board;
	static int startR, startC;
	static int[] dr = {-1,1,0,0}; //상하좌우
	static int[] dc = {0,0,-1,1}; //상하좌우
	static int[][][] visited;
	static int result;
	
	static class Point{
		int r,c,keys,cnt;
		
		public Point(int r, int c, int keys, int cnt) {
			this.r = r;
			this.c = c;
			this.keys = keys;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();
			for(int j=0; j<M; j++) {
				board[i][j] = tmp.charAt(j);
				if(board[i][j]=='0') {
					startR = i;
					startC = j;
				}
			}
		}
		
		visited = new int[N][M][1<<6];
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(startR, startC, 0, 0));
		visited[startR][startC][0] = 1;
		result = -1;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(board[cur.r][cur.c]=='1') {
				result = cur.cnt;
				break;
			}
			
			//열쇠를 줍는다.
			int newKeys = cur.keys;
			if((int)(board[cur.r][cur.c]-'a')>=0 && (int)(board[cur.r][cur.c]-'a')<6) newKeys |= (1<<(int)(board[cur.r][cur.c]-'a'));
			for(int i=0; i<4; i++) {
				int nextR = cur.r+dr[i];
				int nextC = cur.c+dc[i];
				//check
				if(nextR<0 || nextR>=N || nextC<0 || nextC>=M) continue;
				if(board[nextR][nextC]=='#') continue;
				if(visited[nextR][nextC][newKeys]==1) continue;
				if((int)(board[nextR][nextC]-'A')>=0 && (int)(board[nextR][nextC]-'A')<6 && (newKeys&(1<<(int)(board[nextR][nextC]-'A')))==0) continue;
				
				visited[nextR][nextC][newKeys]=1;
				queue.offer(new Point(nextR, nextC, newKeys, cur.cnt+1));
			}
		}
		
		System.out.println(result);
	}
}
