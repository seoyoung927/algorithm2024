import java.io.*;
import java.util.*;

//676ms
public class Main_12015_G2_LIS2 {
    static int N; //1 ≤ N ≤ 1,000,000
    static int[] A;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        //0. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());


        List<Integer> arr = new ArrayList<>();
        for(int i=0; i<N; i++){
            int pos = Collections.binarySearch(arr, A[i]);
            if(pos<0) pos = -1*(pos+1);
            if(pos < arr.size()) arr.set(pos, A[i]);
            else arr.add(A[i]);
            dp[i] = pos+1;
        }

        int answer = 0;
        for(int i=0; i<N; i++) answer = Math.max(answer, dp[i]);

        System.out.println(answer);
    }
}
