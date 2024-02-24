package Subset;

// 25powser :  75ms		경우의 수 : 33,554,432
// 총 경우의 수 : 33554432  time:146
// 26powser :  140ms	경우의 수 : 67,108,863			위험 => BT 필요
// 총 경우의 수 : 67108864  time:290
public class SubsetTest {
    static int N; //data length
    static int[] numbers; //기존 data
    static boolean[] isSelected; //내가 뽑은 부분집합
    ////////////count
    static long tc=0; //부분집합 개수

    public static void subset(int depth){
        if(depth>=N){
            tc++;
            //for(int n : numbers) System.out.print((char)(n+'a')+" ");
            //System.out.println();
            return;
        }

        // 부분집합에 현재 원소를 선택
        isSelected[depth]=true;
        subset(depth+1);
        isSelected[depth]=false;
        // 부분집합에 현재 원소를 선택 X
        subset(depth+1);
    }

    public static void main(String[] args){
        N = 25;
        numbers = new int[N];
        for(int i=0;i<N;i++) numbers[i]=i;
        isSelected = new boolean[N];

        long start = System.currentTimeMillis();
        subset(0);
        long end = System.currentTimeMillis();
        System.out.printf("총 경우의 수 : %d  time:%d%n",tc, end-start);
    }
}
