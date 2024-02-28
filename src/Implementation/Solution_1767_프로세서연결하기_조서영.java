package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기_조서영 {
    static int N;
    static boolean[][] board;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static ArrayList<Point> points;
    static int SIZE;
    static int result;
    static int lenResult;

    static class Point{
        int r,c;
        public Point(int r, int c) {
            this.r=r;
            this.c=c;
        }
    }

    public static void dfs(int idx, int cnt, int len) {
        if(cnt>result) {
            result = cnt;
            lenResult = len;
        }
        if(cnt==result) {
            lenResult = Math.min(lenResult, len);
        }

        if(idx>=SIZE) return;

        dfs(idx+1, cnt, len);
        Point point = points.get(idx);
        for(int i=0;i<4;i++) {
            int curR = point.r;
            int curC = point.c;

            boolean flag = true;
            curR += dr[i];
            curC += dc[i];
            while(!(curR < 0 || curR >= N || curC < 0 || curC >= N)) {
                if (board[curR][curC]){
                    flag=false;
                    break;
                }
                curR += dr[i];
                curC += dc[i];
            }

            if(flag){
                curR = point.r;
                curC = point.c;
                int l = 0;
                curR += dr[i];
                curC += dc[i];
                while(!(curR < 0 || curR >= N || curC < 0 || curC >= N)) {
                    board[curR][curC]=true;
                    l++;
                    curR += dr[i];
                    curC += dc[i];
                }

                //연결되었다면 다음으로
                dfs(idx+1, cnt+1, len+l);

                //다시 원상태로
                curR = point.r;
                curC = point.c;
                curR += dr[i];
                curC += dc[i];
                while(!(curR < 0 || curR >= N || curC < 0 || curC >= N)) {
                    board[curR][curC]=false;
                    curR += dr[i];
                    curC += dc[i];
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<T+1; test_case++) {
            N = Integer.parseInt(br.readLine().trim());
            board = new boolean[N][N];

            points = new ArrayList<>();
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++) {
                    if(Integer.parseInt(st.nextToken())==1){
                        board[i][j]=true;
                        points.add(new Point(i,j));
                    }
                }
            }

            result=Integer.MIN_VALUE;
            lenResult=Integer.MAX_VALUE;
            SIZE=points.size();
            dfs(0,0,0);
            System.out.printf("#%d %d%n",test_case,lenResult);
        }

    }
}
