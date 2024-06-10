package KakaoInternship;

import java.util.Arrays;

public class Solution_주사위굴리기 {
    static int N,R; //N: 주사위의 길이, R: N/2
    static int[] combis; //조합
    static int[] dupliCombis; //중복조합
    static int[] A; //A가 뽑은 주사위
    static int[] B; //B가 뽑은 주사위
    static int[][] dices;
    static int WIN;
    static int DRAW;
    static int LOSE;
    static double best;
    static int DRAWLOSE;
    static int[] result;

    //조합
    public static void combi(int depth, int start){
        if(depth>=R){
            int aIdx = 0;
            int bIdx = 0;

            for(int i=0; i<N; i++){
                boolean flag = false;
                for(int j=0; j<R; j++){
                    if(combis[j]==i){
                        flag = true;
                        break;
                    }
                }

                if(flag) A[aIdx++] = i;
                else B[bIdx++] = i;
            }

            //System.out.println(Arrays.toString(A));
            //System.out.println(Arrays.toString(B));

            WIN=0;
            DRAW=0;
            LOSE=0;

            dupliCombi(0);
            //System.out.println(WIN+" "+DRAW+" "+LOSE);
            double val = (double)WIN/ ((double)(WIN+DRAW+LOSE));
            if(val>best){
                best = val;
                for(int i=0; i<R; i++){
                    result[i] = A[i]+1;
                }
            }

            return;
        }

        for(int i=start; i<N; i++){
            combis[depth]=i;
            combi(depth+1, i+1);
        }
    }

    //중복조합
    public static void dupliCombi(int depth){
        if(DRAW+LOSE>DRAWLOSE) return;

        if(depth>=N){
            //System.out.println(Arrays.toString(dupliCombis));
            int a = 0;
            for(int i=0; i<R; i++){
                a+=dices[A[i]][dupliCombis[i]];
            }
            int b = 0;
            for(int i=R; i<N; i++){
                b+=dices[B[i-R]][dupliCombis[i]];
            }

            if(a>b) WIN++;
            else if(a==b) DRAW++;
            else LOSE++;

            return;
        }

        for(int i=0; i<6; i++){
            dupliCombis[depth]=i;
            dupliCombi(depth+1);
        }
    }

    public int[] solution(int[][] dice) {
        N = dice.length;
        R = dice.length/2;
        combis = new int[R];
        dupliCombis = new int[N];
        A = new int[R];
        B = new int[R];
        dices = new int[N][6];
        for(int i=0; i<N; i++){
            for(int j=0; j<6; j++){
                dices[i][j] = dice[i][j];
            }
        }
        best = -1;
        DRAWLOSE = Integer.MAX_VALUE;
        result = new int[R];
        combi(0,0);

        //System.out.println(Arrays.toString(result));
        return result;
    }
}
