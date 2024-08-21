import java.io.*;
import java.util.*;

// 176ms
// dp + 비트마스킹
public class Main_2098_G1_TSP2 {
    static final int MAX_NUM = 98765432;
    static int N; // N: 도시의 수
    static int[][] adjMatrix;
    // dp[current][visited]: current에서 출발하여 visited에서 방문하지 않은 도시들을 모두 거쳐
    // 다시 0(출발지)로 되돌아오기 위한 최소비용
    static Integer[][] dp;

    public static int tsp(int current, int visited){
        // 더이상 방문할 도시가 없다면 return
        if( visited == ((1<<N)-1) ) return adjMatrix[current][0]==0 ? MAX_NUM : adjMatrix[current][0];

        // 이미 계산된 경우라면?
        if( dp[current][visited]!=null ) return dp[current][visited];

        // 인접한 도시들 방문
        int minVal = MAX_NUM;
        for(int next=0; next<N; next++){
            // current -> next가 불가능한 경우 next
            if(adjMatrix[current][next]==0) continue;
            // 방문했다면 next
            if((visited & (1<<next)) !=0 ) continue;
            minVal = Math.min(minVal, adjMatrix[current][next]+tsp(next,visited | (1<<next)));
        }

        return dp[current][visited] = minVal;
    }

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];
        dp = new Integer[N][1<<N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(tsp(0,1));
    }
}
