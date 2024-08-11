package src.Baekjoon;

import java.util.*;
import java.io.*;

// 104ms
public class Main_1043_G4_거짓말_서로소집합 {
    static int N, M; // N: 사람의 수, M: 파티의 수
    static int WITNESS_SIZE; // 진실을 아는 사람의 수
    static List<Integer> witness;
    static List[] graph;
    static int[] parents;

    public static void make(){
        parents = new int[M+1];
        for(int i=0; i<M+1; i++){
            parents[i] = i;
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
        if(bRoot > aRoot){
            int tmp = aRoot;
            aRoot = bRoot;
            bRoot = tmp;
        }
        parents[bRoot] = aRoot;
        return true;
    }
    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        witness = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        WITNESS_SIZE = Integer.parseInt(st.nextToken());
        for(int i=0; i<WITNESS_SIZE; i++){
            witness.add(Integer.parseInt(st.nextToken()));
        }

        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=1; i<M+1; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for(int j=0; j<n; j++){
                int participant = Integer.parseInt(st.nextToken());
                graph[participant].add(i);
            }
        }

        make();
//        for(int i=1; i<N+1; i++){
//            System.out.println(graph[i]);
//        }

        // 2. 서로소 집합
        for(int i=1; i<N+1; i++){
            for(int j=0, SIZE=graph[i].size(); j<SIZE-1; j++){
                //System.out.println((int) graph[i].get(j)+" "+(int) graph[i].get(j+1));
                union((int) graph[i].get(j), (int) graph[i].get(j+1));
            }
        }
        //System.out.println(Arrays.toString(parents));

        int[] rootParents = new int[M+1];
        for(int i=1; i<M+1; i++){
            rootParents[i] = find(parents[i]);
        }
        boolean[] canVisited = new boolean[M+1];
        Arrays.fill(canVisited, true);
        for(int n: witness){
            for(int i=0, SIZE=graph[n].size(); i<SIZE; i++){
                int idx = (int) graph[n].get(i);
                int target = rootParents[idx];

                for(int j=1; j<M+1; j++){
                    if(rootParents[j]==target) canVisited[j] = false;
                }
            }
        }

        // 3. 출력
        int answer = M;
        for(int i=1; i<M+1; i++){
            if(canVisited[i]==false) answer--;
        }
        System.out.println(answer);
    }
}
