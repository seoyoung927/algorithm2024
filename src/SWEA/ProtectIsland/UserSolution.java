package SWEA.ProtectIsland;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class UserSolution {
    public final int MAX_N = 20;
    public final int MAX_HASH = 10000;

    public int n;
    public int[][] initialMap = new int[MAX_N+2][MAX_N+2];
    public int[][] modifiedMap = new int[MAX_N+2][MAX_N+2];

    public class Candidate {
        int r, c;
        boolean isHorizontal, isReverse;

        public Candidate() {}

        public Candidate(int r, int c, boolean isHorizontal, boolean isReverse) {
            this.r = r;
            this.c = c;
            this.isHorizontal = isHorizontal;
            this.isReverse = isReverse;
        }
    }

    public List<Candidate>[] candidate = new List[MAX_HASH];

    public void init(int N, int mMap[][]) {
        n = N;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                initialMap[r+1][c+1] = modifiedMap[r+1][c+1] = mMap[r][c];
            }
        }
        for (int i = 0; i < MAX_HASH; i++) {
            candidate[i] = new ArrayList<>();
        }

        for (int length = 2; length <= 5; length++) { // 구조물의 길이
            for (int r = 1; r <= n; r++) {
                for (int c = 1; c <= n - length + 1; c++) {
                    // (r, c)를 시작으로 구조물을 놓음

                    // 왼쪽에서 오른쪽으로 구조물을 놓았을 때
                    int hash = 0;
                    for (int i = 0; i + 1 < length; i++)
                        hash = hash * 10 + (initialMap[r][c + i + 1] - initialMap[r][c + i] + 5);
                    candidate[hash].add(new Candidate(r, c, true, false));

                    // 오른쪽에서 왼쪽으로 놓았을 때
                    int reverseHash = 0;
                    for (int i = length - 1; i > 0; i--)
                        reverseHash = reverseHash * 10 + (initialMap[r][c + i - 1] - initialMap[r][c + i] + 5);
                    if (reverseHash != hash)
                        candidate[reverseHash].add(new Candidate(r, c, true, true));
                }
            }

            for (int r = 1; r <= n -length+1; r++) {
                for (int c = 1; c <= n; c++) {
                    // (r, c)를 시작으로 구조물을 놓음

                    // 위에서 아래로 구조물을 놓았을 때
                    int hash = 0;
                    for (int i = 0; i + 1 < length; i++)
                        hash = hash * 10 + (initialMap[r + i + 1][c] - initialMap[r + i][c] + 5);
                    candidate[hash].add(new Candidate(r, c, false, false));

                    // 아래에서 위로 놓았을 때
                    int reverseHash = 0;
                    for (int i = length - 1; i > 0; i--)
                        reverseHash = reverseHash * 10 + (initialMap[r + i - 1][c] - initialMap[r + i][c] + 5);
                    if (reverseHash != hash)
                        candidate[reverseHash].add(new Candidate(r, c, false, true));
                }
            }
        }
    }

    public int numberOfCandidate(int M, int mStructure[]) {
        if (M == 1) {
            System.out.println(n*n);
            return (n * n);
        }

        int hash = 0;
        for (int i = 0; i + 1 < M; i++)
            hash = hash * 10 + (mStructure[i] - mStructure[i + 1] + 5);

        System.out.println(candidate[hash].size());
        return candidate[hash].size();
    }

    public boolean[][] check = new boolean[MAX_N+2][MAX_N+2];
    public int[] dr = {-1,1,0,0};
    public int[] dc = {0,0,-1,1};

    public int unsubmergedArea(int[][] mMap, int mSeaLevel) {
        // 경계 부분을 미리 방문 처리하여 경계에서 물이 들어오는 것을 표시
        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r <= n + 1; r++) {
            for (int c = 0; c <= n + 1; c++) {
                if (r == 0 || r == n + 1 || c == 0 || c == n + 1) {
                    q.add(new int[]{r, c});
                    check[r][c] = true;
                } else {
                    check[r][c] = false;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] front = q.poll();
            for (int i = 0; i < 4; i++) {
                int[] rear = {front[0] + dr[i], front[1] + dc[i]};
                if (rear[0] >= 1 && rear[0] <= n && rear[1] >= 1 && rear[1] <= n) {
                    if (!check[rear[0]][rear[1]] && mMap[rear[0]][rear[1]] < mSeaLevel) {
                        q.add(rear);
                        check[rear[0]][rear[1]] = true;
                    }
                }
            }
        }

        int ret = 0;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (!check[r][c]) ret++;
            }
        }

        return ret;
    }

    public int maxArea(int M, int mStructure[], int mSeaLevel) {
        int ret = -1;
        if (M == 1) {
            for (int r = 1; r <= n; r++) {
                for (int c = 1; c <= n; c++) {
                    modifiedMap[r][c] = initialMap[r][c] + mStructure[0];
                    ret = Math.max(ret, unsubmergedArea(modifiedMap, mSeaLevel));
                    modifiedMap[r][c] = initialMap[r][c];
                }
            }
            System.out.println(ret);
            return ret;
        }

        int hash = 0;
        for (int i = 0; i + 1 < M; i++)
            hash = hash * 10 + (mStructure[i] - mStructure[i + 1] + 5);

        for (Candidate wall : candidate[hash]) {
            if (wall.isHorizontal) {
                int height = mStructure[0] + (wall.isReverse ? initialMap[wall.r][wall.c + M - 1] : initialMap[wall.r][wall.c]);
                for (int i = 0; i < M; i++)
                    modifiedMap[wall.r][wall.c + i] = height;
                ret = Math.max(ret, unsubmergedArea(modifiedMap, mSeaLevel));
                for (int i = 0; i < M; i++)
                    modifiedMap[wall.r][wall.c + i] = initialMap[wall.r][wall.c + i];
            } else {
                int height = mStructure[0] + (wall.isReverse ? initialMap[wall.r + M - 1][wall.c] : initialMap[wall.r][wall.c]);
                for (int i = 0; i < M; i++)
                    modifiedMap[wall.r + i][wall.c] = height;
                ret = Math.max(ret, unsubmergedArea(modifiedMap, mSeaLevel));
                for (int i = 0; i < M; i++)
                    modifiedMap[wall.r + i][wall.c] = initialMap[wall.r + i][wall.c];
            }
        }

        System.out.println(ret);
        return ret;
    }
}