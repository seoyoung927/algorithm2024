import java.io.*;
import java.util.*;

// dia: 다이아몬드 곡괭이의 수
// iron: 철 곡괭이의 수
// stone: 돌 곡괭이의 수
// 각 곡괭이는 종류에 상관없이 광물 5개를 캔 후에는 더 이상 사용할 수 없다.
class Solution_LV2_광물 캐기 {
    static int N;
    static int[][] tired = {{1,1,1},{5,1,1},{25,5,1}};
    static int[] pick = {0,0,0};
    static String[] mineral;
    static Map<String, Integer> map = new HashMap<>();
    static int answer = Integer.MAX_VALUE;
    
    public void dfs(int idx, int val, int curPick, int curCount){
        if(idx == N){
            answer = Math.min(answer, val);
            return;
        }
        
        if(curCount>0){
            dfs(idx+1, val+tired[curPick][map.get(mineral[idx])], curPick, curCount-1);
            return;
        }
        
        boolean flag = false;
        for(int i=0; i<3; i++){
            if(pick[i]>0){
                flag = true;
                pick[i]--;
                dfs(idx+1, val+tired[i][map.get(mineral[idx])], i, 4);
                pick[i]++;
            }
        }
        if(!flag){
            answer = Math.min(answer, val);
            return;
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        N = minerals.length;
        for(int i=0; i<3; i++) pick[i] = picks[i];
        map.put("diamond",0);
        map.put("iron",1);
        map.put("stone",2);
        mineral = new String[N];
        for(int i=0; i<N; i++) mineral[i] = minerals[i];
        
        dfs(0,0,0,0);
        
        return answer;
    }
}
