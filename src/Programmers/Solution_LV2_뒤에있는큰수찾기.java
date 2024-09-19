package Programmers;

import java.util.*;

class Solution_LV2_뒤에있는큰수찾기 {
    int N;

    public int[] solution(int[] numbers) {
        // 1. 입력 및 초기화
        N = numbers.length;
        int[] bigNum = new int[N];
        Arrays.fill(bigNum, -1);
        for(int i=N-1; i>=0; i--){
            int idx = i+1;
            while(idx<N){
                if(numbers[i]<numbers[idx]){
                    bigNum[i] = numbers[idx];
                    break;
                }else if(numbers[i]==numbers[idx]){
                    bigNum[i] = bigNum[idx];
                    break;
                }else if(numbers[i]==numbers[idx]+1 && bigNum[idx]==-1){
                    break;
                }
                idx++;
            }
        }

        return bigNum;
    }
}