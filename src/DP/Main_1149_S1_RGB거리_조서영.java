package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//메모리: 14712KB, 시간: 132ms
public class Main_1149_S1_RGB거리_조서영 {
    static int N;
    static int[][] prices;
    static int[][] DP;

    public static int dp(int idx, int cIdx) {
        if(DP[idx][cIdx]!=-1) return DP[idx][cIdx];

        if(idx==1) return DP[idx][cIdx]=prices[idx][cIdx];

        if(cIdx==0) return DP[idx][0]=Math.min(prices[idx][0]+dp(idx-1,1),prices[idx][0]+dp(idx-1,2));
        else if(cIdx==1) return DP[idx][1]=Math.min(prices[idx][1]+dp(idx-1,0),prices[idx][1]+dp(idx-1,2));
        return DP[idx][2] = Math.min(prices[idx][2]+dp(idx-1,0),prices[idx][2]+dp(idx-1,1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        prices = new int[N+1][3];
        for(int i=1;i<N+1;i++) {
            st = new StringTokenizer(br.readLine());
            prices[i][0] = Integer.parseInt(st.nextToken());
            prices[i][1] = Integer.parseInt(st.nextToken());
            prices[i][2] = Integer.parseInt(st.nextToken());
        }

        DP = new int[N+1][3];
        for(int i=0;i<N+1;i++) Arrays.fill(DP[i], -1);

        int result = Math.min(dp(N,0),dp(N,1));
        result = Math.min(result, dp(N,2));

        System.out.println(result);
    }

}