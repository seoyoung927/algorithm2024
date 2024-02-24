package Permutation;

import java.util.Arrays;

// 10P10 : 0.02초 컷		(안전) tc: 3628800   count:0   time:47ms
public class PermutationTest_swap {
    static int N,R; //nPr
    static int[] numbers; //기존 data
    ////////////count
    static long tc; //순열 개수
    static long cnt; //반복 횟수

    public static void perm(int depth){
        if(depth>=R){
            tc++;
            //System.out.println(Arrays.toString(numbers));
            return;
        }

        for(int i=depth;i<N;i++){
            swap(depth, i);
            perm(depth+1);
            swap(depth, i);
        }
    }

    public static void swap(int i, int j){
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }

    public static void main(String[] args){
        //numbers = new int[] {1,2,3};
        numbers = new int[] {1,2,3,4,5,6,7,8,9,10};
        N = numbers.length;
        R = numbers.length;

        long start = System.currentTimeMillis();
        perm(0);
        long end = System.currentTimeMillis();
        System.out.printf("tc: %d   count:%d   time:%dms%n", tc, cnt, end-start);
    }
}
