package seoyoung.day0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_1194_G1_달이차오른다가자_조서영 {
	static int N,M;
	static char[][] board;
	static int[] dr = {1,-1,0,0}; //상, 하, 좌, 우
	static int[] dc = {0,0,-1,1}; //상, 하, 좌, 우
	static boolean[][][] visited;
	static int startR, startC;
	static int result;
	
	static class Node{
		int r, c, cnt, keys;
		public Node(int r, int c, int cnt, int keys) {
			this.r=r;
			this.c=c;
			this.cnt=cnt;
			this.keys=keys;
		}
		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", cnt=" + cnt + ", keys=" + keys + "]";
		}
	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visited = new boolean[N][M][1<<7];
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();
			for(int j=0; j<M; j++) {
				board[i][j]=tmp.charAt(j);
				if(board[i][j]=='0') {
					startR=i;
					startC=j;
				}
			}
		}
		
		
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(startR, startC, 0, 0));
		visited[startR][startC][0]=true;
		result=-1;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int keys = cur.keys;

			//System.out.println(cur);	
			
			if(board[cur.r][cur.c]=='1') {
				result=cur.cnt;
				break;
			}
			
			if(board[cur.r][cur.c]=='a'
					||board[cur.r][cur.c]=='b'
					||board[cur.r][cur.c]=='c'
					||board[cur.r][cur.c]=='d'
					||board[cur.r][cur.c]=='e'
					||board[cur.r][cur.c]=='f') {
				keys |= 1<<(board[cur.r][cur.c]-97); 
			}
			
			for(int i=0; i<4; i++) {
				int nextR = cur.r+dr[i];
				int nextC = cur.c+dc[i];
				if(nextR<0 || nextR>=N || nextC<0 || nextC>=M) continue;
				if(visited[nextR][nextC][keys] || board[nextR][nextC]=='#') continue;
				if((board[nextR][nextC]=='A'
						||board[nextR][nextC]=='B'
						||board[nextR][nextC]=='C'
						||board[nextR][nextC]=='D'
						||board[nextR][nextC]=='E'
						||board[nextR][nextC]=='F')
						&&((keys & (1<<(board[nextR][nextC]-65)))==0)) continue;
				visited[nextR][nextC][keys]=true;
				q.add(new Node(nextR, nextC, cur.cnt+1, keys));
			}
		}
		
		System.out.println(result);
	}
}