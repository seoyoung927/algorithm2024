package Simulation;

import java.util.Scanner;
import java.lang.StringBuilder;

public class Main_1913_S3_달팽이_조서영 {
    static int N;
    static int target;
    static int[][] board;
    static int[] dr = {1,0,-1,0}; //하우상좌
    static int[] dc = {0,1,0,-1}; //하우상좌
    static int targetR;
    static int targetC;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        N = sc.nextInt();
        target = sc.nextInt();
        board = new int[N][N];

        int n = N*N;
        int i = 0;
        int cr = 0;
        int cc = 0;
        targetR = -1;
        targetC = -1;
        while(n>0){
            if(n==target){
                targetR = cr+1;
                targetC = cc+1;
            }
            board[cr][cc]=n;

            cr+=dr[i];
            cc+=dc[i];
            if(cr<0 || cr>=N || cc<0 || cc>=N || board[cr][cc]!=0){
                cr-=dr[i];
                cc-=dc[i];
                i=(i+1)%4;
                cr+=dr[i];
                cc+=dc[i];
            }
            n-=1;
        }

        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                sb.append(board[r][c]+" ");
            }
            sb.append("\n");
        }
        sb.append(targetR+" "+targetC);
        System.out.println(sb);

        sc.close();
    }
}
