package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//메모리: 32360KB, 시간: 996ms
public class Main_11066_G3_파일합치기_조서영 {
    static int N;
    static int[] files;
    static int[][] S;
    static int[][] DP;

    public static int dp(int start, int end){ //합치는 비용
        if(DP[start][end]!=-1) return DP[start][end];

        if(start==end) return DP[start][end]=0;
        if(end==start+1) return DP[start][end]=files[start]+files[end];

        int result = Integer.MAX_VALUE;
        for(int i=start;i<end;i++){
            result = Math.min(result,dp(start,i)+dp(i+1,end)+s(start,i)+s(i+1,end));
        }

        return DP[start][end]=result;
    }

    public static int s(int start, int end){
        if(S[start][end]!=-1) return S[start][end];

        if(start==end) return S[start][end]=files[start];

        return S[start][end]=files[start]+s(start+1,end);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=0;test_case<T;test_case++){
            N = Integer.parseInt(br.readLine());
            files = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<N+1;i++) files[i] = Integer.parseInt(st.nextToken());

            S = new int[N+1][N+1];
            for(int i=0;i<N+1;i++) Arrays.fill(S[i],-1);

            DP = new int[N+1][N+1];
            for(int i=0;i<N+1;i++) Arrays.fill(DP[i],-1);

            dp(1,N);

            System.out.println(DP[1][N]);
        }
    }
}
