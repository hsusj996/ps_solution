package baekjoon;

import java.util.*;

public class prob1086_2 {
    static int N, K;
    static int[] arr, fact, inv;
    static int[][] dp;
    static int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        fact = new int[51];
        inv = new int[51];
        dp = new int[51][101];

        fact[0] = inv[0] = 1;
        for (int i = 1; i <= 50; i++) {
            fact[i] = (int) ((long) fact[i - 1] * i % MOD);
            inv[i] = power(fact[i], MOD - 2);
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            sum += Integer.toString(arr[i]).length();
        }
        K = sc.nextInt();

        for (int i = 0; i <= sum; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = solve(0, 0);
        int total = fact[sum];
        for (int i = 0; i < N; i++) {
            total = (int) ((long) total * inv[Integer.toString(arr[i]).length()] % MOD);
        }

        System.out.println((long) answer * power(total, MOD - 2) % MOD);
    }

    static int solve(int used, int val) {
        if (used == (1 << N) - 1) {
            return val == 0 ? 1 : 0;
        }
        if (dp[used][val] != -1) {
            return dp[used][val];
        }

        dp[used][val] = 0;
        for (int i = 0; i < N; i++) {
            if ((used & (1 << i)) == 0) {
                int nextVal = (int) ((val * power(10, Integer.toString(arr[i]).length()) + arr[i]) % K);
                dp[used][val] += solve(used | (1 << i), nextVal);
                dp[used][val] %= MOD;
            }
        }

        return dp[used][val];
    }

    static int power(int x, int y) {
        int ret = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                ret = (int) ((long) ret * x % MOD);
            }
            x = (int) ((long) x * x % MOD);
            y /= 2;
        }
        return ret;
    }
}