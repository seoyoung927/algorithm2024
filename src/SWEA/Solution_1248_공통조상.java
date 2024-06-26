package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_1248_공통조상 {
    static int T = 10; //test_case의 수
    static int V; //정점의 개수
    static int E; //간선의 개수
    static int V1, V2; //공통 조상을 찾는 두개의 정점
    static Node[] tree;
    static int[] parent;
    static List<Integer> arr1;
    static List<Integer> arr2;
    static int cnt;

    public static class Node{
        int num;
        Node left;
        Node right;

        public Node() {}
        public Node(int num) {
            this.num = num;
        }
    }

    public static void searchAllParents(int cur, List<Integer> arr){ //O(h)
        if(cur<1) return;
        searchAllParents(parent[cur], arr);
        arr.add(cur);
    }

    public static void countChild(int cur){ //O(2^h)
        if(cur<1) return;

        cnt++;
        if(tree[cur].left!=null) countChild(tree[cur].left.num);
        if(tree[cur].right!=null) countChild(tree[cur].right.num);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=10; test_case++){
            //1. 입력 및 초기화
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            V1 = Integer.parseInt(st.nextToken());
            V2 = Integer.parseInt(st.nextToken());

            tree = new Node[V+1];
            parent = new int[V+1];
            Arrays.fill(parent,0);
            for(int i=1; i<=V; i++){
                tree[i] = new Node(i);
            }


            st = new StringTokenizer(br.readLine());
            for(int i=0; i<E; i++){
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                if(tree[p].left==null) tree[p].left = tree[c];
                else tree[p].right = tree[c];
                parent[c] = p;
            }

            //2. 공통 조상 찾기
            arr1 = new ArrayList<>();
            arr2 = new ArrayList<>();
            searchAllParents(V1, arr1);
            searchAllParents(V2, arr2);
            int N = Math.min(arr1.size(), arr2.size());
            int i = 0;
            for(i=0; i<N; i++){
                //System.out.println("arr1: "+arr1.get(i)+",arr2: "+arr2.get(i));
                if(!arr1.get(i).equals(arr2.get(i))) {
                    i-=1;
                    break;
                }
            }
            int common = arr1.get(i);

            //3. 서브트리의 자식개수 계산
            cnt = 0;
            countChild(common);

            //4. 출력
            System.out.printf("#%d %d %d%n", test_case, common, cnt);
        }
    }
}
