package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5656_모의_벽돌깨기_조서영 {
    static int N,W,H;
    static int[][] board;
    static int[] combi;
    static int[] dr = {-1,1,0,0}; //상하좌우
    static int[] dc = {0,0,-1,1};
    static int result;

    public static void getCombi(int depth){
        if(depth>=N){
            int[][] copy = new int[H][W];
            for(int i=0; i<H; i++){
                for(int j=0; j<W; j++){
                    copy[i][j] = board[i][j];
                }
            }

            for(int j=0; j<N; j++){
                for(int i=0; i<H; i++){
                    if(copy[i][combi[j]]!=0){
                        removeBlock(copy, i, combi[j]);
                        sortBlock(copy);
                        break;
                    }
                }
            }

            int cnt = 0;
            for(int i=0; i<H; i++) {
                for (int j = 0; j < W; j++) {
                    if (copy[i][j] > 0) {
                        cnt += 1;
                    }
                }
            }

            result = Math.min(result, cnt);

            return;
        }

        for(int i=0; i<W; i++){
            combi[depth] = i;
            getCombi(depth+1);
        }
    }

    public static void removeBlock(int[][] b, int r, int c){
        int power = b[r][c];
        b[r][c] = 0;
        int nextR, nextC;
        //block삭제
        for(int p=1; p<power; p++) {
            for (int i = 0; i < 4; i++) {
                nextR = r + dr[i] * p;
                nextC = c + dc[i] * p;
                if (nextR < 0 || nextR >= H || nextC < 0 || nextC >= W) continue;
                if (b[nextR][nextC] != 0) removeBlock(b, nextR, nextC);
            }
        }
    }

    public static void sortBlock(int[][] b){
        for(int j=0; j<W; j++){
            ArrayList<Integer> nums = new ArrayList<>();
            for(int i=H-1; i>=0; i--){
                if(b[i][j]!=0) nums.add(b[i][j]);
            }
            int idx = 0;
            for(int i=H-1; i>= H-nums.size(); i--){
                b[i][j] = nums.get(idx++);
            }
            for(int i=H-nums.size()-1; i>=0; i--) b[i][j]=0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            board = new int[H][W];
            combi = new int[N];
            for(int r=0; r<H; r++){
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<W; c++){
                    board[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            result = Integer.MAX_VALUE;
            getCombi(0);
            System.out.printf("#%d %d%n",t,result);
;        }
    }
}
