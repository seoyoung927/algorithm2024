package day0305;

import java.util.HashMap;

public class KakaoWinterInternship1 {
	static String[] friends = {"muzi", "ryan", "frodo", "neo"};
	static String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
	
	public static void main(String[] args) {
		int N = friends.length;
		int[][] giftMatrix = new int[N][N];
		int[] giftIndex = new int[N];
		
		HashMap<String,Integer> map = new HashMap<>();
		for(int i=0,size=friends.length; i<size; i++) map.put(friends[i], i);

		for(int i=0,size=gifts.length; i<size; i++) {
			String[] tmp = gifts[i].split(" ");
			int give = map.get(tmp[0]);
			int receive = map.get(tmp[1]);
			giftMatrix[give][receive]+=1;
		}
		
		for(int i=0;i<N;i++) {
			int give = 0;
			for(int j=0; j<N; j++) give+=giftMatrix[i][j];
			int receive = 0;
			for(int j=0; j<N; j++) receive+=giftMatrix[j][i];
			giftIndex[i] = give-receive;
		}
		
		int result = Integer.MIN_VALUE;
		for(int i=0, size=giftMatrix.length; i<size; i++) {
			int sum = 0;
			for(int j=0; j<size; j++) {
				if(i==j) continue;
				if(giftMatrix[i][j]>giftMatrix[j][i]) sum+=1;
				else if(giftMatrix[i][j]==giftMatrix[j][i] && giftIndex[i]>giftIndex[j]) sum+=1;
			}
			result = Math.max(result, sum);
		}
		
		System.out.println(result);
	}
}
