package seoyoung.day0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5643_D4_키순서_조서영 {
	static int N,M; //N: 학생들의 수, M: 두 학생들의 수를 비교한 횟수
	static int[][] dist;
	static final int INF = Integer.MAX_VALUE >> 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			//1. 입력 및 초기화
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			dist = new int[N+1][N+1];
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				dist[a][b] = 1; //a보다 b가 키가 크다.
			}
			
			//2. 플로이드-워샬
			for(int k=1; k<N+1; k++) {
				for(int i=1; i<N+1; i++) {
					if(i==k) continue;
					for(int j=1; j<N+1; j++) {
						if(i==j || k==j) continue;
						if(dist[j][i]==1 && dist[i][k]==1) {
							dist[j][k]=1;
						}
					}
				}
			}
			
			//3. 
			int ans = 0;
			for(int i=1; i<N+1; i++) {
				int cnt = 0;
				for(int j=1; j<=N; j++) {
					if(dist[i][j]==1) cnt++;
					if(dist[j][i]==1) cnt++;
				}
				if(cnt==N-1) ans++;
			}
			
			System.out.printf("#%d %d%n", test_case, ans);
			
			
		}
	}
}

