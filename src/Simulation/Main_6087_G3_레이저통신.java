package Simulation;

import java.util.*;
import java.io.*;

public class Main_6087_G3_레이저통신 {
	static int H,W;
	static char[][] map;
	static List<Point> point;
	static int[] dr = {-1,1,0,0}; // 상,하,좌,우
	static int[] dc = {0,0,-1,1}; // 상,하,좌,우
	static int answer;
	
	static class Point{
		int r,c;
		int d; //direction
		int cnt;
		
		public Point() {}
		
		public Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
		
		public Point(int r, int c, int d, int cnt) {
			this.r=r;
			this.c=c;
			this.d=d;
			this.cnt=cnt;
		}
		
		@Override
		public String toString() {
			return "r="+r+",c="+c+",d="+d+",cnt="+cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		point = new ArrayList<>();
		int[][][] visited = new int[H][W][4];
		answer = Integer.MAX_VALUE;
		
		for(int r=0; r<H; r++) {
			String str = br.readLine();
			for(int c=0; c<W; c++) {
				map[r][c] = str.charAt(c);
				if(map[r][c] == 'C') {
					point.add(new Point(r,c));
				}
				Arrays.fill(visited[r][c], Integer.MAX_VALUE);
			}
		}
		
		// 2. 탐색
		int startR = point.get(0).r;
		int startC = point.get(0).c;
		int endR = point.get(1).r;
		int endC = point.get(1).c;
		Queue<Point> q = new ArrayDeque<>();		
		q.add(new Point(startR, startC, -1, -1));
		while(!q.isEmpty()) {
			Point cur = q.poll();
			//System.out.println(cur);
			int curR = cur.r;
			int curC = cur.c;
			int curD = cur.d;
			int curCnt = cur.cnt;
		
			if(curR == endR && curC == endC) {
				answer = Math.min(answer,  curCnt);
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int nextR = curR + dr[i];
				int nextC = curC + dc[i];
				int nextCnt = curCnt;
				if(curD != i) nextCnt++;
				
				if(nextR<0 || nextR>=H || nextC<0 || nextC>=W) continue;
				if(map[nextR][nextC]=='*') continue;
				if(nextCnt >= visited[nextR][nextC][i]) continue;
				
				q.add(new Point(nextR, nextC, i, nextCnt));
				visited[nextR][nextC][i] = nextCnt;
			}
		}
		
		// 3. 출력
		System.out.println(answer);
	}
}

