package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1197_G4_최소스패닝트리_PRIM_조서영 {
    static int V; //정점의 개수
    static int E; //간선의 개수
    static int[][] adjMatrix; //인접행렬
    static boolean[] visited; //정점 방문여부 check
    static int[] minEdge;

    public static int MSTPrim(){
        visited = new boolean[V+1];
        minEdge = new int[V+1];
        Arrays.fill(minEdge,Integer.MAX_VALUE);

        int result = 0;
        minEdge[1]=0;
        int c=0;
        for(c=0;c<V-1;c++){
            int min = Integer.MAX_VALUE;
            int minVertex = -1;

            for(int i=1;i<V+1;i++){
                if(!visited[i] && minEdge[i]<min){
                    min=minEdge[i];
                    minVertex=-1;
                }
            }

            if(minVertex==-1) break; //트리를 만들 수 없는 경우
            result+=min;
            visited[minVertex]=true;

            for(int i=1;i<V+1;i++){
                if(!visited[i] && adjMatrix[minVertex][i]!=0
                    &&adjMatrix[minVertex][i]<minEdge[i]){
                    minEdge[i]=adjMatrix[minVertex][i];
                }
            }
        }

        return c==V-1 ? result : -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adjMatrix = new int[V+1][V+1];

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjMatrix[from][to] = weight;
        }

        System.out.println(MSTPrim());
    }
}
