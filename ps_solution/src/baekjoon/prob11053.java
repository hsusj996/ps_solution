package baekjoon;

import java.util.*;

public class prob11053 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] dp = new int[N];
        int max = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }

            if (max < dp[i]) {
                max = dp[i];
            }
        }

        System.out.println(max);
        for (int i = 0; i <= N; i++) {
            System.out.print(dp[i] + " ");
        }
        sc.close();
    }
}
