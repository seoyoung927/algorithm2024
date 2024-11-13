import java.io.*;
import java.util.*;

public class Main1806 {
    public static int N,S;
    public static int[] arr;

    public static void main(String[] args) throws Exception {
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        // 2. 누적합, 투 포인터
        int answer = Integer.MAX_VALUE;
        int s = 0;
        int left = 0;
        for(int right=0; right<N; right++){
            s += arr[right];
            while(s>=S && left<=right){
                answer = Math.min(answer, right-left+1);
                s -= arr[left++];
            }
        }

        // 3. 출력
        if(answer==Integer.MAX_VALUE) answer=0;
        System.out.println(answer);
    }
}
