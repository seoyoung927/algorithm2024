package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 왕실기사의반란 {
    static int L,N,Q;
    static int[][] board;
    static int[][] board_knights;
    static Knight[] knights;
    static int[] dr = {-1,0,1,0}; //상 우 하 좌
    static int[] dc = {0,1,0,-1}; //상 우 하 좌
    static ArrayList<Knight> players = new ArrayList<>();

    static class Knight{
        int r,c,h,w,k;
        int damage;

        public Knight(int r, int c, int h, int w, int k) {
            this.r=r;
            this.c=c;
            this.h=h;
            this.w=w;
            this.k=k;
            this.damage=0;
        }
    }

    public static boolean canMove(int knightIdx, int direction) {
        boolean result = true;
        Knight knight = knights[knightIdx];

        for(int rr=0; rr<knight.h; rr++) {
            for(int cc=0; cc<knight.w; cc++) {
                int nextR=knight.r+rr+dr[direction];
                int nextC=knight.c+cc+dc[direction];
                if(nextR<=0 || nextR>L || nextC<=0 || nextC>L) return false;
                if(board[nextR][nextC]==2) return false;
                if(board_knights[nextR][nextC]>0 && board_knights[nextR][nextC]!=knightIdx) {
                    result &= canMove(board_knights[nextR][nextC],direction);
                    if(!result) break;
                }
            }
        }

        return result;
    }

    public static void move(boolean directCommand, int knightIdx, int direction) {
        Knight knight = knights[knightIdx];

        for(int rr=0; rr<knight.h; rr++) {
            for(int cc=0; cc<knight.w; cc++) {
                board_knights[knight.r+rr][knight.c+cc] = 0;
            }
        }
        for(int rr=0; rr<knight.h; rr++) {
            for(int cc=0; cc<knight.w; cc++) {
                int nextR=knight.r+rr+dr[direction];
                int nextC=knight.c+cc+dc[direction];

                if(board_knights[nextR][nextC]>0 && board_knights[nextR][nextC]!=knightIdx) {
                    move(false, board_knights[nextR][nextC],direction);
                }

                board_knights[nextR][nextC] = knightIdx;

                if(board[nextR][nextC]==1 && !directCommand) {
                    knights[knightIdx].damage+=1;
                }
            }
        }
        knights[knightIdx].r+=dr[direction];
        knights[knightIdx].c+=dc[direction];

        if(knights[knightIdx].damage>=knights[knightIdx].k) {
            for(int rr=0; rr<knight.h; rr++) {
                for(int cc=0; cc<knight.w; cc++) {
                    board_knights[knight.r+rr][knight.c+cc] = 0;
                }
            }
            players.remove(knights[knightIdx]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        board=new int[L+1][L+1];
        for(int i=1; i<L+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<L+1; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        knights = new Knight[N+1];
        board_knights = new int[L+1][L+1];
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for(int rr=0; rr<h; rr++) {
                for(int cc=0; cc<w; cc++) {
                    board_knights[r+rr][c+cc] = i;
                }
            }
            knights[i] = new Knight(r,c,h,w,k);
            players.add(knights[i]);
        }

        for(int q=0; q<Q; q++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            //이미 사라진 기사번호일 경우
            if(!players.contains(knights[i])) continue;

            if(canMove(i, d)) {
                move(true,i,d);
            }
        }

        int s=0;
        for(Knight knight : players) {
            s+=knight.damage;
        }

        System.out.println(s);
    }

}