package Dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

/**
 * Dijkstra 알고리즘은 한 정점에서 다른 정점까지 가는 최단 경로를 구하는 알고리즘이다.
 * 시작 정점까지의 거리는 0으로, 나머지 정점까지의 거리는 무한대로 초기화한 후에
 * 시작 정점을 우선순위큐에 넣고, 큐에서 정점을 하나씩 꺼내 인접 정점까지의 거리를 update한다.
 * 우선순위큐가 빌 때까지 이 과정을 반복한다.
 * O(ElogN)
 */
public class B1504_G4_Dijkstra {
    static int N,E; //N: 정점의 개수, E: 간선의 개수
    static HashSet<Node>[] graph;

    static class Node implements Comparable<Node>{
        int v;
        int w;

        public Node(){}
        public Node(int v, int w){
            this.v=v;
            this.w=w;
        }

        @Override
        public int compareTo(Node other){
            return Integer.compare(this.w,other.w);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //1. 입력 및 초기화
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new HashSet[N+1]; //1~N까지의 정점
        for(int i=0; i<N+1; i++) graph[i] = new HashSet<>();
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            //양방향
            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        //2. 다익스트라 v1->v2
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[v1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(v1, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.w>dist[cur.v]) continue;

            for(Node next : graph[cur.v]){
                if(cur.w+next.w>dist[next.v]) continue;
                pq.add(new Node(next.v, cur.w+next.w));
                dist[next.v] = cur.w+next.w;
            }
        }
        int d1 = dist[v2];

        //출발정점(1)
        int[] dist2 = new int[N+1];
        Arrays.fill(dist2, Integer.MAX_VALUE);
        dist2[1] = 0;
        PriorityQueue<Node> pq2 = new PriorityQueue<>();
        pq2.add(new Node(1, 0));
        while(!pq2.isEmpty()){
            Node cur = pq2.poll();

            if(cur.w>dist2[cur.v]) continue;

            for(Node next : graph[cur.v]){
                if(cur.w+next.w>dist2[next.v]) continue;
                pq2.add(new Node(next.v, cur.w+next.w));
                dist2[next.v] = cur.w+next.w;
            }
        }

        //도착정점(N)
        int[] dist3 = new int[N+1];
        Arrays.fill(dist3, Integer.MAX_VALUE);
        dist3[N] = 0;
        PriorityQueue<Node> pq3 = new PriorityQueue<>();
        pq3.add(new Node(N, 0));
        while(!pq3.isEmpty()){
            Node cur = pq3.poll();

            if(cur.w>dist3[cur.v]) continue;

            for(Node next : graph[cur.v]){
                if(cur.w+next.w>dist3[next.v]) continue;
                pq3.add(new Node(next.v, cur.w+next.w));
                dist3[next.v] = cur.w+next.w;
            }
        }
        int d2 = Integer.MAX_VALUE;
        if(dist2[v1]!=Integer.MAX_VALUE && dist3[v2]!=Integer.MAX_VALUE) d2 = dist2[v1]+dist3[v2];
        if(dist2[v2]!=Integer.MAX_VALUE && dist3[v1]!=Integer.MAX_VALUE) d2 = Math.min(d2, dist2[v2]+dist3[v1]);

        //3. 출력
        int answer = -1;
        if(d1!=Integer.MAX_VALUE && d2!=Integer.MAX_VALUE) answer = d1+d2;
        System.out.println(answer);
    }
}
