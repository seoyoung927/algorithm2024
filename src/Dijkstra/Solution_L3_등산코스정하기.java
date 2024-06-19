package Dijkstra;

import java.util.*;

class Solution_L3_등산코스정하기 {
    static HashMap<Integer, ArrayList<Node>> graph;
    static HashSet<Integer> gateSet;
    static HashSet<Integer> summitSet;
    static int[] answer;

    static class Node{
        int idx;
        int dist;

        public Node(int idx, int dist){
            this.idx=idx;
            this.dist=dist;
        }

        @Override
        public String toString(){
            return "idx= "+idx+", dist= "+dist;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        //1. 초기화
        graph = new HashMap<>();
        gateSet = new HashSet<>();
        summitSet = new HashSet<>();
        answer = new int[] {-1, Integer.MAX_VALUE};
        for(int idx=0, SIZE=paths.length; idx<SIZE; idx++){
            int i = paths[idx][0];
            int j = paths[idx][1];
            int w = paths[idx][2];

            if(!graph.containsKey(i)) graph.put(i, new ArrayList<>());
            if(!graph.containsKey(j)) graph.put(j, new ArrayList<>());

            //양방향
            graph.get(i).add(new Node(j, w));
            graph.get(j).add(new Node(i, w));
        }
        for (int gate : gates) gateSet.add(gate);
        for (int summit : summits) summitSet.add(summit);

        //2. 다익스트라(Dijkstra)라고 할 수 있나...
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.dist));
        for(int gate : gates){
            dist[gate] = 0;
            pq.add(new Node(gate, 0));
        }

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int curIdx = cur.idx;
            int curDist = cur.dist;

            if(curDist > dist[curIdx]) continue;

            for(Node neighbor : graph.get(curIdx)){
                int newDist = Math.max(dist[curIdx], neighbor.dist);
                if(newDist<dist[neighbor.idx] && !gateSet.contains(neighbor.idx)) {
                    dist[neighbor.idx] = newDist;
                    if(!summitSet.contains(neighbor.idx)) pq.add(new Node(neighbor.idx, newDist));
                }
            }
        }

        //2-2. 최소값으로 update
        //System.out.println(Arrays.toString(dist));
        Arrays.sort(summits);
        for(int summit : summits){
            if(dist[summit]<answer[1]){
                answer[0] = summit;
                answer[1] = dist[summit];
            }
        }

        return answer;
    }
}
