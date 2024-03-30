package seoyoung.day0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12865_G5_평범한배낭_조서영 {
	static int N,K; //N:물품의 수, K:준서가 버틸 수 있는 무게
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[K+1];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			for(int cur=K; cur>=w; cur--) dp[cur]=Math.max(dp[cur], dp[cur-w]+v);
		}
		
		System.out.println(dp[K]);
	}
}
