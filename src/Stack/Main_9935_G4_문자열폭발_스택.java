import java.io.*;
import java.util.*;

public class Main_9935_G4_문자열폭발_스택 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int SIZE = str.length();
        String bomb = br.readLine();
        int bombSize = bomb.length();

        Stack<Character> stack = new Stack<>();
        for(int i=0; i<SIZE; i++){
            stack.add(str.charAt(i));

            if(stack.size()>=bombSize){
                boolean isBomb = true;

                for(int j=0; j<bombSize; j++){
                    if(stack.get(stack.size()-bombSize+j)!=bomb.charAt(j)){
                        isBomb = false;
                        break;
                    }
                }

                if(isBomb){
                    for(int j=0; j<bombSize; j++) stack.pop();
                }
            }
        }

        if(stack.size()==0) System.out.println("FRULA");
        else{
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<stack.size(); i++) sb.append(stack.get(i));
            System.out.println(sb);
        }
    }
}
