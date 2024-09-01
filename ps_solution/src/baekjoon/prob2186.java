package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prob2186 {
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };
    static StringTokenizer st = null;
    static int N, M, K;
    static int[][][] dp;
    static char[][] board;
    static char[] target;
    static int targetSize;
    static long sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        String input = br.readLine();
        targetSize = input.length();
        target = input.toCharArray();

        dp = new int[input.length()][N][M];
        for(int i=0;i<input.length();i++){
            for(int j=0;j<N;j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == target[targetSize - 1]) {
                    sum += Solve(i, j, targetSize - 1);
                }
            }
        }

        System.out.println(sum);
    }

    private static int Solve(int r, int c, int idx) {
        if (dp[idx][r][c] != -1) {
            return dp[idx][r][c];
        }

        if (idx == 0) {
            return dp[idx][r][c] = 1;
        }

        dp[idx][r][c] = 0;

        int ret = 0;
        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < 4; j++) {
                int nx = r + d[0][j] * i;
                int ny = c + d[1][j] * i;

                if (IsOutBound(nx, ny) || board[nx][ny] != target[idx - 1]) {
                    continue;
                }

                if (dp[idx - 1][nx][ny] != -1) {
                    ret += dp[idx - 1][nx][ny];
                } else {
                    ret += Solve(nx, ny, idx - 1);
                }
            }
        }

        return dp[idx][r][c] = ret;
    }

    private static boolean IsOutBound(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= M;
    }
}
