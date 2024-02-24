package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//메모리:49648KB, 시간:632ms
public class Main_1197_G4_최소스패닝트리_KRUSKAL_조서영 {
    static int V; //정점의 개수
    static int E; //간선의 개수
    static Edge[] edgeList; //간선리스트

    ///서로소집합/////////////////////
    static int[] parents; //서로소 집합

    public static void make() {
        parents = new int[V+1];
        for(int i=1;i<V+1;i++){
            parents[i] = i;
        }
    }
    public static int find(int a){
        if(a==parents[a]) return a;
        return parents[a]=find(parents[a]);
    }
    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot==bRoot) return false; //트리를 합칠 수 없음
        parents[bRoot] = aRoot;
        return true;
    }
    ///////////////////////////////

    static class Edge implements Comparable<Edge>{
        int from,to,weight;

        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    public static int MSTKruskal(){
        make();

        Arrays.sort(edgeList);

        int result = 0;
        int cnt = 0;
        for(Edge edge : edgeList){
            if(!union(edge.from,edge.to)) continue;
            result += edge.weight;
            if(++cnt==V-1) break; //최소 신장 트리 완성
        }

        return cnt==V-1?result:-1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edgeList = new Edge[E];

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList[i]=new Edge(from,to,weight);
        }

        System.out.println(MSTKruskal());
    }
}
