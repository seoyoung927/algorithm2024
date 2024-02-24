package Permutation;

import java.util.Arrays;

// 중복 원소를 bitmask를 이용해서 체크하기
// 9P9   : 0.009	    (안전)
// 10P10 : 0.1초 컷		(약간 위험)     tc: 3628800   count:62353010   time:173ms
public class PermutationTest_bitmasking {
    static int N,R; //nPr
    static int[] numbers; //기존 data
    static int[] perms; //내가 만든 순열 조합
    static int bitmasking; //비트마스킹을 이용한 중복 원소 관리
    ////////////count
    static long tc; //순열 개수
    static long cnt; //반복 횟수

    public static void perm(int depth){
        if(depth>=R){
            tc++;
            //System.out.println(Arrays.toString(perms));
            return;
        }

        for(int i=0;i<N;i++){
            cnt++;
            if((bitmasking & (1<<i))!=0) continue;
            perms[depth]=i;
            bitmasking |= (1<<i);
            perm(depth+1);
            bitmasking &= ~(1<<i);
        }
    }

    public static void main(String[] args){
        //numbers = new int[] {1,2,3};
        numbers = new int[] {1,2,3,4,5,6,7,8,9,10};
        N = numbers.length;
        R = numbers.length;
        perms = new int[R];
        bitmasking = 0;

        long start = System.currentTimeMillis();
        perm(0);
        long end = System.currentTimeMillis();
        System.out.printf("tc: %d   count:%d   time:%dms%n", tc, cnt, end-start);
    }
}
