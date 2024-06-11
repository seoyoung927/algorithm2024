package CumulativeSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//512ms
public class B10986 {
    static int N,M;
    static int[] numbers;
    static int[] remains;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) numbers[i] = Integer.parseInt(st.nextToken());

        //2. 누적합 배열
        int s = 0;
        remains = new int[M];
        for(int i=0; i<N; i++) {
            s += numbers[i];
            s = s % M;
            remains[s]++;
        }

        //3. 1~N까지 반복
        answer = remains[0];
        for(int i=0; i<M; i++){
            int size = remains[i];
            long combi = (long) size * (size-1) / 2;
            answer+=combi;
        }

        //4. 출력
        System.out.println(answer);
    }
}
