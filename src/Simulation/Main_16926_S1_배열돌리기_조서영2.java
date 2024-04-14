package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1856ms
public class Main_16926_S1_배열돌리기_조서영2 {
    static int N,M,R;
    static int[][] board;

    public static void rotate(){
        int[][] copy = new int[N][M];
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                copy[r][c]=board[r][c];
            }
        }

        int rStart = 0;
        int rEnd = N-1;
        int cStart = 0;
        int cEnd = M-1;
        while(rStart<=rEnd && cStart<=cEnd){
            for(int c=cStart; c<cEnd; c++){
                board[rStart][c]=copy[rStart][c+1];
            }
            for(int r=rStart; r<rEnd; r++){
                board[r][cEnd]=copy[r+1][cEnd];
            }
            for(int c=cEnd; c>cStart; c--){
                board[rEnd][c]=copy[rEnd][c-1];
            }
            for(int r=rEnd; r>rStart; r--){
                board[r][cStart]=copy[r-1][cStart];
            }
            rStart+=1;
            rEnd-=1;
            cStart+=1;
            cEnd-=1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++){
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<R; i++) {
            rotate();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }
}
