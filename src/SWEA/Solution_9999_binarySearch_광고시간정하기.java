package SWEA;

import java.io.*;
import java.util.*;

public class Solution_9999_binarySearch_광고시간정하기 {
    static int L, N;
    static Ad[] ads;
    static int[] S;

    static class Ad{
        int start;
        int end;

        public Ad() {}

        public Ad(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            //1. 입력 및 초기화
            L = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());

            ads = new Ad[N];
            S = new int[N+1];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                ads[i] = new Ad(s,e);

                //누적합
                S[i+1] = S[i]+e-s;
            }

            //2. 이분탐색
            int answer = 0;
            for(int i=0; i<N; i++){
                int left = i;
                int right = N-1;

                while(left<=right){
                    int mid = (left+right)/2;

                    if(ads[mid].end-ads[i].start<=L){
                        left = mid+1;
                    }else{
                        right = mid - 1;
                    }
                }

                int ans = S[left]-S[i];
                if(left<N) ans+=Math.max(0,(ads[i].start+L)-ads[left].start);

                answer = Math.max(answer, ans);
            }


            //3. 출력
            System.out.printf("#%d %d%n",test_case,answer);
        }
    }
}
