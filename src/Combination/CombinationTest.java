package Combination;

import java.util.Arrays;

/**
 * 25C12,13		=> 35ms		안전 조합수 : 5,200,300
 * 26C8			=> 110ms   	위험	조합수 : 10,400,600  ==> 백트레킹을 시도
 * 27C14		=> 241ms	위험	==> 백트레킹을 시도
 * 30C15  		=> 1.2초 컷  안됨 1억 5천
 */
public class CombinationTest {
    static int N,R; //nCr
    static int[] numbers; //기존 data
    static int[] combis; //내가 뽑은 조합
    ////////////count
    static long tc=0; //조합 개수
    static long cnt=0; //반복 횟수

    public static void combi(int depth, int start){
        if(depth>=R){
            tc++;
            //System.out.println(Arrays.toString(combis));
            return;
        }

        for(int i=start;i<N;i++){
            cnt++;
            combis[depth]=i;
            combi(depth+1,i+1);
        }
    }

    public static void main(String[] args){
        N = 26;
        R = 13;
        numbers = new int[N];
        for(int i=0;i<N;i++) numbers[i] = i;
        combis = new int[R];

        long start = System.currentTimeMillis();
        combi(0,0);
        long end = System.currentTimeMillis();
        System.out.printf("time : %dms   count:%d   tc:%d%n", end-start, cnt, tc);
    }
}
