package seoyoung.day0408;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Arrays;

public class 왕실의기사대결 {
    static int L,N,Q; //L: 체스판의 크기, N: 기사의 수, Q: 명령의 수
    static int[][] board; //체스판에 대한 정보, 0:빈칸/1:함정/2:벽
    static Knight[][] knightBoard;
    static Knight[] knights;
    static int[] dr = {-1,0,1,0}; //상우하좌
    static int[] dc = {0,1,0,-1}; //상우하좌

    static class Knight{
        int idx;
        int r,c,h,w,k; //r,c: 좌측 꼭짓점, h: 세로 길이, w: 가로 길이, k: 초기 체력
        public Knight(int idx, int r, int c, int h, int w, int k){
            this.idx=idx;
            this.r=r;
            this.c=c;
            this.h=h;
            this.w=w;
            this.k=k;
        }
        public String toString(){
            return "나 기사야~"+this.idx;
        }
    }

    //기사가 이동할 수 있는지 확인: idx기사가 dr, dc 방향으로 nr, nc크기만큼 밀려나게 됨
    //이동하면서 벽을 만나면 갈 수 없다.
    //이동하려는 위치에 다른 기사가 있다면 그 기사도 함께 연쇄적으로 밀려난다.
    public static boolean checkMoveKnights(Knight knight, int nr, int nc, int dr, int dc){
        boolean result = true;

        HashSet<Knight> sset = new HashSet<>(); //이동하면서 만난 기사들
        int r = knight.r;
        int c = knight.c;
        int h = knight.h;
        int w = knight.w;
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                int nextR = r+i+nr*dr;
                int nextC = c+j+nc*dc;
                //이동 중에 벽을 만난 경우
                if(nextR<=0 || nextR>L || nextC<=0 || nextC>L){
                    result = false;
                    break;
                }
                if(board[nextR][nextC]==2){
                    result=false;
                    break;
                }
                //이동 중에 다른 기사를 만난 경우
                if(knightBoard[nextR][nextC]!=null && knightBoard[nextR][nextC]!=knight) sset.add(knightBoard[nextR][nextC]);
            }
        }

        System.out.println(sset);
        if(result){ //이동할 수 있음
            for(Knight next : sset){
                result &= checkMoveKnights(next, 1, 1, dr, dc);
            }
        }

        return result;
    }

    //기사가 이동할 수 있다면 => 기사를 이동시킴: idx기사가 dr, dc 방향으로 nr, nc크기만큼 밀려나게 됨
    //이동하면서 벽을 만나면 갈 수 없다.
    //이동하려는 위치에 다른 기사가 있다면 그 기사도 함께 연쇄적으로 밀려난다.
    public static void moveKnights(Knight knight, int nr, int nc, int dr, int dc){
        HashSet<Knight> sset = new HashSet<>(); //이동하면서 만난 기사들
        
        int r = knight.r;
        int c = knight.c;
        int h = knight.h;
        int w = knight.w;
        knight.r = r+nr*dr;
        knight.c = c+nc*dc;
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                //예전 위치
                int cr = r+i;
                int cc = c+j;
                //System.out.println(cr+" "+cc);
                //if(knightBoard[cr][cc]==knight) knightBoard[cr][cc]=null;
                //새로운 위치로 옮김
                int nextR = r+i+nr*dr;
                int nextC = c+j+nc*dc;
                System.out.println(nextR+" "+nextC);
                //knightBoard[nextR][nextC] = knight;
                //이동 중에 다른 기사를 만난 경우
                if(knightBoard[nextR][nextC]!=null && knightBoard[nextR][nextC]!=knight) {
                    System.out.println("dd"+nextR+" "+nextC);
                    sset.add(knightBoard[nextR][nextC]);
                }
            }
        }

        System.out.println(sset);
        for(Knight next : sset){
            moveKnights(next, 1, 1, dr, dc);
        }
    }

    //1.    왕에게 명령을 받은 기사가 밀려남
    //2.    이동하려는 위치에 다른 기사가 있다면 그 기사도 함께 연쇄적으로 밀려남
    //      단, 이동하려는 곳의 끝에 벽이 있다면 아무런 반응이 없게 된다.
    //3.    밀려난 기사는 모두 밀린 후에 w*h 직사각형 내에 놓은 함정의 수만큼 피해를 입게 된다.
    public static void main(String[] args) throws Exception{
        //1. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        
        //1-1. L개의 줄에 거쳐서 체스판에 대한 정보가 주어짐
        board = new int[L+1][L+1];
        for(int r=1; r<L+1; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<L+1; c++){
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        //1-2. N개의 줄에 걸쳐 초기 기사들의 정보가 주어짐
        knightBoard = new Knight[L+1][L+1];
        knights = new Knight[N+1];
        for(int idx=1; idx<N+1; idx++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            knights[idx] = new Knight(idx,r,c,h,w,k);
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    knightBoard[r+i][c+j] = knights[idx];
                }
            }
        }

        //1-3. Q개의 줄에 걸쳐 명령이 주어짐
        System.out.println("명령시작====");
        for(int q=0; q<Q; q++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if(checkMoveKnights(knights[i],1,1,dr[d],dc[d])){
                System.out.println("move+ "+i);
                moveKnights(knights[i],1,1,dr[d],dc[d]);
            }

            for(int r=0; r<L+1; r++){
                System.out.println(Arrays.toString(knightBoard[r]));
            }
        }
    }
}