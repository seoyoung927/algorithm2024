package seoyoung.day0409;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;

public class 메이즈러너 {
    static int N,M,K; //N: 미로의 크기, M: 참가자 수, K: 게임 시간
    static int[][] board;
    static Point[] participants;
    static Point exit;
    static int[] dr = {0,0,-1,1}; //좌우상하
    static int[] dc = {-1,1,0,0}; //좌우상하
    static int[] dist;
    static int result;

    static class Point{
        int r, c;
        boolean isOnBoard;
        public Point(int r, int c, boolean isOnBoard){
            this.r=r;
            this.c=c;
            this.isOnBoard=isOnBoard;
        }
        @Override
        public String toString(){
            return r+" "+c;
        }
    }

    //Point p에서 exit로 가까워지기 위해서 움직여야하는 방향을 return
    //상하좌우로 움직일 수 있고
    //벽이 있다면 움직일 수 없다.
    //현재 머물러 있던 칸보다 출구까지의 최단거리가 가까워야 한다.
    //움직일 수 있는 칸이 2개 이상이라면, 상하로 움직이는 것을 우선시한다.
    //참가자가 움직일 없는 상황이라면 움직이지 않는다.
    //한 칸에 2명 이상의 참가자가 있을 수 있다. 
    public static int getMoveDirection(Point p){
        int direction = -1; //움직일 수 없다면 -1을 return

        int minDist = getDist(p, exit);
        for(int i=0; i<4; i++){
            int nr = p.r + dr[i];
            int nc = p.c + dc[i];
            if(nr<=0 || nr>N || nc<=0 || nc>N) continue; //칸을 벗어날 수 없다.
            if(board[nr][nc]>0) continue; //벽이 있다면 움직일 수 없다.
            int newDist = getDist(new Point(nr,nc,true), exit);
            if(newDist<=minDist){
                //상하로 움직이는 것을 우선시한다. => 값이 같다면 update된다.
                minDist = newDist;
                direction = i;
            }
        }

        return direction;
    }

    public static void moveParticipants(){
        for(int i=0; i<M; i++){
            Point participant = participants[i];
            //이미 탈출했다면
            if(!participant.isOnBoard) continue;
            int direction = getMoveDirection(participant);
            if(direction==-1) continue;
            participant.r = participant.r+dr[direction];
            participant.c = participant.c+dc[direction];
            result+=1;
            if(participant.r==exit.r && participant.c==exit.c) participant.isOnBoard=false;
        }
    }
    
    public static void rotateBoard(int t){
        //한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형을 잡기위해서 참가자들과의 거리를 계산
        Point start = new Point(-1,-1,true);
        boolean flag = false;
        int minVal = -1;
        ArrayList<Point> inParticipants = new ArrayList<>();     
        for(int n=1; n<N; n++){
            if(flag) break;
            for(int sr=Math.max(exit.r-n,1); sr<=exit.r; sr++){
                if(flag) break;
                for(int sc=Math.max(exit.c-n,1); sc<=exit.c; sc++){
                    if(flag) break;
                    int er = sr+n;
                    int ec = sc+n;
                    //정사각형을 만족하는지 확인
                    if(er>N || ec>N) continue;
                    //참가자가 포함되는지 확인
                    for(int i=0; i<M; i++){
                        Point participant = participants[i];
                        if(participant.isOnBoard && participant.r>=sr && participant.r<=er && participant.c>=sc && participant.c<=ec){
                            start = new Point(sr, sc, true);
                            if(participant.isOnBoard) inParticipants.add(participant);
                            minVal = n;
                            flag=true;
                        }
                    }
                }
            }
        }

        //시계방향으로 90도 회전시킴
        //회전된 벽은 내구도가 1씩 깎임
        if(start.r==-1 && start.c==-1) return;
        int[][] tmp = new int[minVal+1][minVal+1];
        for(int r=0; r<minVal+1; r++){
            for(int c=0; c<minVal+1; c++){
                tmp[c][minVal-r] = board[start.r+r][start.c+c]>0 ? board[start.r+r][start.c+c]-1 : 0;
            }
        }
        for(int r=0; r<minVal+1; r++){
            for(int c=0; c<minVal+1; c++){
                board[start.r+r][start.c+c]=tmp[r][c];
            }
        }
        for(Point participant : inParticipants){
            int cr = participant.r-start.r;
            int cc = participant.c-start.c;
            participant.r = start.r+cc;
            participant.c = start.c+(minVal-cr);
        }
        {
            int cr = exit.r-start.r;
            int cc = exit.c-start.c;
            exit.r = start.r+cc;
            exit.c = start.c+(minVal-cr);
        }
    }

    public static int getDist(Point p1, Point p2){
        return Math.abs(p1.r-p2.r)+Math.abs(p1.c-p2.c);
    }

    public static void main(String[] args) throws Exception{
        //1. 입력/////////////////////////////////////////////////////////////////
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        board = new int[N+1][N+1]; //좌상단은 (1,1)
        for(int r=1; r<N+1; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<N+1; c++){
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        participants = new Point[M];
        dist = new int[M];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            participants[i] = new Point(r,c,true);
        }

        {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            exit = new Point(r,c,true);
        }

        ////////////////////////////////////////////////////////////////
        //2. 로직 수행
        result = 0;
        for(int t=1; t<K+1; t++){
            moveParticipants();
            //2-2. 미로의 회전
            rotateBoard(t);
            if(t>=0) continue;
            System.out.println(t+" 미로 회전 후 미로 상태");
            for(int r=0; r<N+1; r++){
                System.out.println(Arrays.toString(board[r]));
            }
            System.out.println(t+" 미로 회전 후 참가자 상태");
            for(int i=0; i<M; i++){
                System.out.println(participants[i]);
            }
            System.out.println(t+" 미로 회전 후 출구 상태");
            System.out.println(exit);
        }

        //3. 출력
        System.out.println(result);
        System.out.println(exit);
    }
}
