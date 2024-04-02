package seoyoung.day0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//264ms
public class Main_4485_G4_녹색옷입은애가젤다지_조서영 {
	static int N;
	static int[][] board;
	static int[][] dist;
	static int[] dr = {-1,1,0,0}; //상하좌우
	static int[] dc = {0,0,-1,1}; //상하좌우
	
	static class Rupoor implements Comparable<Rupoor>{
		int r, c, point;
		public Rupoor(int r, int c, int point) {
			this.r=r;
			this.c=c;
			this.point=point;
		}
		@Override
		public int compareTo(Rupoor rupoor) {
			if(this.point < rupoor.point) return -1;
			return 1;
		}
		@Override
		public String toString() {
			return "Rupoor [r=" + r + ", c=" + c + ", point=" + point + "]";
		}
	}
	
	static void dijkstra() {
		dist[0][0]=board[0][0];
		PriorityQueue<Rupoor> pq = new PriorityQueue<>();
		pq.offer(new Rupoor(0,0,board[0][0]));
		while(!pq.isEmpty()) {
			Rupoor cur = pq.poll();
			int p = cur.point;
			if(dist[cur.r][cur.c]<p) continue;
			for(int i=0; i<4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				int npoint = dist[cur.r][cur.c]+board[nr][nc];
				if(npoint<dist[nr][nc]) {
					dist[nr][nc]=npoint;
					pq.offer(new Rupoor(nr,nc,npoint));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int idx=1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			
			board = new int[N][N];
			dist = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
				Arrays.fill(dist[i], 987654321);
			}
			dijkstra();
			System.out.println("Problem "+(idx++)+": "+dist[N-1][N-1]);
		}
	}
}
