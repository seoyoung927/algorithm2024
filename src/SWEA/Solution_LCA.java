package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_LCA {
    static int N;
    static List<Integer>[] graph;
    static int[][] parent;// i의 j번째 조상
    static int[] d;
    static boolean[] visit;

    public static void init(){
        graph = new ArrayList[100055];
        for(int i=0; i<100055; i++){
            graph[i] = new ArrayList<>();
        }
        d = new int[100055];
        visit = new boolean[100055];
    }

    public static int LCA(int x, int y, int cnt){
        if(x==y) return 0;
        //y가 더 깊게 만듦
        if(d[x]>d[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }
        if(parent[y][0]==x) return 1;
        //높이 맞추기
        for(int i=19; i>=0; i--){
            if(d[y]-d[x]>=(1<<i)){
                y = parent[y][i];
                cnt += (1<<i);
            }
        }
        //이제 높이가 같아져서
        if(parent[y][0]!=parent[x][0]){
            for(int i=19; i>=0; i--){
                if(parent[x][i]!=parent[y][i]){
                    cnt += 2*(1<<i);
                    x = parent[x][i];
                    y = parent[y][i];
                }
            }
        }
        if (parent[x][0] == parent[y][0]) {
            cnt += 2;
        }
        return cnt; // 추가된 반환문
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            // 1. 입력 및 초기화
            N = Integer.parseInt(br.readLine());
            graph = new List[N+1];
            for(int i=1; i<=N; i++){
                graph[i] = new ArrayList<>();
            }
            parent = new int[N+1][20];
            visit = new boolean[N+1];
            d = new int[N+1];
            d[1] = 0;
            st = new StringTokenizer(br.readLine());
            for(int i=2; i<=N; i++){
                int p = Integer.parseInt(st.nextToken());
                graph[p].add(i);
                parent[i][0]=p;
                d[i]=d[p]+1;
            }

            for(int y=1; y<20; y++){
                for(int x=1; x<=N; x++){
                    parent[x][y] = parent[parent[x][y-1]][y-1];
                }
            }

            //3. BFS
            long result = 0L;
            int prev = 1;
            Queue<Integer> q = new ArrayDeque<>();
            q.add(1);
            visit[1] = true;
            while(!q.isEmpty()){
                int cur = q.poll();
                //System.out.println("cur="+cur+",LCA="+LCA(prev,cur,0));
                result += LCA(prev, cur, 0);
                prev = cur;
                for(int n : graph[cur]){
                    if(visit[n]) continue;
                    q.add(n);
                    visit[n] = true;
                }
            }

            // 4. 출력
            System.out.printf("#%d %d%n", test_case, result);
        }
    }
}
