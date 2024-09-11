package seoyoung.day0327;

import java.util.*;
import java.io.*;

public class Main_2533_G3_사회적관계망 {
    static int N;
    static List<Integer>[] tree;
    static int[][] memo; // 자식까지 isEarly의 개수
    static boolean[] visited;

    public static void go(int n){
        memo[n][0] = 0;
        memo[n][1] = 1;

        for(int next : tree[n]){
            if(visited[next]) continue;
            visited[next] = true;
            go(next);
            memo[n][1] += Math.min(memo[next][0], memo[next][1]);
            memo[n][0] += memo[next][1];
        }
    }

    public static void main(String[] args) throws IOException {
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        memo = new int[N+1][2];
        visited = new boolean[N+1];
        for(int i=0; i<=N; i++){
            tree[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tree[start].add(end);
            tree[end].add(start);
        }

        // 2.
        visited[1] = true;
        go(1);
        System.out.println(Math.min(memo[1][0], memo[1][1]));
    }
}
