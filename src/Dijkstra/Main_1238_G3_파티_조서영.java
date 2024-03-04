package seoyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1238_G3_파티_조서영 {
	static int N; //N명의 학생
	static int M; //도로의 개수
	static int X; //party~
	static int start; //수빈이가 현재 있는 점
	static int end; //목표 지점
	static Node[] adjList;
	static int result;
	
	static class Node{
		int vertex, weight;
		Node next;
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		X=Integer.parseInt(st.nextToken());
		
		adjList = new Node[N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			int weight=Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to,weight,adjList[from]);
		}
		
		final int INF = Integer.MAX_VALUE;
		int[] minDistanceX = new int[N+1];
		boolean[] visitedX = new boolean[N+1];
		
		Arrays.fill(minDistanceX, INF);
		minDistanceX[X] = 0;
		
		int min = 0, stopOver = 0;
		for(int i=0;i<N;i++) {
			min = INF;
			stopOver = -1;
			
			for(int j=1;j<N+1;j++) {
				if(!visitedX[j] && min>minDistanceX[j]) {
					min = minDistanceX[j];
					stopOver = j;
				}
			}
			
			if(stopOver==-1) break;
			visitedX[stopOver] = true;
			
			for(Node tmp = adjList[stopOver]; tmp!=null; tmp=tmp.next) {
				if(minDistanceX[tmp.vertex]>min+tmp.weight) minDistanceX[tmp.vertex]=min+tmp.weight;
			}
		}
		
		int[] minDistance;
		boolean[] visited;
		
		result = Integer.MIN_VALUE;
		for(int startCity = 1; startCity<N+1; startCity++) {
			minDistance = new int[N+1];
			visited = new boolean[N+1];
			
			Arrays.fill(minDistance, INF);
			minDistance[startCity] = 0;
			
			min = 0;
			stopOver = 0;
			
			for(int i=1;i<N+1;i++) {
				min = INF;
				stopOver = -1;
				
				for(int j=1;j<N+1;j++) {
					if(!visited[j] && min>minDistance[j]) {
						min = minDistance[j];
						stopOver = j;
					}
				}
				
				if(stopOver==-1) break;
				visited[stopOver] = true;
				
				for(Node tmp = adjList[stopOver];tmp!=null;tmp=tmp.next) {
					if(minDistance[tmp.vertex]>min+tmp.weight) minDistance[tmp.vertex]=min+tmp.weight;
				}
			}
			
			
			result = Math.max(result, minDistance[X]+minDistanceX[startCity]);
		}
		
		System.out.println(result);
	}
}
