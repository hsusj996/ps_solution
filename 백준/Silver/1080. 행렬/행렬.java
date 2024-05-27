import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] matA;
    static int[][] matB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matA = new int[N][M];
        matB = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                matA[i][j] = input.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                matB[i][j] = input.charAt(j) - '0';
            }
        }

        int ans = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                if (matA[i][j] == matB[i][j]) {
                    continue;
                }

                reverseMat(i + 1, j + 1);
                ans++;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matA[i][j] != matB[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(ans);
    }

    private static void reverseMat(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                matA[i][j] ^= 1;
            }
        }
    }
}