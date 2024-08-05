import java.util.*;
import java.io.*;

// 악, 시간초과 22... -> 이번에는 틀렸어 ㅠ ., ㅠ
public class Main_2206_G3_벽부수고이동하기_거리체크 {
    static int N,M; // N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)
    static int[][] map;
    static int[][][] dp;
    static int[] dr = {-1,1,0,0}; //상하좌우
    static int[] dc = {0,0,-1,1};

    static class Point{
        int r,c;
        int dist;
        int isBreak; // 1: 이미 부심, 0: 아직 안부심
        public Point(int r, int c){
            this.r = r;
            this.c = c;
            this.dist = 0;
            this.isBreak = 0;
        }
        public Point(int r, int c, int dist, int isBreak){
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.isBreak = isBreak;
        }
        @Override
        public String toString(){
            return "r="+r+",c="+c+", dist="+dist;
        }
    }

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        dp = new int[N+1][M+1][2];
        for(int i=1; i<=N; i++){
            String s = br.readLine();
            for(int j=1; j<=M; j++) {
                map[i][j] = s.charAt(j-1) - '0';
                Arrays.fill(dp[i][j],Integer.MAX_VALUE);
            }
        }

        // 2. BFS
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(1,1,1,0));
        while(!q.isEmpty()){
            Point cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;
            int cd = cur.dist;
            int cb = cur.isBreak;
            if(dp[cr][cc][cb]<=cd) continue;
            dp[cr][cc][cb] = cd;

            if(cr==N && cc==M) break;

            for(int i=0; i<4; i++){
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if(nr<=0 || nr>N || nc<=0 || nc>M) continue;
                if(map[nr][nc]==0){
                    q.add(new Point(nr, nc, cd+1, cb));
                }
                if(map[nr][nc]==1 && cb==0){
                    q.add(new Point(nr, nc, cd+1, 1));
                }
            }
        }

        // 3. 출력
        int answer = Math.min(dp[N][M][0], dp[N][M][1]);
        if(answer==Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }
}
