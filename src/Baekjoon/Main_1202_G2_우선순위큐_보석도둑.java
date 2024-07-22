import java.io.*;
import java.util.*;

//1936ms
public class Main_1202_G2_우선순위큐_보석도둑 {
    static int N, K;
    static Jewel[] jewel;
    static int[] bag;

    static class Jewel implements Comparable<Jewel>{
        int m; //무게
        int v; //가격

        public Jewel() {}

        public Jewel(int m, int v){
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel other){
            return Integer.compare(this.m, other.m);
        }

        @Override
        public String toString(){
            return "[m="+m+", v="+v+"]";
        }
    }

    public static void main(String[] args) throws IOException{
        //0. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewel = new Jewel[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewel[i] = new Jewel(w,v);
        }
        Arrays.sort(jewel);

        int s = 0;
        bag = new int[K];
        for(int i=0; i<K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
            s+=bag[i];
        }
        Arrays.sort(bag);

        //2. 우선순위 큐
        long answer = 0;
        int idx = 0;
        PriorityQueue<Jewel> pq = new PriorityQueue<>(new Comparator<Jewel>(){
            @Override
            public int compare(Jewel j1, Jewel j2){
                return Integer.compare(j2.v, j1.v); //가치를 기준으로 내림차순 정렬
            }
        });

        for(int i=0; i<K; i++){
            int j = idx;
            for(j=idx; j<N; j++){
                if(jewel[j].m>bag[i]) break;
                pq.add(jewel[j]);
            }
            idx = j;

            if(!pq.isEmpty()){
                Jewel cur = pq.poll();
                answer+=cur.v;
            }
        }

        //3. 출력
        System.out.println(answer);
    }
}
