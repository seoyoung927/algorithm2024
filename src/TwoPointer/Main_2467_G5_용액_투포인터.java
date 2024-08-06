import java.util.*;
import java.io.*;

// 308ms, 투포인터, O(N)
public class Main_2467_G5_용액_투포인터 {
    static int N;
    static int[] liquid;

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        liquid = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 투 포인터
        long answer = Long.MAX_VALUE;
        int answerLeft = 0;
        int answerRight = 0;
        int left = 0;
        int right = N-1;
        while(left<right){
            long s = (long)liquid[left]+(long)liquid[right];
            if(Math.abs(s)<=answer){
                answer = Math.abs(s);
                answerLeft = left;
                answerRight = right;
            }
            if(s>0) right--;
            if(s<=0) left++;
        }

        // 3. 출력
        System.out.printf("%d %d", liquid[answerLeft], liquid[answerRight]);
    }
}
