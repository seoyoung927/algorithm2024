package CumulativeSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10986_2 {
    static int N, M;
    static int[] arr;
    static long[] sumArr;

    public static void main(String[] args) throws IOException {
        //1. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //2. 누적합
        int s = 0;
        sumArr = new long[M];
        for(int i=0; i<N; i++){
            s += arr[i];
            s = s % M;
            sumArr[s]++;
        }

        //3. 계산
        long answer = sumArr[0];
        for(int i=0; i<M; i++){
             answer += sumArr[i]*(sumArr[i]-1)/2;
        }
        System.out.println(answer);
    }
}
