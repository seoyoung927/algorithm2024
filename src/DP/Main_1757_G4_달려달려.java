package seoyoung.day0327;

import java.io.*;
import java.util.*;

// dp
public class Main_1757_G4_달려달려 {
    public static int N,M;
    public static int[] D;
    public static int[][] memo; //시간 n에 지침정도 m일 때 최대값

    public static void main(String[] args) throws Exception{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = new int[N];
        for(int i=0; i<N; i++){
            D[i] = Integer.parseInt(br.readLine());
        }
        memo = new int[N][M+1];
        memo[0][1] = D[0];

        // 2. dp - bottom up
        for(int n=1; n<N; n++){
            memo[n][0]=memo[n-1][0];

            for(int m=1; m<=M; m++){
                memo[n][m]=memo[n-1][m-1]+D[n];
            }

            for(int i=1; i<=M & i<=n; i++){
                memo[n][0]=Math.max(memo[n][0], memo[n-i][i]);
            }
        }

        // 3. 출력
        System.out.println(memo[N-1][0]);
    }
}
