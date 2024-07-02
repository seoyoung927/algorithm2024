import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3000_중간값구하기 {
    static int N, A; //N: 노트에 글자를 적는 횟수, A: 처음 적은 숫자

    static class MaxHeap{
        int[] arr;
        int cnt;

        public MaxHeap(){
            arr = new int[200055];
            cnt = 0;
        }

        public void add(int n){
            arr[++cnt]=n;
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
            while(getLeft(now)<=cnt){
                int larger = now;
                int left = getLeft(now);
                int right = getRight(now);

                if(left <= cnt && arr[larger]<arr[left]) larger = left;
                if(right <= cnt && arr[larger]<arr[right]) larger = right;

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
            int ret = arr[1];
            arr[1] = arr[cnt];
            arr[cnt] = 0;
            cnt--;
            heapify();
            return ret;
        }

        public int getParent(int n) { return n/2; }
        public int getLeft(int n) { return 2*n; }
        public int getRight(int n) { return 2*n+1; }
    }

    static class MinHeap{
        int[] arr;
        int cnt;

        public MinHeap(){
            arr = new int[200055];
            cnt = 0;
        }

        public void add(int n){
            arr[++cnt]=n;
            int now = cnt;
            while(now>1){
                int parent = getParent(now);
                if(arr[now]<arr[parent]){
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

            while(getLeft(now)<=cnt){
                int smaller = now;
                int left = getLeft(now);
                int right = getRight(now);

                if(left <= cnt && arr[smaller]>arr[left]) smaller = left;
                if(right <= cnt && arr[smaller]>arr[right]) smaller = right;

                if(smaller!=now){
                    int tmp = arr[smaller];
                    arr[smaller] = arr[now];
                    arr[now] = tmp;
                    now = smaller;
                }else{
                    break;
                }
            }
        }

        public int poll(){
            if(cnt==0) return -1;
            int ret = arr[1];
            arr[1] = arr[cnt];
            arr[cnt] = 0;
            cnt--;
            heapify();
            return ret;
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
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            MaxHeap maxHeap = new MaxHeap();
            MinHeap minHeap = new MinHeap();
            maxHeap.add(A);
            minHeap.add(A);

            int answer = 0;
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                //a가 더 작거나 같은 숫자가 되도록
                if(a>b){
                    int tmp = a;
                    a = b;
                    b = tmp;
                }

                int mid = maxHeap.arr[1];
                if(a<=mid && b>=mid){
                    maxHeap.add(a);
                    minHeap.add(b);
                }else if(b<=mid){
                    maxHeap.poll();
                    maxHeap.add(a);
                    maxHeap.add(b);
                    int newMid = maxHeap.arr[1];
                    minHeap.add(newMid);
                }else{
                    minHeap.poll();
                    minHeap.add(a);
                    minHeap.add(b);
                    int newMid = minHeap.arr[1];
                    maxHeap.add(newMid);
                }
                mid = minHeap.arr[1];
                answer += mid%20171109;
                answer %= 20171109;
            }

            System.out.printf("#%d %d%n",test_case,answer);
        }
    }
}
