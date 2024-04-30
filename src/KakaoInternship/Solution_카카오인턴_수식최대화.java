package KakaoInternship;

import java.util.ArrayList;
import java.util.Arrays;

class Solution_카카오인턴_수식최대화 {
    static String str;
    static char[] OP = {'+','-','*'};
    static int N = 3; //N = +,-,*개수
	//연산자 우선순위를 정하기 위함
    static int[] priority;
	static boolean[] isSelected;
    //연산자와 피연산자 구분
	static ArrayList<Long> operands;
	static ArrayList<Character> operators;
    //계산
	static boolean[] visited;
	//결과
    static long result;
    
    public static void perm(int depth) {
		if(depth>=N) {
			//System.out.println(Arrays.toString(priority));
			getList();
			
			int size = operators.size();
			visited = new boolean[size+1];
			
			for(int i=0; i<N; i++) {
				char curOp = OP[priority[i]];
				
				for(int j=0; j<size; j++) {
					if(operators.get(j)==curOp) {
						int idx = -1;
						for(int k=j+1; k<size+1; k++) {
							if(visited[k]) continue;
							idx=k;
							break;
						}
						
						if(idx!=-1) {
							visited[j]=true;
							long num = operands.get(j);
							if(curOp=='+') num+=operands.get(idx);
							if(curOp=='-') num-=operands.get(idx);
							if(curOp=='*') num*=operands.get(idx);
							operands.set(j, 0L);
							operands.set(idx, num);
						}
					}
				}
			}
			
			for(int i=0; i<size+1; i++) {
				if(operands.get(i)!=0) result = Math.max(result, Math.abs(operands.get(i)));
			}
			
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			
			priority[depth]=i;
			isSelected[i]=true;
			perm(depth+1);
			isSelected[i]=false;
		}
	}
	
    public static void getList() {
		operands = new ArrayList<>();
		operators = new ArrayList<>();
		
		String tmp = "";
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='+' || str.charAt(i)=='-' || str.charAt(i)=='*') {
				if(tmp.length()!=0) operands.add(Long.parseLong(tmp));
				tmp = "";
				operators.add(str.charAt(i));
			}else {
				tmp+=str.charAt(i);
			}
		}
		operands.add(Long.parseLong(tmp));
		
//		System.out.println("operands: "+ operands);
//		System.out.println("operators: "+ operators);
	}
            
    public long solution(String expression) {
        str = expression;
        priority = new int[N];
		isSelected = new boolean[N];
        
        result = Long.MIN_VALUE;
        perm(0);
		
        return result;
    }
}