package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 길이 K의 dp배열을 만들고
 * 물건을 순회하면서 각 물건별로 뒤에서부터(K에서부터 현재 물건 무게까지) dp배열의 값을 update
 * 이때, 뒤에서부터 update하는 이유는 앞에서부터 하면 중복되기 때문(즉, 중복을 없애기 위해서)
 */
public class Solution_D3_01Knapsack {
    static int N,K;
    static int[] dp;
    static int[][] product;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            //1. 입력 및 초기화
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            dp = new int[K+1];
            product = new int[N][2];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                product[i][0] = Integer.parseInt(st.nextToken()); //v
                product[i][1] = Integer.parseInt(st.nextToken()); //c
            }

            //2. dp 배열 update
            for(int i=0; i<N; i++){
                int w = product[i][0];
                int v = product[i][1];
                for(int j=K; j>=w; j--) dp[j] = Math.max(dp[j], v+dp[j-w]);
            }

            //3. 출력
            int answer = 0;
            for(int i=1; i<=K; i++) answer = Math.max(answer, dp[i]);
            System.out.printf("#%d %d%n",test_case,answer);
        }
    }
}
