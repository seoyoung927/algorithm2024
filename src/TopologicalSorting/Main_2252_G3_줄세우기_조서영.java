package TopologicalSorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252_G3_줄세우기_조서영 {
    static int N,M;
    static ArrayList<Integer>[] list; //진출 정보를 담는 배열
    static int[] inDegree; //진입 차수를 담는 배열
    static ArrayList<Integer> result = new ArrayList<>(); //위상정렬 결과
    static Queue<Integer> queue = new ArrayDeque<>(); //진입 차수가 0인 정점을 담는 큐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        list=new ArrayList[N+1];
        inDegree=new int[N+1];
        for(int i=1;i<=N;i++) list[i]=new ArrayList<Integer>();
        //1. 학생정보 입력
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            list[from].add(to);
            inDegree[to]++;
        }

        //2. 진입차수가 0인 정점을 큐에 담기
        for(int i=1;i<=N;i++){
            if(inDegree[i]==0) queue.offer(i);
        }

        //3. 진입 차수가 0인 정점부터 연결된 정점들을 탐색
        while(!queue.isEmpty()){
            int cur = queue.poll();
            result.add(cur);

            for(int i=0,size=list[cur].size();i<size;i++){
                int ad = list[cur].get(i);
                inDegree[ad]--;
                if(inDegree[ad]==0) queue.add(ad);
            }
        }

        //4. 출력
        for(int i=0,size=result.size();i<size;i++){
            System.out.print(result.get(i)+" ");
        }
    }
}
