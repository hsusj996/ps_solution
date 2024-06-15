import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MOD = 1_000_000_000;
    static long[][][] dp = new long[101][10][1024];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        if (N < 10) {
            System.out.println(0);
            return;
        }

        dp[1][0][1] = 1;
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < 1024; k++) {
                    if (j == 0) {
                        dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k] % MOD;
                    } else if (j == 9) {
                        dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k] % MOD;
                    } else {
                        dp[i][j][k | (1 << j)] += (dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % MOD;
                    }
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += dp[N][i][1023];
            sum %= MOD;
        }

        System.out.println(sum);
    }
}