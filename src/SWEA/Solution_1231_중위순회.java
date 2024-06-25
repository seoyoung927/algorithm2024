package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1231_중위순회 {
    static int T = 10;
    static int N;
    static char[] tree;
    static String answer;

    public static void dfs(int cur){
        if(cur>N) return;

        dfs(2*cur);
        answer+=tree[cur];
        dfs(2*cur+1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int test_case=1; test_case<=T; test_case++){
            N = Integer.parseInt(br.readLine());
            tree = new char[N+1];
            for(int i=1; i<N+1; i++){
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                char c = st.nextToken().charAt(0);
                tree[idx] = c;
            }

            answer = "";
            dfs(1);
            System.out.printf("#%d %s%n",test_case,answer);
        }
    }
}
