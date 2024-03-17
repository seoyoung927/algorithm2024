package HSAT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 자동차테스트 {
    static int n,q; //n개의 자동차, q개의 질의
    static int cars[];
    static int result;

    public static int binarySearch(int target){
        int result = -1;

        int high = cars.length-1;
        int low = 0;
        while(low<=high){
            int mid = (high+low)/2;
            if(cars[mid]==target){
                result = mid;
                break;
            }
            else if(cars[mid]>target){
                high = mid-1;
            }
            else{ //cars[mid]<target
                low = mid+1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        cars = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=-0;i<n;i++) cars[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(cars);

        for(int i=0;i<q;i++){
            int target = Integer.parseInt(br.readLine());
            int targetIdx = binarySearch(target);

            if(targetIdx==-1) System.out.println(0);
            else System.out.println(targetIdx*(n-targetIdx-1));
        }
    }
}
