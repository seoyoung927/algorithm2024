package seoyoung.day0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//160ms
public class Main_11054_G4_가장긴바이토닉부분수열_조서영 {
	static int N; //수열 A의 크기
	static int[] inputs;
	static int[] dp1;
	static int[] dp2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		inputs = new int[N];
		dp1 = new int[N];
		dp2 = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) inputs[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			int curMax = 1;
			for(int j=0; j<i; j++) {
				if(inputs[i]>inputs[j]) {
					curMax = Math.max(curMax, dp1[j]+1);	
				}
			}
			dp1[i]=curMax;
		}
		for(int i=N-1; i>=0; i--) {
			int curMax = 1;
			for(int j=N-1; j>i; j--) {
				if(inputs[i]>inputs[j]) {
					curMax = Math.max(curMax, dp2[j]+1);	
				}
			}
			dp2[i]=curMax;
		}
		
		int maxNum = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			maxNum = Math.max(maxNum, dp1[i]+dp2[i]-1);
		}
		
		System.out.println(maxNum);
	}
}
