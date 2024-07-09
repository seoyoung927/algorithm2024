package DP;

import java.io.*;
import java.util.*;

//236ms
public class Main_2098_G1_TSP {
    static int N; //N: 도시의 수
    static int[][] adjMatrix;
    //dp[current][visited]
    //현재 도시에서 남은 모든 도시들을 모두 방문하고 출발지로 돌아가기 위한 최소비용
    static Integer[][] dp;

    public static int tsp(int current, int visited){
        //1-1. 만약 모든 도시들을 방문했다면 current->0(출발지) 비용 return
        if( visited == (1<<N)-1 ) {
            return adjMatrix[current][0]==0 ? 98765432 : adjMatrix[current][0];
        }

        //1-2. dp가 이미 계산되었다면
        if(dp[current][visited]!=null) return dp[current][visited];

        //1-3. 다음 방문 도시 방문으로 값 update
        int minCost = 98765432;
        for(int next=0; next<N; next++){
            if( (visited & (1<<next))==0 && adjMatrix[current][next]!=0){
                minCost = Math.min(minCost, tsp(next, visited|(1<<next))+adjMatrix[current][next] );
            }
        }

        return dp[current][visited]=minCost;
    }

    public static void main(String[] args) throws IOException {
        //0. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //dp는 INF로 초기화
        dp = new Integer[N][1<<N];

        //1. TSP(재귀) + 출력
        System.out.println(tsp(0,1));
    }
}
