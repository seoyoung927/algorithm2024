import java.util.*;
import java.io.*;

// 472ms
public class Main_14567_G5_선수과목_위상정렬 {
    static int N, M;
    static List<Integer>[] graph;
    static int[] in;

    static class Node{
        int n;
        int depth;

        public Node(){}

        public Node(int n, int depth){
            this.n = n;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        in = new int[N+1];

        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            in[B]++;
        }

        // 2. 진입차수가 0인 지점을 Queue에 넣음
        Queue<Node> q = new ArrayDeque<>();
        int[] visited = new int[N+1];
        for(int i=1; i<=N; i++){
            if(in[i]==0){
                q.add(new Node(i, 1));
                visited[i]=1;
            }
        }
        while(!q.isEmpty()){
            Node cur = q.poll();
            int curN = cur.n;
            int curD = cur.depth;

            for(int next : graph[curN]){
                if(visited[next]!=0) continue;
                if(--in[next]==0){
                    q.add(new Node(next, curD+1));
                    visited[next]=curD+1;
                }
            }
        }

        //3.
        for(int i=1; i<=N; i++){
            System.out.print(visited[i]+" ");
        }
    }
}
