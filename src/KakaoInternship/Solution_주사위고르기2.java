package KakaoInternship;

//완전탐색 최악-> 10C5 * 6^5 * 6^5
//개선-> 10C5 * 6^5 * log(6^5)
class Solution_주사위고르기2 {
    static int N, R; //N: dices의 길이, R: N/2
    static int[] combis;
    static int[] A; //A가 뽑은 주사위
    static int[] B; //B가 뽑은 주사위
    static int[][] dices;
    static double best;
    static int[] result;

    // 조합 생성
    // O(NCR)
    public static void combi(int depth, int start) {
        if (depth >= R) {
            int aIdx = 0;
            int bIdx = 0;

            for (int i = 0; i < N; i++) {
                boolean flag = false;
                for (int j = 0; j < R; j++) {
                    if (combis[j] == i) {
                        flag = true;
                        break;
                    }
                }

                if (flag) A[aIdx++] = i;
                else B[bIdx++] = i;
            }

            // A와 B의 주사위 결과를 각각 시뮬레이션
            // O(m×log m)
            List<Integer> sumA = new ArrayList<>();
            List<Integer> sumB = new ArrayList<>();

            simulate(0, 0, A, sumA);
            simulate(0, 0, B, sumB);

            Collections.sort(sumA);
            Collections.sort(sumB);
            //System.out.println(sumA);
            //System.out.println(sumB);

            int winCount = getWin(sumA, sumB);
            double val = (double) winCount / (sumA.size()*sumB.size());
            //System.out.println(val);

            if(val>best){
                for(int i=0; i<R; i++){
                    result[i] = A[i]+1;
                }
                best = val;
            }

            return;
        }

        for (int i = start; i < N; i++) {
            combis[depth] = i;
            combi(depth + 1, i + 1);
        }
    }

    // A와 B의 주사위를 시뮬레이션하여 각 합을 계산
    public static void simulate(int depth, int curSum, int[] group, List<Integer> sum){
        if(depth>=R){
            sum.add(curSum);
            return;
        }

        for(int i=0; i<6; i++){
            simulate(depth+1, curSum+dices[group[depth]][i], group, sum);
        }
    }

    public static int getWin(List<Integer> sumA, List<Integer> sumB){
        int result = 0;
        int bIdx = 0;

        for(int i=0, SIZE=sumA.size(); i<SIZE; i++){
            while(bIdx<SIZE && sumB.get(bIdx)<sumA.get(i)){
                bIdx++;
            }
            result+=bIdx;
        }

        return result;
    }

    public int[] solution(int[][] dice) {
        N = dice.length;
        R = N / 2;
        combis = new int[R];
        A = new int[R];
        B = new int[R];
        dices = dice;
        best = -1;
        result = new int[R];
        combi(0, 0);

        return result;
    }
}
