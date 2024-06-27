package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B11437_G3_LCA {
    static int N; //노드의 개수
    static int M;
    static List<Integer>[] graph;
    static int[] par;
    static int[] d;
    static boolean[] visited;

    public static int LCA(int v1, int v2){
        //1-1. v1을 더 깊게 만듦
        if(d[v1]<d[v2]){
            int tmp = v1;
            v1 = v2;
            v2 = tmp;
        }
        //1-2. 만약 v2가 1이라면
        if(v2==1) return 1;

        //2-1. v1과 v2의 깊이를 같게 만듦
        while(d[v1]!=d[v2]){
            v1 = par[v1];
        }
        //2-2. 이때, v1==v2라면 v2가 최소공통조상
        if(v1==v2) return v2;

        //3. 공통 부모를 찾을 때까지 위로
        while(v1!=v2){
            v1 = par[v1];
            v2 = par[v2];
        }
        return v2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //1. 입력 및 초기화
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        par = new int[N+1];
        d = new int[N+1];
        visited = new boolean[N+1];
        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();

        for(int i=1; i<=N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        //2. BFS로 트리 만들기 O(N)
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        visited[1] = true;
        d[1] = 0;
        while(!q.isEmpty()){
            int cur = q.poll();

            for(int c : graph[cur]){
                if(visited[c]) continue;
                q.add(c);
                visited[c]=true;
                par[c] = cur;
                d[c] = d[cur]+1;
            }
        }

        //3. LCA 트리가 선형 형태면 높이 N-1 O(NM)? 그럼 시간초과 아닌가..
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            System.out.println(LCA(v1,v2));
        }
    }
}
