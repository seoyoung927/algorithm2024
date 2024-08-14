import java.util.*;
import java.io.*;

// 216ms, 스택, O(N)
public class Main_1863_G4_스카이라인쉬운거_스택 {
    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        Stack<Integer> stack = new Stack();
        
        // 2. Stack
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 스택에는 나보다 큰 것들이 들어있으면 안됨
            while(!stack.isEmpty() && (int)stack.peek()>y){
                stack.pop();
                answer++;
            }

            // 높이가 0이면 무조건 건물은 없음
            if(y==0) continue;

            // 동일 높이이면 넣을 필요 없음
            if(!stack.isEmpty() && stack.peek()==y) continue;

            stack.add(y);
        }

        while(!stack.isEmpty()){
            stack.pop();
            answer++;
        }

        // 3. 출력
        System.out.println(answer);
    }
}
