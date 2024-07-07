package SWEA.LongLadder;

import java.util.*;

class UserSolution {
    static final int N = 100;
    static final int MAX_LENGTH = 400_255;

    static class Node{
        int idx;
        Node prev;
        Node next;

        public Node() {}
        public Node(int idx){
            this.idx = idx;
        }
    }
    static Node[] node = new Node[MAX_LENGTH];
    static int cnt = 0;
    static TreeMap<Integer, Node>[] nodeMap = new TreeMap[N+1];

    public static void link(Node front, Node back){
        front.next = back;
        back.prev = front;
    }

    public static void init() {
        for (int i = 0; i < MAX_LENGTH; i++) {
            node[i] = new Node(i);
        }
        for (int i = 1; i <= N; i++) {
            nodeMap[i] = new TreeMap<>();

            nodeMap[i].put(0, node[i]);     // i 번 세로줄에 i 번 참가자를 놓는다.

            nodeMap[i].put(1000000000, node[MAX_LENGTH - i]);   // i 번 세로줄의 도착점을 추가한다.
            link(node[i], node[MAX_LENGTH - i]);
        }
        cnt = N + 1;
    }

    public void add(int mX, int mY) {
        Node nowLeft = node[cnt++];
        Node nowRight = node[cnt++];

        Node prevLeft = nodeMap[mX].floorEntry(mY).getValue();
        Node prevRight = nodeMap[mX + 1].floorEntry(mY).getValue();

        Node nextLeft = prevLeft.next;
        Node nextRight = prevRight.next;

        link(prevLeft, nowRight);
        link(nowRight, nextRight);

        link(prevRight, nowLeft);
        link(nowLeft, nextLeft);

        nodeMap[mX].put(mY, nowLeft);
        nodeMap[mX+1].put(mY, nowRight);
    }

    public void remove(int mX, int mY) {
        Node nowLeft = nodeMap[mX].get(mY);
        Node nowRight = nodeMap[mX+1].get(mY);

        Node prevLeft = nowRight.prev;
        Node prevRight = nowLeft.prev;

        Node nextLeft = nowLeft.next;
        Node nextRight = nowRight.next;

        link(prevLeft, nextLeft);
        link(prevRight, nextRight);

        nodeMap[mX].remove(mY);
        nodeMap[mX+1].remove(mY);
    }

    public int numberOfCross(int mID) {
        int ret = -1;
        Node now = node[mID];
        while(now.idx<MAX_LENGTH-N){
            ret++;
            now = now.next;
        }
        return ret;
    }

    public int participant(int mX, int mY) {
        Node now = nodeMap[mX].floorEntry(mY).getValue();
        while(now.idx>N) now = now.prev;
        return now.idx;
    }
}