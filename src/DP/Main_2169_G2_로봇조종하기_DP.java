package DP;

import java.util.*;
import java.io.*;

public class Main_2169_G2_로봇조종하기_DP {
    static int MIN_VAL = -987654321;
    static int N,M; // (1≤N, M≤1,000)
    static int[][] map;
    static int[][] dp;
    static int[] dr = {0,0,1}; //왼,오,아(위로는 이동불가)
    static int[] dc = {-1,1,0}; //왼,오,아(위로는 이동불가)

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        dp = new int[N+1][M+1];
        for(int r=1; r<N+1; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<M+1; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                dp[r][c] = MIN_VAL;
            }
        }

        // 2.
        dp[1][1] = map[1][1];
        for(int c=2; c<M+1; c++) dp[1][c] = dp[1][c] = map[1][c] + dp[1][c-1];
        for(int r=2; r<N+1; r++){
            int[][] tmp = new int[2][M+2];

            // 왼 -> 오
            tmp[0][0] = dp[r-1][1];
            for(int c=1; c<M+1; c++) tmp[0][c] = Math.max(tmp[0][c-1], dp[r-1][c]) + map[r][c];

            // 오 -> 왼
            tmp[1][M+1] = dp[r-1][M];
            for(int c=M; c>=1; c--) tmp[1][c] = Math.max(tmp[1][c+1], dp[r-1][c]) + map[r][c];

            for(int c=1; c<M+1; c++) dp[r][c] = Math.max(tmp[0][c], tmp[1][c]);
        }

        // 3. 출력
        System.out.println(dp[N][M]);
    }
}
