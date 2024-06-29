package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_파핑파핑지뢰 {
    static int N;
    static char[][] map;
    static int[][] cnt;
    static List<Point> safe;
    static int[] dr = {-1,-1,-1,0,0,1,1,1};
    static int[] dc = {-1,0,1,-1,1,-1,0,1};
    static int answer;

    static class Point{
        int r, c;
        int n; //같이 open할 수 있는 칸의 개수
        public Point() {}
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void click(int r, int c){
        map[r][c] = 'O';
        for(int i=0; i<8; i++){
            int nr = r+dr[i];
            int nc = c+dc[i];
            if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
            if(cnt[nr][nc]==0 && map[nr][nc]!='O') click(nr,nc);
            map[nr][nc] = 'O';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            //1. 입력 및 초기화
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            cnt = new int[N][N];
            safe = new ArrayList<>();
            for(int r=0; r<N; r++){
                String s = br.readLine();
                for(int c=0; c<N; c++){
                    map[r][c] = s.charAt(c);
                    if(map[r][c]!='*') safe.add(new Point(r,c));
                    if(map[r][c]=='*'){
                        for(int i=0; i<8; i++){
                            int nr = r+dr[i];
                            int nc = c+dc[i];
                            if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                            cnt[nr][nc]++;
                        }
                    }
                }
            }

            answer = 0;
            for(int idx=0, SIZE=safe.size(); idx<SIZE; idx++){
                Point p = safe.get(idx);
                int cr = p.r;
                int cc = p.c;
                if(cnt[cr][cc]==0 && map[cr][cc]!='O'){
                    click(cr,cc);
                    answer++;
                }
            }
            for(int idx=0, SIZE=safe.size(); idx<SIZE; idx++){
                Point p = safe.get(idx);
                int cr = p.r;
                int cc = p.c;
                if(map[cr][cc]!='O') answer++;
            }

            System.out.printf("#%d %d%n",test_case, answer);
        }
    }
}
