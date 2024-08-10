package Baekjoon;

import java.util.*;
import java.io.*;

// O(N^2)
public class Main_1253_G4_좋다_투포인터 {
    static int N;
    static int[] numbers;

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers); // 정렬

        // 2. answer 값 계산
        int answer = 0;
        for(int i=0; i<N; i++){
            int n = numbers[i];
            int left = 0;
            int right = N-1;

            while(left<right){
                int s = numbers[left]+numbers[right];

                if(s==n){
                    if(left==i){
                        left++;
                    }else if(right==i){
                        right--;
                    }else{
                        answer++;
                        break;
                    }
                }else if(s>n){
                    right--;
                }else if(s<n){
                    left++;
                }
            }
        }

        // 3. 출력
        System.out.println(answer);
    }
}
