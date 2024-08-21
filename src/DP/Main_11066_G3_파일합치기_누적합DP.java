import java.util.*;
import java.io.*;

// 732ms
// 누적합 + dp
public class Main_11066_G3_파일합치기_누적합DP {
    static int K;
    static int[] file;
    static int[] sum;
    static int[][] dp;

    public static int dp(int start, int end){
        if(dp[start][end]!=0) return dp[start][end];

        if(start==end) return dp[start][end] = 0;
        if(start==end+1) return dp[start][end] = file[start]+file[end];

        int minVal = 98765432;
        for(int i=start; i<end; i++){
            minVal = Math.min(minVal, dp(start,i)+dp(i+1,end)+sum[i]-sum[start-1]+sum[end]-sum[i]);
        }

        return dp[start][end] = minVal;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            K = Integer.parseInt(br.readLine());
            file = new int[K+1];
            sum = new int[K+1];
            dp = new int[K+1][K+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<K+1; i++){
                file[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + file[i];
            }

            dp(1, K);

            System.out.println(dp[1][K]);
        }
    }
}
