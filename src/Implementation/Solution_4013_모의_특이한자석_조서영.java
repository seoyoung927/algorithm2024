package seoyoung.day0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_4013_모의_특이한자석_조서영 {
	static int K;
	static int[] magnets;
	
	public static void turn(int n, int d) {
		//1.d==1, 시계방향
		if(d==1) {
			int tmp = magnets[n*8+7];
			for(int i=7; i>0; i--) {
				magnets[n*8+i] = magnets[n*8+i-1];
			}
			magnets[n*8] = tmp;
		}else {
			//2.d==-1, 반시계방향
			int tmp = magnets[n*8];
			for(int i=0; i<7; i++) {
				magnets[n*8+i] = magnets[n*8+i+1];
			}
			magnets[n*8+7] = tmp;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			//1. 입력
			K = Integer.parseInt(br.readLine());
			magnets = new int[5*8];
			int idx = 8;
			for(int i=1; i<5; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<8; j++) {
					magnets[idx++] = Integer.parseInt(st.nextToken());
				}
			}

			
			//2. K번 회전
			for(int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				ArrayList<Integer[]> arr = new ArrayList<>();
				arr.add(new Integer[] {n,d});
				int dd = d;
				//오른쪽 check
				for(int i=n; i<4; i++) {
					if(magnets[i*8+2]==magnets[(i+1)*8+6]) break;
					else {
						dd = dd*-1;
						arr.add(new Integer[] {i+1,dd});
					}
				}
				//왼쪽 check
				dd = d;
				for(int i=n; i>0; i--) {
					if(magnets[i*8+6]==magnets[(i-1)*8+2]) break;
					else{
						dd = dd*-1;
						arr.add(new Integer[] {i-1,dd});
					}
				}
				
				for(Integer[] a : arr) turn(a[0],a[1]);
			}
			
			//3. 점수 count
			int score = 0;
			for(int i=1; i<5; i++) {
				if(magnets[i*8]==1) score+=Math.pow(2,i-1);
			}
			System.out.printf("#%d %d%n",test_case,score);
		}
	}
}

