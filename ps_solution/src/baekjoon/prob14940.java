package baekjoon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob14940 {
    static int N;
    static int M;
    static int[][] map;
    static int[][] ans;
    static boolean[][] visited;
    static int[] r_d = { -1, 0, 1, 0 };
    static int[] c_d = { 0, -1, 0, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int start_x = -1;
        int start_y = -1;

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        ans = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(ans[i], 0);
        }
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();

                if (map[i][j] == 2) {
                    start_x = i;
                    start_y = j;
                }
            }
        }

        bfs(start_x, start_y);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    sb.append(-1 + " ");
                } else {
                    sb.append(ans[i][j] + " ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

        sc.close();
    }

    public static void bfs(int x, int y) {
        Queue<vertex> q = new LinkedList<>();
        q.add(new vertex(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            vertex now = q.poll();

            for (int i = 0; i < 4; i++) {
                int next_x = now.x + r_d[i];
                int next_y = now.y + c_d[i];

                if (next_x >= 0 && next_x < N && next_y >= 0 && next_y < M && !visited[next_x][next_y]) {
                    if (map[next_x][next_y] == 1) {
                        ans[next_x][next_y] = ans[now.x][now.y] + 1;
                        q.add(new vertex(next_x, next_y));
                        visited[next_x][next_y] = true;
                    }
                }
            }

        }
    }
}

class vertex {
    int x;
    int y;

    public vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
