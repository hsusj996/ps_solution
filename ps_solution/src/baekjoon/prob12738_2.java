package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob12738_2 {
    static int N;
    static int[] dp;
    static int[] arr;
    static int max = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp = new int[N + 1];
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            int idx = binary_search(1, i, arr[i]);

            dp[i] = dp[idx] + 1;

            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }

    private static int binary_search(int left, int right, int target) {
        int start = left;
        int end = right;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (dp[mid] == target) {
                return mid - 1;
            }

            if (dp[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (start > right) {
            return right;
        } else {
            return end;
        }
    }
}