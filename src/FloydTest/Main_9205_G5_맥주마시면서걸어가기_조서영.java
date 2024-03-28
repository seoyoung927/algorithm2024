package seoyoung.day0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_9205_G5_맥주마시면서걸어가기_조서영 {
	static int t; //테스트 케이스 개수
	static int n; //편의점 개수
	static Point[] points;
	static int[][] adjMap;
	
	static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
	  
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine());
		for(int test_case=0; test_case<t; test_case++) {
			//1. 입력
			n = Integer.parseInt(br.readLine());
			points = new Point[n+2];
			for(int i=0; i<n+2; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				points[i] = new Point(r,c);	
			}
			
			//2. 거리 계산
			adjMap = new int[n+2][n+2];
			for(int i=0; i<n+2; i++) {
				for(int j=0; j<n+2; j++) {
					int dist = Math.abs(points[i].r-points[j].r)+Math.abs(points[i].c-points[j].c);
					if(dist>1000) adjMap[i][j]=98765432;
					else adjMap[i][j]=dist;
				}
			}
			
			for(int k=0; k<n+2; k++) {
				for(int i=0; i<n+2; i++) {
					if(i==k) continue; //출발지와 경유지가 같다면 갱신할 필요가 없음
					for(int j=0; j<n+2; j++) {
						if(i==j || k==j) continue; //출발지와 도착지가 같거나, 경유지와 도착지가 같으면 갱신할 필요가 없다.
						
						//직접 비용      경유지 비용
						if(adjMap[i][j] >= adjMap[i][k] + adjMap[k][j]) {
							adjMap[i][j] = adjMap[i][k] + adjMap[k][j];
						}
					}
				}
			}

			if(adjMap[0][n+1]!=98765432) System.out.println("happy");
			else System.out.println("sad");
		}
	}

}
