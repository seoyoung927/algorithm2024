package DisjointSet;

import java.util.*;
import java.io.*;

// O(NM), 104ms
public class Main_1043_G4_거짓말_서로소집합2 {
    static int N, M; // N: 사람의 수, M: 파티의 수
    static int SIZE_OF_WITNESS;
    static List<Integer> witness;
    static List[] participant;
    static int[] parents;
    static Map<Integer, List<Integer>> rootMap;

    public static void make(){
        parents = new int[M+1];
        for(int i=0; i<M+1; i++) parents[i] = i;
    }

    public static int find(int v){
        if(v==parents[v]) return v;
        return parents[v] = find(parents[v]);
    }

    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot==bRoot) return false;
        parents[bRoot]=aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //// 1-1. 사람의 수 N과 파티의 수 M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //// 1-2. 이야기의 진실을 아는 사람의 수와 번호
        st = new StringTokenizer(br.readLine());
        SIZE_OF_WITNESS = Integer.parseInt(st.nextToken());
        witness = new ArrayList<>();
        for(int i=0; i<SIZE_OF_WITNESS; i++){
            witness.add(Integer.parseInt(st.nextToken()));
        }

        ///// 1-3. 각 파티마다 오는 사람의 수와 번호
        participant = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            participant[i] = new ArrayList();
        }
        for(int i=1; i<M+1; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for(int j=0; j<n; j++){
                int p = Integer.parseInt(st.nextToken());
                participant[p].add(i);
            }
        }

        //// 1-4. 서로소 집합 초기화
        make();

        // 2. 서로소 집합
        for(int i=1; i<N+1; i++){
            for(int j=0, SIZE=participant[i].size(); j<SIZE-1; j++){
                union((int) participant[i].get(j), (int) participant[i].get(j+1));
            }
        }
        //// 두 노드의 대표자가 같다면 연결되어 있다고 판단할 수 있다.
        //// 대표자가 다르면 연결되어 있지 않다.
        int[] rootParents = new int[M+1];
        rootMap = new HashMap<>();
        for(int i=1; i<M+1; i++){
            rootMap.put(i, new ArrayList<>());
        }
        for(int i=1; i<M+1; i++){
            int rootParent = find(parents[i]);
            rootMap.get(rootParent).add(i);
            rootParents[i] = rootParent;
        }

        //// 진실을 아는 파티 식별: O(NM)
        Set<Integer> notAllowed = new HashSet<>();
        for(int n : witness){
            for(int i=0, SIZE=participant[n].size(); i<SIZE; i++){
                int p = (int) participant[n].get(i);
                notAllowed.add(rootParents[p]);
            }
        }

        // 3. 최종 결과 계산 및 출력: O(M)
        int answer = M;
        for(int n : notAllowed){
            answer -= rootMap.get(n).size();
        }
        System.out.println(answer);
    }
}