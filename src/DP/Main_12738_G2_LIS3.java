package DP;

//616ms
import java.io.*;
import java.util.*;

public class Main_12738_G2_LIS3 {
    static int N;
    static int[] A;
    static int[] dp;

    public static int binarySearch(List<Integer> arr, int target){
        int left = 0;
        int right = arr.size()-1;

        while(left<=right){
            int mid = (left+right)/2;

            if(arr.get(mid)>=target){
                right = mid-1;
            }else if(target>arr.get(mid)){
                left = mid+1;
            }
        }

        return left;
    }

    public static void main(String[] args) throws IOException {
        //0. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());

        //1. 로직
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<N; i++){
            int pos = binarySearch(list, A[i]);

            // pos 위치에 값 업데이트 또는 새로운 값 추가
            if (pos < list.size()) {
                list.set(pos, A[i]);
            } else {
                list.add(A[i]);
            }

        }

        //2. 출력
        System.out.println(list.size());
    }
}
