package HyundaiMobis;

import java.util.*;

public class 상담원인원 {
    static int N;
    static int K;
    static Set<List<Integer>> partitions;
    static List<List<Integer>> permsArr;
    static int[] perms;
    static boolean[] isSelected; //배열을 이용해서 중복관리
    static Map<Integer, List<Req>> reqMap;
    static long answer;

    public class Req implements Comparable<Req>{
        int type;
        int start;
        int duration;

        public Req(int a, int b, int c){
            this.type = c;
            this.start = a;
            this.duration = b;
        }

        @Override
        public int compareTo(Req other){
            return Integer.compare(this.start, other.start);
        }

        @Override
        public String toString(){
            return "[start= "+start+", duration= "+duration+"]";
        }
    }


    public static void partition(int n, int k, int min, List<Integer> result) {
        if (k == 1) {
            if (n >= min) {
                result.add(n);
                for(List<Integer> p : permsArr){
                    List<Integer> tmp = new ArrayList<>();
                    for(int i : p){
                        tmp.add(result.get(i));
                    }
                    partitions.add(tmp);
                }
                result.remove(result.size() - 1);
            }
            return;
        }

        for (int i = min; i <= n - k; i++) {
            result.add(i);
            partition(n - i, k - 1, i, result); // 중복X
            result.remove(result.size() - 1); // 백트래킹
        }
    }

    public static void perm(int depth){
        if(depth>=K){
            List<Integer> tmp = new ArrayList<>();
            for(int i=0; i<K; i++) tmp.add(perms[i]);
            permsArr.add(tmp);
            return;
        }

        for(int i=0; i<K; i++){
            if(isSelected[i]) continue;
            isSelected[i]=true;
            perms[depth]=i;
            perm(depth+1);
            isSelected[i]=false;
        }
    }

    public int solution(int k, int n, int[][] reqs) {
        //1. 초기화
        N = n;
        K = k;
        partitions = new HashSet<>();
        permsArr = new ArrayList<>();
        perms = new int[K];
        isSelected = new boolean[K];
        reqMap = new HashMap<>();
        answer = Long.MAX_VALUE;
        for(int type=1; type<=K; type++){
            reqMap.put(type, new ArrayList<>());
        }
        for(int i=0, SIZE=reqs.length; i<SIZE; i++){
            int a = reqs[i][0];
            int b = reqs[i][1];
            int c = reqs[i][2];
            reqMap.get(c).add(new Req(a,b,c));
        }

        //2. 자연수 분할 + 순열
        perm(0);
        if(N==K){
            List<Integer> tmp = new ArrayList<>();
            for(int i=0; i<K; i++) tmp.add(1);
            partitions.add(tmp);
        }
        else partition(N, K, 1, new ArrayList<>());

        //3. 대기시간 측정
        for(int key : reqMap.keySet()){
            Collections.sort(reqMap.get(key));
        }

        //System.out.println(reqMap);
        for (List<Integer> p : partitions) {
            //System.out.println(p);
            long wait = 0L;
            for(int type=1; type<=K; type++){
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                List<Req> reqArr = reqMap.get(type);
                int cnt = p.get(type-1); //상담원 수
                for(Req r : reqArr){
                    if(pq.isEmpty() || pq.size() < cnt){ //상담원수가 남아있을 때
                        pq.add(r.start+r.duration);
                    }else{
                        int early = pq.poll();
                        if(early<r.start){ //대기시간 X
                            pq.add(r.start+r.duration);
                        }else{
                            wait+=(early-r.start);
                            pq.add(early+r.duration);
                        }
                    }
                }
            }
            //System.out.println("wait: "+wait);
            answer = Math.min(answer, wait);
        }

        return (int) answer;
    }
}