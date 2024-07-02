package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//G3
//604ms
public class B1238 {
    static int N; //N명의 학생
    static int M; //M개의 단방향 도로
    static int X; //x번 마을에 모여서 파티
    static HashMap<Integer, List<Node>> map;
    static int[][] cost; //i->j로 가는 최소비용

    public static class Node implements Comparable<Node>{
        int end;
        int weight;

        public Node(int end, int weight){
            this.end=end;
            this.weight=weight;
        }

        @Override
        public int compareTo(Node other){
            return Integer.compare(this.weight, other.weight);
        }

        @Override
        public String toString(){
            return "[end="+end+", weight="+weight+"]";
        }
    }

    public static void main(String[] args) throws IOException {
        //1. 초기화 및 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        cost = new int[N+1][N+1];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Node node = new Node(e, w);
            if (!map.containsKey(s)) map.put(s, new ArrayList<>());
            map.get(s).add(node);
        }

        //2. 다익스트라
        for(int s=1; s<=N; s++){
            int[] dist = new int[N+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[s] = 0;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(s, 0));

            while(!pq.isEmpty()){
                Node cur = pq.poll();
                if(cur.weight>dist[cur.end]) continue;

                //인접한 정점과의 거리 update
                for(Node n : map.get(cur.end)){
                    int newDist = cur.weight+n.weight;
                    if(newDist<dist[n.end]){
                        pq.add(new Node(n.end, newDist));
                        dist[n.end] = newDist;
                    }
                }
            }

            for(int i=1; i<=N; i++){
                cost[s][i] = dist[i];
            }
        }

        //3. X로부터 학생들의 오고가는 시간 계산
        int[] total = new int[N+1];
        for(int i=1; i<=N; i++){
            total[i] = cost[i][X]+cost[X][i];
        }
        int answer = Integer.MIN_VALUE;
        for(int i=1; i<=N; i++){
            answer = Math.max(answer, total[i]);
        }
        System.out.println(answer);
    }
}
