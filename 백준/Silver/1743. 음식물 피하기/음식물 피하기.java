import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int d[][] = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    static class xy {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static StringTokenizer st = null;
    static int max;
    static int[][] board = new int[101][101];
    static int N, M, K;
    static boolean[][] visited = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (board[i][j] == 0 || visited[i][j]) {
                    continue;
                }

                bfs(i, j);
            }
        }

        System.out.println(max);
    }

    private static void bfs(int i, int j) {
        int size = 1;
        Queue<xy> q = new ArrayDeque<>();

        visited[i][j] = true;
        q.add(new xy(i, j));

        while (!q.isEmpty()) {
            xy cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + d[0][k];
                int ny = cur.y + d[1][k];

                if(IsOutBound(nx, ny) || visited[nx][ny] || board[nx][ny] == 0){
                    continue;
                }

                visited[nx][ny] = true;
                size++;
                q.add(new xy(nx, ny));
            }
        }

        max = Math.max(max, size);
    }

    private static boolean IsOutBound(int nx, int ny) {
        return nx <= 0 || nx > N || ny <= 0 || ny > M;
    }
}