package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_Heap {
    static int N;

    static class Heap{
        int[] arr;
        int cnt; //현재 노드의 개수를 나타냄

        public Heap(){
            arr = new int[100055];
            cnt = 0;
        }

        public void add(int n){
            arr[++cnt] = n;
            int now = cnt;
            while(now>1){
                int parent = getParent(now);
                if(arr[now]>arr[parent]){
                    int tmp = arr[parent];
                    arr[parent] = arr[now];
                    arr[now] = tmp;
                    now = parent;
                }else{
                    break;
                }
            }
        }

        public void heapify(){
            int now = 1;
            while(getRight(now)<=cnt){
                int larger = now;
                int left = getLeft(now);
                int right = getRight(now);
                if(arr[left]>arr[larger]) larger = left;
                if(arr[right]>arr[larger]) larger = right;
                if(larger!=now){
                    int tmp = arr[larger];
                    arr[larger] = arr[now];
                    arr[now] = tmp;
                    now = larger;
                }else{
                    break;
                }
            }
        }

        public int poll(){
            if(cnt==0) return -1;

            int max = arr[1];
            arr[1] = arr[cnt];
            arr[cnt] = 0;
            heapify();
            cnt--;
            return max;
        }

        public int getParent(int n) { return n/2; }
        public int getLeft(int n) { return 2*n; }
        public int getRight(int n) { return 2*n+1; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            Heap heap = new Heap();

            N = Integer.parseInt(br.readLine());
            System.out.printf("#%d ",test_case);
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                if(cmd==1){
                    int n = Integer.parseInt(st.nextToken());
                    heap.add(n);
                }else if(cmd==2){
                    System.out.print(heap.poll()+" ");
                }
            }
            System.out.println();
        }
    }
}
