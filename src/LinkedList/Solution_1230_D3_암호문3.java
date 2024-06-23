package LinkedList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution_1230_D3_암호문3 {
    public static int N;
    public static int M;
    public static Node head;
    public static Node tail;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static class Node{
        int num;
        Node next;

        public Node() {}

        public Node(int num, Node next){
            this.num = num;
            this.next = next;
        }
    }

    public static void insert(){
        int x = Integer.parseInt(st.nextToken());
        Node prev = head;
        Node prevNext = head.next;
        for(int i=0; i<x; i++){
            prev = prevNext;
            prevNext = prev.next;
        }

        int y = Integer.parseInt(st.nextToken());
        for (int i = 0; i < y; i++) {
            int n = Integer.parseInt(st.nextToken());
            Node newNode = new Node(n, null);
            prev.next = newNode;
            prev = newNode;
        }
        prev.next = prevNext;
    }

    public static void delete(){
        int x = Integer.parseInt(st.nextToken());
        Node prev = head;
        Node after = head;
        for(int i=0; i<x; i++){
            prev = prev.next;
            after = prev;
        }

        int y = Integer.parseInt(st.nextToken());
        for (int i = 0; i < y; i++) {
            after = after.next;
        }
        prev.next = after.next;
    }

    public static void append(){
        int y = Integer.parseInt(st.nextToken());
        for(int i=0; i<y; i++){
            int num = Integer.parseInt(st.nextToken());
            Node newNode = new Node(num, null);
            tail.next = newNode;
            tail = newNode;
        }
    }

    public static void main(String args[]) throws Exception {
        int T = 10;
        for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            head = new Node();
            tail = head;

            for(int i=0; i<N; i++){
                int n = Integer.parseInt(st.nextToken());
                Node newNode = new Node(n, null);
                tail.next = newNode;
                tail = newNode;
            }

            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++){
                String cmd = st.nextToken();
                if(cmd.equals("I")) {
                    insert();
                }else if(cmd.equals("D")){
                    delete();
                }
                if(cmd.equals("A")){
                    append();
                }
            }

            Node node = head;
            System.out.printf("#%d ",test_case);
            for(int i=0; i<10; i++) {
                node = node.next;
                System.out.print(node.num+" ");
            }
            System.out.println();
        }
    }
}