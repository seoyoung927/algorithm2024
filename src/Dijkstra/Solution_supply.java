package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_supply {
    static int N; //N*N map
    static int[][] map; //좌상(0,0)
    static int[] dr = {-1,1,0,0}; //상하좌우
    static int[] dc = {0,0,-1,1}; //상하좌우

    static class Point implements Comparable<Point>{
        int r;
        int c;
        int w;

        public Point() {}

        public Point(int r, int c, int w){
            this.r=r;
            this.c=c;
            this.w=w;
        }

        @Override
        public int compareTo(Point other){
            return Integer.compare(this.w,other.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            //1. 입력 및 초기화
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for(int r=0; r<N; r++){
                String s = br.readLine();
                for(int c=0; c<N; c++){
                    map[r][c] = Character.getNumericValue(s.charAt(c));
                }
            }

            //2. 다익스트라
            int[][] dist = new int[N][N];
            for(int i=0; i<N; i++) Arrays.fill(dist[i],Integer.MAX_VALUE);
            dist[0][0] = 0;
            PriorityQueue<Point> pq = new PriorityQueue<>();
            pq.add(new Point(0,0,0));
            while(!pq.isEmpty()){
                Point cur = pq.poll();

                if(cur.w>dist[cur.r][cur.c]) continue;

                for(int i=0; i<4; i++){
                    int nr = cur.r+dr[i];
                    int nc = cur.c+dc[i];
                    if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                    if(map[nr][nc]+cur.w<dist[nr][nc]){
                        pq.add(new Point(nr,nc,map[nr][nc]+cur.w));
                        dist[nr][nc] = map[nr][nc]+cur.w;
                    }
                }
            }

            //3. 출력
            System.out.printf("#%d %d%n",test_case,dist[N-1][N-1]);
        }
    }
}
