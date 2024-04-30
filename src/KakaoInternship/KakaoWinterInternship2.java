package day0308;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class KakaoWinterInternship2 {
	//static int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
	static int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};

	public static void main(String[] args) {
		int[] result = {0,0,0,0};
		
		HashMap<Integer, ArrayList<Integer>> out = new HashMap<>();
		HashMap<Integer, ArrayList<Integer>> in = new HashMap<>();
		for(int i=0, size=edges.length; i<size; i++) {
			if(!out.containsKey(edges[i][0])) out.put(edges[i][0], new ArrayList<>());
			if(!in.containsKey(edges[i][1])) in.put(edges[i][1], new ArrayList<>());
			out.get(edges[i][0]).add(edges[i][1]);
			in.get(edges[i][1]).add(edges[i][0]);
		}
		
		//2.
		//경우 1) out==0: ---> 직선 그래프 갯수 +=1
		//경우 2) out==1: 흔한 노드.. 무시
		//경우 3) out==2:
		//	if in>0: ---> 8자 그래프 갯수 +=1
		//	else: ---> 이 노드가 시작 정점
		//경우 4) out>2: ---> 이 노드가 시작 정점
		System.out.println(out);
		System.out.println(in);
		HashSet<Integer> keys = new HashSet<>();
		keys.addAll(out.keySet());
		keys.addAll(in.keySet());
		
		for(int key : keys) {
			if(!out.containsKey(key)) result[2]+=1;
			else if(out.get(key).size()==1) continue;
			else if(out.get(key).size()==2 && !in.containsKey(key)) result[0]=key;
			else if(out.get(key).size()==2 && in.get(key).size()>0) result[3]+=1;
			else if(out.get(key).size()>2) result[0]=key;
		}
		result[1]=out.get(result[0]).size()-result[2]-result[3];
		
		System.out.println(Arrays.toString(result));
	}
}
