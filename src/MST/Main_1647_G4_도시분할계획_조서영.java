package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//메모리:343884KB, 시간:2612ms
public class Main_1647_G4_도시분할계획_조서영 {
    static int N; //집의 개수
    static int M; //길의 개수
    static Edge[] edgeList;

    static class Edge implements Comparable<Edge>{
        int from,to,weight;
        public Edge(int from, int to, int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }

    ///서로소 집합
    static int[] parents;
    public static void make(){
        parents=new int[N+1];
        for(int i=0;i<N;i++){
            parents[i]=i;
        }
    }
    public static int find(int a){
        if(a==parents[a]) return a;
        return parents[a]=find(parents[a]);
    }
    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot==bRoot) return false;
        parents[bRoot]=aRoot;
        return true;
    }
    /////////////

    ///크루스칼///
    public static long MSTKruskal(){
        make();
        Arrays.sort(edgeList);

        long result=0;
        int cnt=0;
        for(Edge edge : edgeList){
            if(!union(edge.from,edge.to)) continue; //사이클이 생성되면 다음으로
            if(cnt++==N-2) break;
            result+=edge.weight;
        }

        return result;
    }
    /////////////

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        edgeList = new Edge[M];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            edgeList[i]=new Edge(from,to,weight);
        }

        System.out.println(MSTKruskal());
    }
}

