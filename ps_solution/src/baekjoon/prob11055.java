package baekjoon;

import java.util.*;
import java.io.*;

public class prob11055 {
    static int N;
    static int[] arr;
    static int[] dp;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N - 1; i >= 0; i--) {
            dp[i] = arr[i];
            int max_dp = 0;
            for (int j = i + 1; j < N; j++) {
                if (arr[i] < arr[j] && max_dp < dp[j]) {
                    max_dp = dp[j];
                }
            }
            dp[i] += max_dp;
            
            if(ans < dp[i]){
                ans = dp[i];
            }
        }

        System.out.println(ans);
    }
}
