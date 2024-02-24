package Permutation;

import java.util.Arrays;

// 10P10 : 0.02초 time : 29ms   count:5880473   tc:3628800
// 11P11 : 0.15초컷    (위험)
public class Permutation_NP {
    static int[] numbers; //기존 data
    ////////////count
    static long tc=0; //순열 개수
    static long cnt=0; //반복 횟수

    public static boolean np(int[] p){
        int N = p.length-1;

        int i = N;
        while(i>0 && p[i-1]>=p[i]){
            //가장 큰 p[i]<p[i+1]를 찾기
            cnt++;
            --i;
        }
        if(i==0) return false;

        int j = N;
        while(p[i-1]>p[j]){
            cnt++;
            --j;
        }
        swap(p,i-1,j);

        int k = N;
        while(i<k){
            cnt++;
            swap(p,i++,k--);
        }

        return true;
    }

    public static void swap(int[] p, int i, int j){
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }

    public static void main(String[] args){
        numbers = new int[] {1,2,3,4,5,6,7,8,9,10};

        long start = System.currentTimeMillis();
        do{
            tc++;
            //System.out.println(Arrays.toString(numbers));
        }while(np(numbers));
        long end = System.currentTimeMillis();
        System.out.printf("time : %dms   count:%d   tc:%d%n", end-start, cnt, tc);
    }
}
