package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int L;
    static int N;
    static Peek[] peek;
    static int answer;

    static class Peek{
        int start;
        int end;
        int duration;

        public Peek() {}
        public Peek(int start, int end, int duration){
            this.start = start;
            this.end = end;
            this.duration = duration;
        }

        @Override
        public String toString(){
            return "[s="+start+",e="+end+",d="+duration+"]";
        }
    }

    public static void dfs(int sIdx, int eIdx, int cur){
        if(sIdx>=N||eIdx>=N) return;
        if(sIdx>eIdx) return;
        if(eIdx==N-1){
            int ans = cur-peek[eIdx].duration+(peek[sIdx].start+L-peek[eIdx].start);
            answer = Math.max(answer, ans);
            return;
        }

        if(peek[sIdx].end+L>peek[eIdx+1].start){
            //앞으로 더 나아갈 수 있음
            dfs(sIdx,eIdx+1, cur+peek[eIdx+1].duration);
        }else{
            //현재가 최대
            int ans = cur-peek[eIdx].duration+(peek[sIdx].start+L-peek[eIdx].start);
            answer = Math.max(answer, ans);
            dfs(sIdx+1, eIdx, cur-peek[sIdx].duration);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            L = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());
            peek = new Peek[N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                peek[i]=new Peek(s,e,e-s);
            }

            answer = 0;
            dfs(0,0, peek[0].duration);

            System.out.printf("#%d %d%n", test_case, answer);
        }
    }
}
