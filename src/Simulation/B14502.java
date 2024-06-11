package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class B14502 {
    static int N,M; //N: 연구소의 세로 크기, M: 연구소의 가로 크기
    static int[][] map; //0: 빈칸, 1: 벽, 2: 바이러스가 있는 위치
    static int[] dr = {-1,1,0,0}; //상하좌우
    static int[] dc = {0,0,-1,1}; //상화좌우
    static int cSize;
    static List<int[]> candidates; //벽을 세울 수 있는 후보지들
    static int[] combis = new int[3];
    static int answer;

    public static void combi(int depth, int start){
        if(depth>=3){
            //System.out.println(Arrays.toString(combis));
            int[][] copy = new int[N][M];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    copy[i][j]=map[i][j];
                }
            }
            for(int i=0; i<3; i++) copy[candidates.get(combis[i])[0]][candidates.get(combis[i])[1]]=1;

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(copy[i][j]==2) dfs(i,j,copy);
                }
            }

            int safe=0;
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(copy[i][j]==0) safe+=1;
                }
            }

            answer = Math.max(answer, safe);

            return;
        }

        for(int i=start; i<cSize; i++){
            combis[depth]=i;
            combi(depth+1, i+1);
        }
    }

    public static void dfs(int r, int c, int[][] board){
        //System.out.println(r+" "+c);
        board[r][c] = 2;

        for(int i=0; i<4; i++){
            int nr = r+dr[i];
            int nc = c+dc[i];
            if(nr<0 || nr>=N || nc<0 || nc>=M) continue; //map밖을 벗어난 경우
            if(board[nr][nc]!=0) continue;
            dfs(nr,nc,board);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1.입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        candidates = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0) candidates.add(new int[] {i,j});
            }
        }
        cSize = candidates.size();

        //2. 3개를 뽑아서 벽을 세운다.
        answer = 0;
        combi(0,0);

        //3. 출력
        System.out.println(answer);
    }
}
