import java.io.*;
import java.util.*;

public class Main_9370_G2_미확인도착지 {
    static int n, m, t;
    static int s, g, h;
    static List<Node>[] graph;
    static int[] target;

    static class Node implements Comparable<Node> {
        int n;
        int d;

        public Node(int n, int d) {
            this.n = n;
            this.d = d;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.d, other.d);
        }
    }

    // 다익스트라 알고리즘
    public static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 100_000_000);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.d > dist[cur.n]) continue;

            for (Node next : graph[cur.n]) {
                if (cur.d + next.d < dist[next.n]) {
                    dist[next.n] = cur.d + next.d;
                    pq.add(new Node(next.n, dist[next.n]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n + 1];
            target = new int[t];
            for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                if ((a == g && b == h) || (a == h && b == g)) {
                    // g-h 간선은 홀수로 처리
                    graph[a].add(new Node(b, 2 * d - 1));
                    graph[b].add(new Node(a, 2 * d - 1));
                } else {
                    // 나머지 간선은 짝수로 처리
                    graph[a].add(new Node(b, 2 * d));
                    graph[b].add(new Node(a, 2 * d));
                }
            }

            for (int i = 0; i < t; i++) {
                target[i] = Integer.parseInt(br.readLine());
            }

            // 다익스트라를 한 번만 실행
            int[] visited = dijkstra(s);

            // 결과 처리
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                if (visited[target[i]] % 2 != 0) {  // g-h 경로를 거친 경우 (홀수 거리)
                    result.add(target[i]);
                }
            }

            Collections.sort(result);
            for (int i = 0, SIZE = result.size(); i < SIZE; i++) {
                System.out.print(result.get(i) + " ");
            }
            System.out.println();  // 테스트 케이스 끝나면 줄바꿈
        }
    }
}

