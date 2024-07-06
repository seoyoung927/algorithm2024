package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_longest_common_subsequence {
    static String s1;
    static String s2;
    static int N; //s1의 길이
    static int M; //s2의 길이
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            //1. 입력 및 초기화
            st = new StringTokenizer(br.readLine());
            s1 = st.nextToken();
            s2 = st.nextToken();
            N = s1.length();
            M = s2.length();
            dp = new int[N+1][M+1];

            //2. dp
            for(int i=1; i<N+1; i++){
                for(int j=1; j<M+1; j++){
                    if(s1.charAt(i-1)==s2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1]+1;
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }

            //3. 출력
            System.out.printf("#%d %d%n",test_case,dp[N][M]);
        }

    }
}
