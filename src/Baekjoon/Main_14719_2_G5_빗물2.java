import java.util.*;
import java.io.*;

// 108ms
public class Main_14719_2_G5_빗물2 {
    static int H, W;
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

        //2. 로직 전체 면적  - 블록의 면적
        int highest = 0;
        for(int i=0; i<W; i++){
            if(block[i]>=block[highest]) highest=i;
        }
        int s = block[highest];
        int left = 0;
        for(int i=1; i<=highest; i++){
            if(block[i]>=block[left]){
                int n = Math.min(block[i],block[left]);
                s+=(n*(i-left));
                left = i;
            }
        }
        int right = W-1;
        for(int i=W-2; i>=highest; i--){
            if(block[i]>=block[right]){
                int n = Math.min(block[i],block[right]);
                s+=(n*(right-i));
                right = i;
            }
        }
        for(int i=0; i<W; i++){
            s-=block[i];
        }
        //3. 출력
        System.out.println(s);
    }
}
