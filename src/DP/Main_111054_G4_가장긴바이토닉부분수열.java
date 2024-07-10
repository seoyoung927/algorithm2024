import java.io.*;
import java.util.*;

//132ms
public class Main_111054_G4_가장긴바이토닉부분수열 {
    static int N;
    static int[] A;
    static int[] dp1;
    static int[] dp2;

    public static void main(String[] args) throws IOException {
        //0. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp1 = new int[N];
        dp2 = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());

        //1. dp
        for(int i=0; i<N; i++){
            for(int j=0; j<i; j++){
                if(A[i]>A[j]) dp1[i] = Math.max(dp1[i],dp1[j]+1);
            }
        }
        for(int i=N-1; i>=0; i--){
            for(int j=N-1; j>i; j--){
                if(A[i]>A[j]) dp2[i] = Math.max(dp2[i],dp2[j]+1);
            }
        }

        //2. 계산 및 출력
        int answer = 0;
        for(int i=0; i<N; i++){
            answer = Math.max(answer, dp1[i]+dp2[i]+1);
        }
        System.out.println(answer);
    }
}
