package SWEA;

import java.io.*;
import java.util.*;

public class Solution_10507_binarySearch_영어공부 {
    static int n,p;
    static int[] numbers;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            //1. 입력 및 초기화
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            numbers = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) numbers[i] = Integer.parseInt(st.nextToken());


            //2. 이분탐색
            int answer = 0;
            for(int i=0; i<n; i++){
                int left = i;
                int right = n-1;

                while(left<=right){
                    int mid = (left+right)/2;

                    if(numbers[mid]-numbers[i]-p<=mid-i){
                        left = mid+1;
                    }else{
                        right = mid-1;
                    }
                }

                answer = Math.max(answer, left-i+p);
            }

            System.out.printf("#%d %d%n", test_case, answer);
        }
    }
}
