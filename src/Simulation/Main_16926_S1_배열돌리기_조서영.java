package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1556ms
public class Main_16926_S1_배열돌리기_조서영 {
    static int N,M,R; //N,M: 배열의 크기, R: 회전의 수
    static int[][] board;

    public static void rotate(){
        int rStart = 0;
        int rEnd = N-1;
        int cStart = 0;
        int cEnd = M-1;
        while(rStart<=rEnd && cStart<=cEnd){
            int tmp = board[rStart][cStart];
            int lastR = -1;
            int lastC = -1;
            for(int c=cStart; c<cEnd; c++){
                board[rStart][c]=board[rStart][c+1];
            }
            for(int r=rStart; r<rEnd; r++){
                board[r][cEnd]=board[r+1][cEnd];
            }
            for(int c=cEnd; c>cStart; c--){
                board[rEnd][c]=board[rEnd][c-1];
                if(c==cStart+1){
                    lastR=rEnd;
                    lastC=c;
                }
            }
            for(int r=rEnd; r>rStart; r--) {
                board[r][cStart] = board[r-1][cStart];
                if(r==rStart+1){
                    lastR=r;
                    lastC=cStart;
                }
            }
            board[lastR][lastC]=tmp;
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

        for(int i=0; i<R; i++) rotate();

        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                System.out.print(board[r][c]+" ");
            }
            System.out.println();
        }

    }
}
