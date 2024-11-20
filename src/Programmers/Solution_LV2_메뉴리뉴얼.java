package Programmers;

import java.util.*;
import java.io.*;

class Solution_LV2_메뉴리뉴얼 {
    // 조합
    public static void getCombination(Map<String, Integer> ret, String str, int N, int depth, int start, char[] cur){
        if(depth>=N){
            String s = new String(cur);
            if(!ret.containsKey(s)) ret.put(s, 0);
            ret.put(s, ret.get(s)+1);
            return;
        }

        for(int i=start, LEN=str.length(); i<LEN; i++){
            cur[depth] = str.charAt(i);
            getCombination(ret, str, N, depth+1, i+1, cur);
        }

    }

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        for(int c : course){
            Map<String, Integer> candidate = new HashMap<>();
            for(String order : orders) {
                char[] tmp = order.toCharArray();
                Arrays.sort(tmp);
                String sortedOrder = new String(tmp);
                getCombination(candidate, sortedOrder, c, 0, 0, new char[c]);
            }
            int maxVal = 2;
            for(int val : candidate.values()){
                if(val>maxVal) maxVal = val;
            }

            for(String key : candidate.keySet()){
                if(candidate.get(key)==maxVal){
                    answer.add(key);
                }
            }
        }

        // 결과 정렬
        Collections.sort(answer);

        // List를 String[]로 변환하여 반환
        return answer.toArray(new String[0]);
    }
}