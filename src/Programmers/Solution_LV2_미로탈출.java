import java.util.*;
import java.io.*;

class Solution {
    static int R,C;
    static char[][] map;
    static int SR, SC;
    static int ER, EC;
    static int LR, LC;
    static int[] dr = {-1,1,0,0}; //상하좌우
    static int[] dc = {0,0,-1,1}; //상하좌우
    
    class Point{
        int r,c;
        int n;
        
        public Point() {}
        
        public Point(int r, int c){
            this.r=r;
            this.c=c;
        }
        
        public Point(int r, int c, int n){
            this.r=r;
            this.c=c;
            this.n=n;
        }
    }
    
    public int solution(String[] maps) {
        // 1. 입력 및 초기화
        R = maps.length;
        C = maps[0].length();
        map = new char[R][C];
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                map[r][c] = maps[r].charAt(c); 
                if(map[r][c]=='S'){
                    SR = r;
                    SC = c;
                }else if(map[r][c]=='E'){
                    ER = r;
                    EC = c;
                }else if(map[r][c]=='L'){
                    LR = r;
                    LC = c;
                }
            }
        }
        
        // 2. BFS
        int toPoint1 = -1;
        Queue<Point> q = new ArrayDeque<>();
        int[][] visited = new int[R][C];
        q.add(new Point(SR, SC, 0));
        visited[SR][SC] = 1;
        while(!q.isEmpty()){
            Point cur = q.poll();
            
            if(cur.r==LR && cur.c==LC){
                toPoint1 = cur.n;
                break;
            }
            
            for(int i=0; i<4; i++){
                int nr = cur.r+dr[i];
                int nc = cur.c+dc[i];
                if(nr<0 || nr>=R || nc<0 || nc>=C) continue; // map을 벗어날 때
                if(map[nr][nc]=='X') continue; // 벽일 때
                if(visited[nr][nc]==1) continue;
                q.add(new Point(nr, nc, cur.n+1));
                visited[nr][nc] = 1;
            }
        }
        
        if(toPoint1==-1) return -1;
        
        // 3. BFS
        int toPoint2 = -1;
        Queue<Point> q2 = new ArrayDeque<>();
        int[][] visited2 = new int[R][C];
        q2.add(new Point(LR, LC, 0));
        visited2[LR][LC] = 1;
        while(!q2.isEmpty()){
            Point cur = q2.poll();
            
            if(cur.r==ER && cur.c==EC){
                toPoint2 = cur.n;
                break;
            }
            
            for(int i=0; i<4; i++){
                int nr = cur.r+dr[i];
                int nc = cur.c+dc[i];
                if(nr<0 || nr>=R || nc<0 || nc>=C) continue; // map을 벗어날 때
                if(map[nr][nc]=='X') continue; // 벽일 때
                if(visited2[nr][nc]==1) continue;
                q2.add(new Point(nr, nc, cur.n+1));
                visited2[nr][nc] = 1;
            }
        }
        
        if(toPoint2==-1) return -1;
        
        return toPoint1+toPoint2;
    }
}
