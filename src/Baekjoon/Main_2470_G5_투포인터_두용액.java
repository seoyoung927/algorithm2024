import java.util.*;
import java.io.*;

//324ms
public class Main_2470_G5_투포인터_두용액 {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        //0. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        //1. 정렬
        Arrays.sort(arr);

        //2. 투 포인터
        //System.out.println(Arrays.toString(arr));
        int best = Integer.MAX_VALUE; //두 용액의 특성값
        int n1 = arr[0];
        int n2 = arr[N-1];
        int left = 0;
        for(int right=N-1; right>=0; right--){
            if(left>=right) break;

            int tmpBest = Math.abs(arr[right]+arr[left]);
            while(left<right){
                if(Math.abs(arr[right]+arr[left])<=tmpBest){
                    tmpBest = Math.min(tmpBest, Math.abs(arr[right]+arr[left]));
                }else{
                    break;
                }
                left++;
            }
            left = left == 0 ? 0 : left - 1;
            if(best>tmpBest){
                best = tmpBest;
                n1 = arr[left];
                n2 = arr[right];
            }
            //System.out.println("left="+left+",right="+right+",best="+best);
        }

        //3. 출력
        System.out.printf("%d %d", n1, n2);
    }
}
