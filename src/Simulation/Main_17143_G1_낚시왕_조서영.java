package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_17143_G1_낚시왕_조서영 {
    static int R,C,M;
    static Shark[][] board;
    static int result;
    static ArrayList<Shark> sharks;
    static int[] dr = {0,-1,1,0,0}; //상하우좌
    static int[] dc = {0,0,0,1,-1}; //상하우좌

    static class Shark implements Comparable<Shark>{
        int r,c,s,d,z;
        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
        @Override
        public int compareTo(Shark o) {
            return Integer.compare(this.z,o.z);
        }
        @Override
        public boolean equals(Object o){
            if(this==o) return true;
            if(o==null | this.getClass()!=o.getClass()) return false;
            Shark other = (Shark) o;
            return this.r==other.r && this.c==other.c && this.s==other.s && this.d==other.d && this.z==other.z;
        }
    }

    public static void captureShark(int c){
        for(int r=1; r<=R; r++){
            if(board[r][c]==null) continue;
            Shark capturedShark = board[r][c];
            result += capturedShark.z;
            board[r][c] = null;
            sharks.remove(capturedShark);
            break;
        }
    }

    public static void moveShark(){
        Shark[][] tmp = new Shark[R+1][C+1];
        ArrayList<Shark> eatenSharks = new ArrayList<>();
        for(Shark shark : sharks){
            int s = shark.s;
            int nr = shark.r;
            int nc = shark.c;
            for(int t=0; t<s; t++){
                nr += dr[shark.d];
                nc += dc[shark.d];
                if(nr<=0 || nr>R || nc<=0 || nc>C){
                    nr -= dr[shark.d];
                    nc -= dc[shark.d];
                    //방향을 바꿈
                    if(shark.d==1) shark.d=2;
                    else if(shark.d==2) shark.d=1;
                    else if(shark.d==3) shark.d=4;
                    else if(shark.d==4) shark.d=3;
                    nr += dr[shark.d];
                    nc += dc[shark.d];
                }
            }
            if(tmp[nr][nc]!=null) eatenSharks.add(tmp[nr][nc]);
            shark.r = nr;
            shark.c = nc;
            tmp[nr][nc]=shark;
        }
        board = tmp;
        for(Shark eatenShark : eatenSharks) {
            int idx = sharks.indexOf(eatenShark);
            sharks.remove(idx);
        }
    }

    //1. shark를 잡는다.
    //2. shark가 이동한다.
    public static void main(String[] args) throws IOException {
        //1. 입력
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new Shark[R+1][C+1];
        sharks = new ArrayList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            board[r][c] = new Shark(r,c,s,d,z);
            sharks.add(board[r][c]);
        }
        Collections.sort(sharks);

        //2.
        result = 0;
        for(int t=1; t<=C; t++){
            captureShark(t);
            moveShark();
        }

        //3. 결과 출력
        System.out.println(result);
    }
}
