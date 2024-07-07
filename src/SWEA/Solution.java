package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int L;
    static int N;
    static Peek[] peek;
    static int[] sum;
    static int answer;

    static class Peek{
        int start;
        int end;
        int duration;

        public Peek() {}
        public Peek(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString(){
            return "[s="+start+",e="+end+"]";
        }
    }

    public static int search(int target){
        int ret = N;
        int start = 0;
        int end = N-1;

        while(start<=end){
            int mid = (start+end)/2;

            if(peek[mid].end>target){
                ret = mid;
                end = mid-1;
            }else{
                start = mid+1;
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            L = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());
            peek = new Peek[N];
            sum = new int[N+1];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                peek[i]=new Peek(s,e);

                //누적합
                if(i==0) sum[i]=e-s;
                else sum[i]=sum[i-1]+e-s;
            }

            answer = 0;
            for(int i=0; i<N; i++){
                int target = peek[i].start+L;
                int ub = search(target);

                int s = sum[ub-1];
                if(i>0) s-=sum[i-1];

                if(ub!=N && target>=peek[ub].start) s+=target-peek[ub].start;

                answer=Math.max(answer,s);
            }

            System.out.printf("#%d %d%n", test_case, answer);
        }
    }
}
