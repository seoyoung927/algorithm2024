import java.util.*;
import java.io.*;

// 다익스트라, 우선순위큐
public class Main_5972_G5_택배배송_다익스트라 {
    static int N, M; // N: 헛간, M: 길
    static List<Node>[] graph;

    static class Node implements Comparable<Node>{
        int n;
        int cost;

        public Node(int n, int cost){
            this.n = n;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other){
            return Integer.compare(this.cost, other.cost);
        }

        @Override
        public String toString(){
            return "n="+n+",cost="+cost;
        }
    }

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // 양방향 도로
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }
//        for(int i=1; i<N+1; i++){
//            System.out.println(graph[i]);
//        }

        // 2. 다익스트라 O(MlogN) -> 각 노드와 간선에 대해 큐의 삽입 및 삭제 작업이 O(logV)만큼 수행되기 때문이다.
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] minDist = new int[N+1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        pq.add(new Node(1, 0));
        minDist[1] = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            //System.out.println(cur);

            if(cur.n==N){
                System.out.println(cur.cost);
                break;
            }

            for(Node next : graph[cur.n]){
                if(cur.cost+next.cost>=minDist[next.n]) continue;
                pq.add(new Node(next.n, cur.cost+next.cost));
                minDist[next.n]=cur.cost+next.cost;
            }
        }
    }
}
