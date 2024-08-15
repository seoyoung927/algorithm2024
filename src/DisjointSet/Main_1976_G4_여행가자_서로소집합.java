import java.util.*;
import java.io.*;

// 160ms, Disjoint Set
public class Main_1976_G4_여행가자_서로소집합 {
    static int N; // N: 도시의 수
    static int M; // M: 여행 계획에 속한 도시들의 수
    static int[][] graph;
    static int[] plan;
    static int[] parents;
    static int[] ranks;

    public static void make(){
        parents = new int[N+1];
        ranks = new int[N+1];
        for(int i=0; i<N+1; i++) {
            parents[i] = i;
            ranks[i] = 0;
        }
    }

    public static int find(int v){
        if(v==parents[v]) return v;
        return parents[v]=find(parents[v]);
    }

    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot==bRoot) return false;

        if(ranks[bRoot]<ranks[aRoot]){
            parents[bRoot] = aRoot;
        }else if(ranks[aRoot]<ranks[bRoot]){
            parents[aRoot] = bRoot;
        }else{
            parents[bRoot] = aRoot;
            ranks[aRoot]++;
        }
        return true;
    }

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new int[N+1][N+1];
        plan = new int[M];
        make();
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j]==1) union(i,j);
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            plan[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 대표자를 찾음, O(N)
        int[] rootParents = new int[N+1];
        for(int i=1; i<N+1; i++){
            rootParents[i] = find(parents[i]);
        }

        // 3. 여행 계획인 도시들의 대표자가 모두 같다면 yes, 아니면 no, O(M)
        boolean canTravel = true;
        for(int i=0; i<M-1; i++){
            if(rootParents[plan[i]]!=rootParents[plan[i+1]]){
                canTravel = false;
                break;
            }
        }
        if(canTravel) System.out.println("YES");
        else System.out.println("NO");
    }
}
