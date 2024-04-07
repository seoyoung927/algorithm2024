package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 루돌프의반란 {
    static int N,M,P,C,D;
    static Point rudolph;
    static Santa[] santas;
    static int[] drr = {-1,-1,0,1,1,1,0,-1}; //상,우상,우,우하,하,좌하,좌,좌상
    static int[] drc = {0,1,1,1,0,-1,-1,-1}; //상,우상,우,우하,하,좌하,좌,좌상
    static Santa[][] board;
    static int[] dsr = {0,1,0,-1}; //좌하우상
    static int[] dsc = {-1,0,1,0}; //좌하우상

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r=r;
            this.c=c;
        }
    }

    static class Santa extends Point implements Comparable<Santa>{
        int idx;
        int score;
        int availableTurn; //기절 sleepCnt가 0일 때 산타는 움직일 수 있음
        boolean isAlive;
        public Santa(int idx, int r, int c, int score, int availableTurn, boolean isAlive){
            super(r,c);
            this.idx=idx;
            this.score=score;
            this.availableTurn=availableTurn;
            this.isAlive=isAlive;
        }
        @Override
        public int compareTo(Santa o){
            //r을 기준으로 내림차순 정렬, r이 같이면 c로 내림차순 정렬
            if(this.r==o.r) return Integer.compare(o.c,this.c);
            return Integer.compare(o.r, this.r);
        }
        @Override
        public String toString(){
            return "idx: "+this.idx
                    +", r: "+this.r
                    +" ,c: "+this.c
                    +", score: "+this.score
                    +", availableTurn: "+this.availableTurn
                    + ", isAlive: "+this.isAlive;
        }
    }

    //루돌프에서 가장 가까운 산타를 찾음
    public static Santa getNearestSanta(){
        ArrayList<Santa> tmp = new ArrayList<>();
        int minVal = Integer.MAX_VALUE;
        for(int i=1; i<P+1; i++){
            Santa santa = santas[i];
            if(santa.isAlive==false) continue;
            int dist = getDist(rudolph, santa);
            if(dist<minVal){
                tmp.clear();
                tmp.add(santa);
                minVal = dist;
            }else if(dist==minVal){
                tmp.add(santa);
            }
        }
        Collections.sort(tmp);
        if(tmp.size()==0) return null;
        //System.out.println(tmp);
        return tmp.get(0);
    }

    //가장 가까운 산타를 향해 어느 방향으로 돌진할지 결정
    public static int getBestRudolphDirection(Santa santa){
        int result = -1;
        int minVal = getDist(rudolph, santa);
        for(int i=0; i<8; i++){
            int nrr = rudolph.r + drr[i];
            int nrc = rudolph.c + drc[i];
            if(nrr<=0 || nrr>N || nrc<=0 || nrc>N) continue;
            int newVal = getDist(new Point(nrr, nrc), santa);
            if(newVal<minVal){
                minVal = newVal;
                result = i;
            }
        }
        return result;
    }

    //해당 santa가 루돌프와 가장 가까워지기 위해서 어느방향으로 돌진해야하는지
    //만약 가까워질 수 있는 방법이 없다면 -1 return
    //산타는 다른 산타가 있는 칸이나 게임판 밖으로는 움직일 수 없다.
    public static int getBestSantaDirection(Santa santa){
        int result = -1;
        int minVal = getDist(rudolph, santa); //초기
        for(int i=0; i<4; i++){
            int nsr = santa.r + dsr[i];
            int nsc = santa.c + dsc[i];
            if(nsr<=0 || nsr>N || nsc<=0 || nsc>N) continue; //산타는 게임판 밖으로는 움직일 수 없다.
            if(board[nsr][nsc]!=null) continue; //산타는 다른 산타가 있는 칸으로는 움직일 수 없다.
            int newVal = getDist(rudolph, new Point(nsr, nsc));
            if(newVal<=minVal){
                minVal = newVal;
                result = i;
            }
        }
        return result;
    }

    //Santa를 r방향으로 nr만큼, c방향으로 nc만큼 d방향만큼 이동시키는 함수
    public static void moveSanta(Santa santa, int nr, int nc, int dr, int dc){
        //산타로 이루어진 board를 수정해야 하고
        //Santa 배열을 수정해야함
        //이동하려는 위치에 또 산타가 있다면 해당 산타로 이동시켜야함
        //게임판밖으로 산타가 밀려난 경우 처리해야함
        int nsr = santa.r+dr*nr;
        int nsc = santa.c+dc*nc;
        //1. 이동하려는 위치가 게임판밖인 경우
        if(nsr<=0 || nsr>N || nsc<=0 || nsc>N){
            board[santa.r][santa.c] = null;
            santa.isAlive = false;
            return;
        }
        //2. 이동하려는 위치에 또 산타가 있는 경우
        if(board[nsr][nsc]!=null){
            Santa interactionSanta = board[nsr][nsc]; //상호작용이 발생하는 산타
            if(interactionSanta.isAlive==true) moveSanta(interactionSanta, 1, 1, dr, dc);
        }
        board[santa.r][santa.c] = null;
        board[nsr][nsc] = santa;
        santa.r = nsr;
        santa.c = nsc;
        return;
    }

    public static int getDist(Point p1, Point p2){
        return (int) (Math.pow(p1.r-p2.r,2)+ Math.pow(p1.c-p2.c,2));
    }

    public static void main(String[] args) throws Exception {
        //1. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //게임판의 크기
        M = Integer.parseInt(st.nextToken()); //게임 턴 수
        P = Integer.parseInt(st.nextToken()); //산타의 수
        C = Integer.parseInt(st.nextToken()); //루돌프의 힘
        D = Integer.parseInt(st.nextToken()); //산타의 힘

        //루돌프의 초기 위치
        st = new StringTokenizer(br.readLine());
        int Rr = Integer.parseInt(st.nextToken()); //루돌프의 초기 위치 r
        int Rc = Integer.parseInt(st.nextToken()); //루돌프의 초기 위치 c
        rudolph = new Point(Rr, Rc);

        //P개의 줄에 거쳐 산타의 번호와 초기위치가 공백을 두고 주어진다.
        board = new Santa[N+1][N+1];
        santas = new Santa[P+1]; // -> idx가 1부터 주어지므로
        for(int i=0; i<P; i++){
            st = new StringTokenizer(br.readLine());
            int Pn = Integer.parseInt(st.nextToken());
            int Sr = Integer.parseInt(st.nextToken());
            int Sc = Integer.parseInt(st.nextToken());
            santas[Pn] = new Santa(Pn, Sr, Sc,0,0,true);
            board[Sr][Sc] = santas[Pn];
        }

        //2.
        //M번의 turn에 걸쳐 루돌프, 산타가 순서대로 움직인다.
        for(int turn = 0; turn<M; turn++){
            //루돌프의 턴
            Santa targetSanta = getNearestSanta();
            if(targetSanta!=null){
                int bestRDirection = getBestRudolphDirection(targetSanta);
                int nrr = rudolph.r + drr[bestRDirection];
                int nrc = rudolph.c + drc[bestRDirection];
                if(board[nrr][nrc]!=null){
                    //루돌프가 움직여 산타와 충돌 발생
                    //C만큼의 점수를 얻고
                    //C칸 만큼 밀려나게 됨
                    //기절 cnt + 2
                    Santa collisionSanta = board[nrr][nrc];
                    collisionSanta.score+=C;
                    collisionSanta.availableTurn=turn+2;
                    moveSanta(collisionSanta, C, C, drr[bestRDirection], drc[bestRDirection]);
                }
                rudolph.r = nrr;
                rudolph.c = nrc;
            }
            //산타의 턴
            //기절한 산타는 움직일 수 없음
            for(int i=1; i<P+1; i++){
                Santa santa = santas[i];
                if(santa.isAlive==false) continue; //이미 탈락한 산타는 움직일 수 없음
                if(santa.availableTurn>turn) continue; //기절한 산타는 움직일 수 없음

                int bestSDirection = getBestSantaDirection(santa);
                if(bestSDirection==-1) continue; //움직일 수 없는 경우 다음으로
                int nsr = santa.r + dsr[bestSDirection];
                int nsc = santa.c + dsc[bestSDirection];
                //산타가 움직이려는 곳에 루돌프가 있는 경우
                //D점을 얻고 D만큼 반대방향으로 밀려나게 됨
                board[santa.r][santa.c]=null;
                if(nsr==rudolph.r && nsc==rudolph.c){
                    santa.score+=D;
                    santa.availableTurn=turn+2;
                    santa.r=rudolph.r;
                    santa.c=rudolph.c;
                    moveSanta(santa, D, D, dsr[bestSDirection]*(-1), dsc[bestSDirection]*(-1));
                }else{
                    board[nsr][nsc] = santa;
                    santa.r = nsr;
                    santa.c = nsc;
                }
            }
            //매턴 이후 아직 탈락하지 않은 산타들에게는 1점씩 추가로 부여
            for(int i=1; i<P+1; i++){
                Santa santa = santas[i];
                if(santa.isAlive==false) continue;
                santa.score+=1;
            }
        }

        //3. 최종결과 확인
        //System.out.println("최종 결과 확인: ");
        for(int i=1; i<P+1; i++){
            System.out.print(santas[i].score+" ");
        }
        //System.out.println(Arrays.toString(santas));
    }
}