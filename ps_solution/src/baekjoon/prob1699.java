package baekjoon;

import java.util.*;
import java.io.*;

public class prob1699 {
    static int N;
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                int val = 1 + dp[i - j * j];
                if (val < dp[i]) {
                    dp[i] = val;
                }
            }
        }
        System.out.println(dp[N]);
    }
}
