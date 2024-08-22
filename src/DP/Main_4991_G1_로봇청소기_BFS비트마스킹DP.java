import java.util.*;
import java.io.*;

// 396ms
// BFS + 비트마스킹 + dp
public class Main_4991_G1_로봇청소기_BFS비트마스킹DP {
    static int h,w; // w: 방의 가로크기, h: 방의 세로 크기
    static char[][] map;
    static List<Point> dirty;
    static int startR, startC;
    static int dirtyCnt;
    static int[] dr = {-1,1,0,0}; // 상,하,좌,우
    static int[] dc = {0,0,-1,1}; // 상,하,좌,우

    static class Point{
        int r;
        int c;
        int dirty;
        int n;

        public Point() {}

        public Point(int r, int c, int dirty, int n){
            this.r = r;
            this.c = c;
            this.dirty = dirty;
            this.n = n;
        }

        @Override
        public String toString() {
            return "r="+r+",c="+c+",dirty="+dirty+",n="+n;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            // 1. 입력 및 초기화
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w==0 && h==0) break;

            map = new char[h][w];
            dirty = new ArrayList<>();
            dirtyCnt = 0;
            for(int r=0; r<h; r++){
                String str = br.readLine();
                for(int c=0; c<w; c++){
                    map[r][c] = str.charAt(c);
                    if(map[r][c]=='o'){
                        startR = r;
                        startC = c;
                    }
                    if(map[r][c]=='*'){
                        dirty.add(new Point(r,c,0,0));
                        dirtyCnt++;
                    }
                }
            }


            // 2. BFS
            int result = -1;

            Queue<Point> q = new ArrayDeque<>();
            boolean[][][] visited = new boolean[h][w][1<<10];
            q.add(new Point(startR, startC, 0, 0));
            visited[startR][startC][0] = true;
            while(!q.isEmpty()){
                Point cur = q.poll();
                int curR = cur.r;
                int curC = cur.c;
                int curD = cur.dirty;
                int curN = cur.n;

                if(map[curR][curC]=='*') { // 더러운 칸
                    for(int i=0; i<dirtyCnt; i++){
                        Point dirtyPoint = dirty.get(i);
                        if(curR==dirtyPoint.r && curC==dirtyPoint.c){
                            curD = curD | (1<<i);
                        }
                    }
                }
                if(curD==((1<<dirtyCnt)-1)){
                    result = curN;
                    break;
                }

                for(int i=0; i<4; i++){
                    int nextR = curR + dr[i];
                    int nextC = curC + dc[i];
                    if(nextR<0 || nextR>=h || nextC<0 || nextC>=w) continue;
                    if(map[nextR][nextC]=='x') continue;
                    if(visited[nextR][nextC][curD]) continue;
                    q.add(new Point(nextR, nextC, curD, curN+1));
                    visited[nextR][nextC][curD] = true;
                }
            }


            // 3. 출력
            System.out.println(result);
        }
    }
}
