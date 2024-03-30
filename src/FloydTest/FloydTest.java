package seoyoung.day0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 모든 쌍에 대한 최단 경로
 * 
 * O(N^3)
 */
public class FloydTest {
	public static void main(String[] args) throws IOException {
		//1. 데이터 입력 받기
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int[][] map = new int[N][N];
		StringTokenizer st;
		final int Max = Integer.MAX_VALUE>>2;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(i!=j && map[i][j]==0) { //연결되지 않은 경로, 0으로 두면 최소값으로 처리
					map[i][j] = Max;
				}
			}
		}
		
		//프로이드+워셜
		//경유지 반복
		//출발지
		//도착지
		for(int k=0; k<map.length; k++) { //경유지 반복
			for(int i=0; i<N; i++) { //출발지
				if(i==k) continue; //출발지와 경유지가 같다면 갱신할 필요가 없음
				for(int j=0; j<N; j++) { //도착지
					if(i==j || k==j) continue; //출발지와 도착지가 같거나, 경유지와 도착지가 같으면 갱신할 필요가 없다.
					//직접 비용      경유지 비용
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k]+map[k][j];
					}
				}
			}
		}
		
		//출력
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}
