package seoyoung.day0328;

import java.util.*;
import java.io.*;

// BFS 방식의 큐를 이용, map에 최신 version을 저장하여 answer를 한번만 update하도록 함
class Solution_LV2_충돌위험찾기 {
    int n; // n개의 point가 존재
    int m; // 운송 경로는 m개의 포인트로 구성
    int MSIZE;
    Point[] point;
    Node[][] map; // 좌상단 (1,1) -> [각 운송지점의 version]
    int[] curIdx; // 각 운송경로들의 idx를 나타냄

    class Point{
        int idx; // 운송경로의 idx
        int r;
        int c;
        int v;

        public Point() {}

        public Point(int r, int c){
            this.r=r;
            this.c=c;
        }

        public Point(int idx, int r, int c, int v){
            this.idx=idx;
            this.r=r;
            this.c=c;
            this.v=v; // version
        }

        @Override
        public String toString(){
            return "idx="+idx+",r="+r+",c="+c+",v="+v;
        }
    }

    class Node{
        int v; // version
        boolean isCount; // 충돌 count 했는지

        public Node() {}

        public Node(int v, boolean isCount){
            this.v = v;
            this.isCount = isCount;
        }
    }

    public int solution(int[][] points, int[][] routes) {
        // 1. 입력 및 초기화
        int answer = 0; // 로봇이 운송을 마칠 때까지 발생하는 위험한 상황의 횟수
        n = points.length;
        m = routes.length;
        MSIZE = routes[0].length;
        point = new Point[n+1];
        for(int i=0; i<n; i++) point[i+1] = new Point(points[i][0], points[i][1]);
        map = new Node[101][101]; // 최댓값으로 초기화
        for(int r=1; r<101; r++){
            for(int c=1; c<101; c++){
                map[r][c] = new Node(-1,false);
            }
        }
        curIdx = new int[m]; // 출발지점의 idx는 0

        // 2. 큐로 이동경로 계산
        Queue<Point> q = new ArrayDeque<>();
        for(int i=0; i<m; i++){
            int idx = routes[i][0];
            Point p = point[idx];
            q.add(new Point(i, p.r, p.c, 0));
            // 이미 있는지 계산
            if(map[p.r][p.c].v==0 && map[p.r][p.c].isCount==false){
                answer++;
                map[p.r][p.c] = new Node(0, true);
            }else if(map[p.r][p.c].v==0 && map[p.r][p.c].isCount==true){
                continue;
            }else{
                map[p.r][p.c] = new Node(0, false);
            }
        }
        while(!q.isEmpty()){
            Point cur = q.poll();
            int curI = cur.idx;
            int curR = cur.r;
            int curC = cur.c;
            int curV = cur.v;

            // 최종 운송경로에 도착했다면 큐에 더이상 넣지 않음
            if(curIdx[curI] == MSIZE-1){
                continue;
            }

            Point target = point[routes[curI][curIdx[curI]+1]];
            int nextR = curR;
            int nextC = curC;
            int nextV = curV+1;
            if(nextR != target.r){
                if(target.r>nextR){
                    nextR+=1;
                }else{
                    nextR-=1;
                }
            }else{ // nextR == nextC
                if(target.c>nextC){
                    nextC+=1;
                }else{
                    nextC-=1;
                }
            }

            // 다음 idx에 도착했다면 curIdx update
            if(nextR==target.r && nextC==target.c){
                curIdx[curI]+=1;
            }
            q.add(new Point(curI, nextR, nextC, nextV));

            // 이미 있는지 계산
            if(map[nextR][nextC].v==nextV && map[nextR][nextC].isCount==false){
                answer++;
                map[nextR][nextC] = new Node(nextV, true);
            }else if(map[nextR][nextC].v==nextV && map[nextR][nextC].isCount==true){
                continue;
            }else{
                map[nextR][nextC] = new Node(nextV, false);
            }
        }

        return answer;
    }
}