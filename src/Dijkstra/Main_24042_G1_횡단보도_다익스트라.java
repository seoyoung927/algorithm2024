import java.util.*;
import java.io.*;

// 1164ms, 다익스트라
public class Main_24042_G1_횡단보도_다익스트라 {
    static int N, M; // N: 지역의 수, M: 횡단보도의 주기
    static List<Node>[] signal;

    static class Node implements Comparable<Node>{
        int n;
        long t;

        public Node() {}

        public Node(int n, long t){
            this.n = n;
            this.t = t;
        }

        @Override
        public int compareTo(Node other){
            return Long.compare(this.t, other.t);
        }

        @Override
        public String toString(){
            return "(n="+n+",t="+t+")";
        }
    }

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        signal = new ArrayList[N+1];
        for(int i=1; i<N+1; i++) signal[i] = new ArrayList<>();
        for(int t=0; t<M; t++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            signal[from].add(new Node(to, t));
            signal[to].add(new Node(from, t));
        }
        //for(int i=1; i<N+1; i++) System.out.println(signal[i]);


        // 2. 다익스트라
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] minDistance = new long[N+1];
        Arrays.fill(minDistance, Long.MAX_VALUE);
        pq.add(new Node(1,0));
        minDistance[1] = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            //System.out.println(cur);
            //System.out.println(Arrays.toString(minDistance));
            int curN = cur.n;
            long curT = cur.t;
            long curIdx = curT%M;

            if(curN == N){
                System.out.println(curT);
                return;
            }

            for(Node next : signal[curN]){
                long waitTime = (next.t - curIdx + M) % M;  // 다음 신호까지 기다리는 시간
                long nextT = curT + waitTime + 1;  // 다음 신호등에 도착하는 시간

                if(nextT < minDistance[next.n]){
                    minDistance[next.n] = nextT;
                    pq.add(new Node(next.n, nextT));
                }
            }
        }
    }
}
