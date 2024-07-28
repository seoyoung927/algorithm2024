import java.io.*;
import java.util.*;

public class Main_14719_G5_빗물 {
    static int H,W;
    static int[] block;

    public static void main(String[] args) throws IOException{
        //1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        block = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++){
            block[i] = Integer.parseInt(st.nextToken());
        }

        //2.
        int answer = 0;
        int left = 0;
        for(int i=0; i<W; i++){
            if(i==0) continue;

            if(block[i]>block[i-1]){
                int n = Math.min(block[left], block[i]);
                for(int j=left+1; j<i; j++){
                    if(block[j]<n){
                        answer+=n-block[j];
                        block[j]=n;
                    }
                }
                if(block[i]>=block[left]) left = i;
            }
        }

        //3. 출력
        System.out.println(answer);
    }
}
