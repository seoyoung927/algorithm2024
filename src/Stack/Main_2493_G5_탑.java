package Stack;

import java.io.*;
import java.util.*;

//716ms, O(N)
public class Main_2493_G5_탑 {
    static int N;
    static int[] tower;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tower = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) tower[i] = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for(int i=1; i<=N; i++){
            while(!stack.isEmpty() && (tower[stack.peek()] <= tower[i])){
                stack.pop();
            }
            if(stack.isEmpty()) sb.append(0+" ");
            else sb.append(stack.peek()+" ");
            stack.add(i);
        }

        System.out.println(sb);
    }
}
