package Stack;

import java.io.*;
import java.util.*;

//스택->현재 빌딩보다 낮거나 같은 높이의 빌딩들을 효율적으로 제거 가능
//한번에 계산해서 O(N)으로
//3420ms
public class Main_22866_G3_Stack_탑보기 {
    static int N;
    static int[] building;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        building = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) building[i] = Integer.parseInt(st.nextToken());

        int[] leftCount = new int[N+1]; //왼쪽 방향으로 보이는 더 높은 빌딩의 개수
        int[] rightCount = new int[N+1]; //오른쪽 방향으로 보이는 더 높은 빌딩의 개수
        int[] leftMax = new int[N+1]; //왼쪽 방향으로 보이는 가장 가까운 더 높은 빌딩의 위치
        int[] rightMax = new int[N+1]; //오른쪽 방향으로 보이는 가장 가까운 더 높은 빌딩의 위치

        //왼쪽 계산
        Stack<Integer> stack = new Stack<>();
        for(int i=1; i<=N; i++){
            while (!stack.isEmpty() && building[stack.peek()] <= building[i]) {
                stack.pop();
            }
            leftCount[i] = stack.size();
            if(!stack.isEmpty()){
                leftMax[i] = stack.peek();
            }
            stack.push(i);
        }
        //오른쪽 계산
        stack.clear();
        for(int i=N; i>=1; i--){
            while (!stack.isEmpty() && building[stack.peek()] <= building[i]) {
                stack.pop();
            }
            rightCount[i] = stack.size();
            if(!stack.isEmpty()){
                rightMax[i] = stack.peek();
            }
            stack.push(i);
        }

        for(int i=1; i<=N; i++){
            if (leftCount[i] == 0 && rightCount[i] == 0) {
                System.out.println(0);
            }else if (leftCount[i] > 0 && (rightCount[i] == 0 || i - leftMax[i] <= rightMax[i] - i)) {
                System.out.printf("%d %d%n", leftCount[i]+rightCount[i], leftMax[i]);
            } else {
                System.out.printf("%d %d%n", leftCount[i]+rightCount[i], rightMax[i]);
            }
        }
    }
}
