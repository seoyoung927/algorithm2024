package seoyoung.day0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//532ms
public class Main_15961_G4_회전초밥_조서영 {
	static int N, d, k, c; //N: 회전 초밥 벨트에 놓인 접시의 수
	static int[] sushi;
	static int[] eat;
	static int result;
	 
	
	public static void main(String[] args) throws IOException {
		//1. 입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		sushi = new int[N];
		for(int i=0; i<N; i++) sushi[i] = Integer.parseInt(br.readLine());
		
		
		//2. 계산
		eat = new int[d+1];
		result = 0;
		int startIdx = 0;
		int endIdx = k-1;
		
		for(int i=0; i<k; i++) eat[sushi[i]]+=1;
		eat[c]+=1;
		for(int i=0; i<d+1; i++) if(eat[i]>0) result++;
		
		int cnt = result;
		while(startIdx<N) {
			if(eat[sushi[startIdx]]==1) cnt-=1;
			eat[sushi[startIdx]]-=1;
			startIdx+=1;
			
			endIdx = endIdx+1<N ? endIdx+1 : 0;
			if(eat[sushi[endIdx]]==0) cnt+=1;
			eat[sushi[endIdx]]+=1;
			
			result = Math.max(result, cnt);
		}
		
		
		//3. 출력
		System.out.println(result);
	}
}
