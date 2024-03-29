package seoyoung.day0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1263_사람네트워크2_조서영 {
	static int N;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==0) map[i][j] = Integer.MAX_VALUE/4; 
				}
			}
			
			for(int k=0; k<N; k++) {
				for(int i=0; i<N; i++) { //출발지
					if(i==k) continue;
					for(int j=0; j<N; j++) {
						if(i==j || k==j) continue;
						if(map[i][j]>map[i][k]+map[k][j]) {
							map[i][j]=map[i][k]+map[k][j];
						}
					}
				}
			}
			
			
			int[] C = new int[N];
			int minNum = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				int s = 0;
				for(int j=0; j<N; j++) {
					if(i!=j) s+=map[i][j];
				}
				C[i]=s;
				minNum = Math.min(minNum, C[i]);
			}
			
			System.out.printf("#%d %d%n", test_case, minNum);
		}
		
	}
}
