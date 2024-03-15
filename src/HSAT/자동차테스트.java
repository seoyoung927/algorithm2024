package HSAT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 자동차테스트 {
    static int n,q; //n개의 자동차, q개의 질의
    static int[] combi;
    static long[] cars;
    static int result;

    public static void getCombi(int depth, int start, long target){
        if(depth>=3){
            long[] arr = new long[3];
            for(int i=0;i<3;i++){
                arr[i] = cars[combi[i]];
            }
            Arrays.sort(arr);

            if(arr[1]==target) result+=1;

            return;
        }

        for(int i=start; i<n; i++){
            combi[depth]=i;
            getCombi(depth+1, i+1, target);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        cars = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) cars[i] = Long.parseLong(st.nextToken());

        for(int i=0;i<q;i++){
            long target = Long.parseLong(br.readLine());
            combi = new int[3];
            result = 0;
            getCombi(0,0, target);
            System.out.println(result);
        }
    }
}
