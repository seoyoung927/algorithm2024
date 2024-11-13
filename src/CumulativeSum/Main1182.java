import java.util.*;
import java.io.*;

public class Main1182 {
    public static int N, S;
    public static int[] arr;
    public static int answer;

    public static void dfs(int depth, int val){
        if(depth==N) {
            if(val==S) answer++;
            return;
        }
        if(val>0 && val>S) return;

        dfs(depth+1, val+arr[depth]);
        dfs(depth+1, val);
    }

    public static void main(String[] args) throws IOException {
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)  arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        // 2. dfs
        answer = 0;
        if(S==0) answer--;
        dfs(0, 0);

        // 3. 출력
        System.out.println(answer);
    }
}
