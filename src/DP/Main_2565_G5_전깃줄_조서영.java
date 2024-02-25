package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2565_G5_전깃줄_조서영 {
    static int N; //전깃줄의 개수
    static ArrayList<Integer[]> lines = new ArrayList<>();
    static int[] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            lines.add(new Integer[] {from,to});
        }
        lines.sort(Comparator.comparingInt(o1->o1[0]));

        DP=new int[N];
        int ans=0;
        for(int i=0;i<N;i++){
            int maxN = 0;
            for(int j=0;j<i;j++){
                if(lines.get(j)[1]<lines.get(i)[1]) maxN=Math.max(maxN, DP[j]);
            }
            ans=Math.max(ans, DP[i]=maxN+1);
        }

        System.out.println(N-ans);
    }
}
