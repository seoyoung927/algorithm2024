package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Rudolph_Rebellion {
    static int N,M,P,C,D;
    static Point rudolph;
    static Santa[] santas;
    static int[] drr = {-1,0,1,0,-1,1,1,-1}; //상 우 하 좌 상우 우하 하좌 상좌
    static int[] drc = {0,1,0,-1,1,1,-1,-1}; //상 우 하 좌 상우 우하 하좌 상좌
    static int[] dsr = {-1,0,1,0}; //상 우 하 좌
    static int[] dsc = {0,1,0,-1}; //상 우 하 좌
    static int[][] board;
    static ArrayList<Santa> players;

    static class Point{
        int r,c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static class Santa extends Point implements Comparable{
        int index;
        int score;
        int availableTurn;

        public Santa(int index, int r, int c){
            super(r,c);
            this.index=index;
            this.score=0;
            this.availableTurn=-1;
        }

        @Override
        public int compareTo(Object o) {
            Santa s = (Santa) o;
            if(this.r==s.r) return Integer.compare(s.c,this.c);
            return Integer.compare(s.r,this.r);
        }

        @Override
        public String toString() {
            return "r: "+r+", c: "+c+", score: "+score+", avt: "+availableTurn;
        }
    }

    public static int getDist(Point p1, Point p2){
        return ((int) Math.pow(p1.r-p2.r,2)+(int)Math.pow(p1.c-p2.c,2));
    }

    public static int getNearestSanta(){
        ArrayList<Santa> candidates = new ArrayList<>();

        int minDist = Integer.MAX_VALUE;
        for(int i=0, size = players.size(); i<size; i++){
            Santa santa = players.get(i);
            int curDist = getDist(rudolph, santa);
            if(curDist<minDist){
                candidates.clear();
                candidates.add(santa);
                minDist=curDist;
            }else if(minDist==curDist){
                candidates.add(santa);
            }
        }

        Collections.sort(candidates);

        return candidates.get(0).index;
    }

    public static int getRudolphDirection(int santaIdx){
        Santa santa = santas[santaIdx];
        ArrayList<Integer> candidates = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;
        for(int i=0; i<8; i++){
            int nextRr = rudolph.r + drr[i];
            int nextRc = rudolph.c + drc[i];
            if(nextRr<=0 || nextRr>N || nextRc<=0 || nextRc>N) continue;
            int curDist = getDist(new Point(nextRr,nextRc), santa);
            if(curDist<minDist) {
                candidates.clear();
                minDist = curDist;
                candidates.add(i);
            }else if(curDist==minDist){
                candidates.add(i);
            }
        }

        if(candidates.size()==0) return -1;
        return candidates.get(0);
    }

    public static int getSantaDirection(int santaIdx){
        Santa santa = santas[santaIdx];

        int minDist = getDist(rudolph, santa);
        int index = -1;
        for(int i=0; i<4; i++){
            int nextSr = santa.r+dsr[i];
            int nextSc = santa.c+dsc[i];
            if(nextSr<=0 || nextSr>N || nextSc<=0 || nextSc>N) continue;
            if(board[nextSr][nextSc]>0) continue;
            int curDist = getDist(rudolph, new Point(nextSr,nextSc));
            if(curDist<minDist){
                minDist = curDist;
                index = i;
            }
        }

        return index;
    }

    public static void moveSanta(int index, int curSr, int curSc, int moveR, int moveC, int direction){
        //board[curSr][curSc] = 0;
        int nextSr = curSr + moveR;
        int nextSc = curSc + moveC;

        if(nextSr<=0 || nextSr>N || nextSc<=0 || nextSc>N){ //board밖으로 나가면 제거
            players.remove(santas[index]);
            return;
        }

        if(board[nextSr][nextSc]!=0){ //움직일 위치에 또 산타가 서 있다면
            int moveSantaIdx = board[nextSr][nextSc]; //움직일 위치에 있는 santa index;
            //산타 이동
            moveSanta(moveSantaIdx,nextSr,nextSc,drr[direction],drc[direction],direction);
        }
        board[nextSr][nextSc] = index;
        santas[index].r = nextSr;
        santas[index].c = nextSc;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[N+1][N+1];
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        rudolph = new Point(r,c);
        board[r][c] = -1;

        santas = new Santa[P+1];
        players = new ArrayList<>();
        for(int i=0; i<P ;i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            santas[idx] = new Santa(idx, r, c);
            board[r][c] = idx;
            players.add(santas[idx]);
        }

        //2. M turn 반복
        for(int t=0; t<M; t++){
            if(players.size()==0) break;
            //2-1.루돌프
            int bestSanta = getNearestSanta();

            int bestDirectionRudolph = getRudolphDirection(bestSanta);
            int curRr = rudolph.r;
            int curRc = rudolph.c;
            int nextRr = curRr + drr[bestDirectionRudolph];
            int nextRc = curRc + drc[bestDirectionRudolph];
            if(!(nextRr<=0 || nextRr>N || nextRc<=0 || nextRc>N)){ //이 조건식 자체는 꼭 필요한 것은 아닌 것 같지만 어쨌든
                board[curRr][curRc] = 0;
                if(board[nextRr][nextRc]!=0){
                    int moveSantaIdx = board[nextRr][nextRc]; //움직일 위치에 있는 santa index;
                    santas[moveSantaIdx].score+=C;
                    santas[moveSantaIdx].availableTurn=t+1;
                    //산타 이동
                    moveSanta(moveSantaIdx, nextRr, nextRc,drr[bestDirectionRudolph]*C,drc[bestDirectionRudolph]*C, bestDirectionRudolph);
                }
                board[nextRr][nextRc] = -1;
                rudolph.r = nextRr;
                rudolph.c = nextRc;
            }

            //2-2. 산타
            for(int p=1; p<P+1; p++){
                if(!players.contains(santas[p])) continue; //이미 벽 밖으로 나간 산타라면

                Santa santa = santas[p];

                if(santa.availableTurn >= t) continue; //산타가 기절한 상태라면

                int bestDirectionSanta = getSantaDirection(santa.index);

                if(bestDirectionSanta==-1) continue; //산타가 움직일 수 없다면

                int curSr = santa.r;
                int curSc = santa.c;
                int nextSr = curSr + dsr[bestDirectionSanta];
                int nextSc = curSc + dsc[bestDirectionSanta];

                if(!(nextSr<=0 || nextSr>N || nextSc<=0 || nextSc>N)) { //이 조건식 자체는 꼭 필요한 것은 아닌 것 같지만 어쨌든
                    //산타이동
                    board[curSr][curSc] = 0;
                    if (board[nextSr][nextSc] == -1) { //움직일 위치에 루돌프가 위치했다면
                        santas[santa.index].score+=D;
                        santas[santa.index].availableTurn=t+1;

                        //산타 이동
                        int direction = -1;
                        if (bestDirectionSanta == 0) direction = 2;
                        else if (bestDirectionSanta == 1) direction = 3;
                        else if (bestDirectionSanta == 2) direction = 0;
                        else if (bestDirectionSanta == 3) direction = 1;

                        moveSanta(santa.index, nextSr, nextSc, -dsr[bestDirectionSanta]*D, -dsc[bestDirectionSanta]*D, direction);
                    } else {
                        board[nextSr][nextSc] = santa.index;
                        santas[santa.index].r = nextSr;
                        santas[santa.index].c = nextSc;
                    }
                }
            }

            for (Santa s : players){
                santas[s.index].score+=1;
            }
        }

        //3. 출력
        for(int i=1;i<P+1; i++){
            System.out.print(santas[i].score+" ");
        }
    }
}
