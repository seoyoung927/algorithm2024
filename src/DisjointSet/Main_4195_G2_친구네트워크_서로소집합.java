package DisjointSet;

import java.util.*;
import java.io.*;

// 1668ms
// union 연산에서 랭크 기반 union을 사용하므로 O(1)에 가깝게 동작한다,
// 따라서 대략 O(N)=O(F)
public class Main_4195_G2_친구네트워크_서로소집합 {
    static final int N = 200005;
    static int F; // F: 친구 관계의 수
    static Map<String, Integer> name;
    static int idx;
    static int[] parents;
    static int[] ranks;

    public static void make(){
        parents = new int[N];
        ranks = new int[N];
        for(int i=0; i<N; i++){
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    public static int find(int v){
        if(v == parents[v]) return v;
        return parents[v] = find(parents[v]);
    }

    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot==bRoot) {
            System.out.println(Math.max(ranks[aRoot], ranks[bRoot]));
            return false;
        }

        if (ranks[aRoot] < ranks[bRoot]) {
            parents[aRoot] = bRoot;
            ranks[bRoot] += ranks[aRoot];
            System.out.println(ranks[bRoot]);
        } else {
            parents[bRoot] = aRoot;
            ranks[aRoot] += ranks[bRoot];
            System.out.println(ranks[aRoot]);
        }
        return true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            F = Integer.parseInt(br.readLine());

            make();
            idx = 0;
            name = new HashMap<>();
            for(int i=0; i<F; i++){
                st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                if(!name.containsKey(name1)) name.put(name1, idx++);
                String name2 = st.nextToken();
                if(!name.containsKey(name2)) name.put(name2, idx++);

                union(name.get(name1), name.get(name2));
            }
        }
    }
}
