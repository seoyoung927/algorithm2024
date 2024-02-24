package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//메모리: 46372KB, 시간: 408ms
public class Main_1922_G4_네트워크연결_조서영 {
    static int N;
    static int M;
    static int[][] board;

    public static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        board = new int[N+1][N+1];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            board[from][to] = weight;
            board[to][from] = weight;
        }
    }

    public static int MSTPrim(){
        int[] minEdge = new int[N+1];
        Arrays.fill(minEdge,Integer.MAX_VALUE);
        boolean[] visited = new boolean[N+1];
        minEdge[1]=0;
        int result=0;

        int c;
        for(c=1;c<N+1;c++){
            int min = Integer.MAX_VALUE;
            int minVertex = -1;

            for(int i=1;i<N+1;i++){
                if(!visited[i] && minEdge[i]<min){
                    min = minEdge[i];
                    minVertex = i;
                }
            }

            if(minVertex==-1) break; //트리를 만들 수 없는 경우
            result+=min;
            visited[minVertex]=true;

            for(int i=1;i<N+1;i++){
                if(!visited[i] && board[minVertex][i]!=0
                && board[minVertex][i]<minEdge[i]){
                    minEdge[i]=board[minVertex][i];
                }
            }
        }

        return c==N+1 ? result : -1;
    }

    public static void main(String[] args) throws IOException {
        getInput();
        System.out.println(MSTPrim());
    }
}
