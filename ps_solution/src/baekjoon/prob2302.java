package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob2302 {
    static int N;
    static int M;
    static int[] dp;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int answer = 1;
        int idx = 0;

        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(br.readLine());
            answer *= dp[n - idx - 1];
            idx = n;
        }

        answer *= dp[N - idx];

        System.out.println(answer);
    }
}
