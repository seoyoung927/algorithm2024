import java.util.*;
import java.io.*;

public class Solution_3282_01Knapsack {
    static int N;
    static int K;
    static Node[] node;
    static int[] dp;

    static class Node{
        int v; //부피
        int c;

        public Node() {}
        public Node(int v, int c){
            this.v=v;
            this.c=c;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            node = new Node[N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                node[i] = new Node(v,c);
            }

            dp = new int[K+1];
            for(int i=0; i<N; i++){
                int v = node[i].v;
                int c = node[i].c;

                for(int j=K; j>=v; j--){
                    dp[j] = Math.max(dp[j], c+dp[j-v]);
                }
            }

            System.out.printf("#%d %d%n",test_case,dp[K]);

        }
    }
}
