import java.util.*;
import java.io.*;

// DFS, O(N^2)
public class Main_2668_G5_숫자고르기_DFS {
    static int N;
    static boolean[] visited;
    static int[] numbers;
    static List<Integer> answer;

    public static void dfs(int idx, int target){
        if(numbers[idx]==target) answer.add(numbers[idx]);

        if(visited[numbers[idx]]) return;

        visited[numbers[idx]] = true;
        dfs(numbers[idx], target);
        visited[numbers[idx]] = false;
    }

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N+1];
        visited = new boolean[N+1];
        answer = new ArrayList<>();
        for(int i=1; i<N+1; i++) numbers[i] = Integer.parseInt(br.readLine());

        // 2. DFS
        for(int i=1; i<N+1; i++){
            visited[i] = true;
            dfs(i,i);
            visited[i] = false;
        }

        // 3. 출력
        Collections.sort(answer);
        int size = answer.size();
        System.out.println(answer.size());
        for(int i=0; i<size; i++) System.out.println(answer.get(i));
    }
}
