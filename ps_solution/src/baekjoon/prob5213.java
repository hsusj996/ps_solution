package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class prob5213 {
    static final int INF = 1_000_000;

    static class Tile {
        int idx;
        int left;
        int right;

        public Tile(int idx, int left, int right) {
            this.idx = idx;
            this.left = left;
            this.right = right;
        }
    }

    static int N;
    static int cnt;
    static Tile[] board;
    static boolean[] visited;
    static int[] minDistance;
    static int[] beforeNode;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cnt = N * N - N / 2;
        board = new Tile[cnt + 1];
        visited = new boolean[cnt + 1];
        minDistance = new int[cnt + 1];
        Arrays.fill(minDistance, INF);
        beforeNode = new int[cnt + 1];
        for (int i = 1; i <= cnt; i++) {
            String[] s = br.readLine().split(" ");
            int left = Integer.parseInt(s[0]);
            int right = Integer.parseInt(s[1]);

            board[i] = new Tile(i, left, right);
        }

        bfs();

        for (int i = cnt; i > 0; i--) {
            if (minDistance[i] != INF) {
                System.out.println(minDistance[i]);
                List<Integer> routeList = new ArrayList<>();
                for (int j = i; j != 0; j = beforeNode[j]) {
                    routeList.add(j);
                }

                for (int k = routeList.size() - 1; k >= 0; k--) {
                    System.out.print(routeList.get(k) + " ");
                }
                break;
            }
        }
    }

    private static void bfs() {
        Queue<Tile> q = new ArrayDeque<>();
        q.add(board[1]);
        visited[1] = true;
        minDistance[1] = 1;
        int depth = 1;

        while (q.size() > 0) {
            int qsize = q.size();
            depth++;
            while (qsize-- > 0) {
                Tile cur = q.poll();

                int remainder = cur.idx % (2 * N - 1);

                if (remainder == 1) {
                    if (check(cur.idx, cur.idx - (N - 1), depth))
                        q.add(board[cur.idx - (N - 1)]);
                    if (check(cur.idx, cur.idx + 1, depth))
                        q.add(board[cur.idx + 1]);
                    if (check(cur.idx, cur.idx + N, depth))
                        q.add(board[cur.idx + N]);
                } else if (remainder == N) {
                    if (check(cur.idx, cur.idx - N, depth))
                        q.add(board[cur.idx - N]);
                    if (check(cur.idx, cur.idx - 1, depth))
                        q.add(board[cur.idx - 1]);
                    if (check(cur.idx, cur.idx + (N - 1), depth))
                        q.add(board[cur.idx + (N - 1)]);
                } else if (remainder == N + 1) {
                    if (check(cur.idx, cur.idx - N, depth))
                        q.add(board[cur.idx - N]);
                    if (check(cur.idx, cur.idx - (N - 1), depth))
                        q.add(board[cur.idx - (N - 1)]);
                    if (check(cur.idx, cur.idx + 1, depth))
                        q.add(board[cur.idx + 1]);
                    if (check(cur.idx, cur.idx + (N - 1), depth))
                        q.add(board[cur.idx + (N - 1)]);
                    if (check(cur.idx, cur.idx + N, depth))
                        q.add(board[cur.idx + N]);
                } else if (remainder == 0) {
                    if (check(cur.idx, cur.idx - N, depth))
                        q.add(board[cur.idx - N]);
                    if (check(cur.idx, cur.idx - (N - 1), depth))
                        q.add(board[cur.idx - (N - 1)]);
                    if (check(cur.idx, cur.idx - 1, depth))
                        q.add(board[cur.idx - 1]);
                    if (check(cur.idx, cur.idx + (N - 1), depth))
                        q.add(board[cur.idx + (N - 1)]);
                    if (check(cur.idx, cur.idx + N, depth))
                        q.add(board[cur.idx + N]);
                } else {
                    if (check(cur.idx, cur.idx - N, depth))
                        q.add(board[cur.idx - N]);
                    if (check(cur.idx, cur.idx - (N - 1), depth))
                        q.add(board[cur.idx - (N - 1)]);
                    if (check(cur.idx, cur.idx - 1, depth))
                        q.add(board[cur.idx - 1]);
                    if (check(cur.idx, cur.idx + 1, depth))
                        q.add(board[cur.idx + 1]);
                    if (check(cur.idx, cur.idx + (N - 1), depth))
                        q.add(board[cur.idx + (N - 1)]);
                    if (check(cur.idx, cur.idx + N, depth))
                        q.add(board[cur.idx + N]);
                }
            }
        }
    }

    private static boolean check(int cur, int next, int depth) {
        if (next <= 0 || next > cnt || visited[next]) {
            return false;
        }

        int dif = next - cur;
        if (dif == -1 * N || dif == -1 || dif == (N - 1)) {
            if (board[cur].left == board[next].right) {
                visited[next] = true;
                minDistance[next] = depth;
                beforeNode[next] = cur;
                return true;
            }
        } else {
            if (board[cur].right == board[next].left) {
                visited[next] = true;
                minDistance[next] = depth;
                beforeNode[next] = cur;
                return true;
            }
        }
        return false;
    }
}
