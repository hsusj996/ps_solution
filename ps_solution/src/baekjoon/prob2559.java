package baekjoon;

import java.util.*;
import java.io.*;

public class prob2559 {
    static int N;
    static int K;
    static int[] sum;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        sum = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = K; i <= N; i++) {
            max = Math.max(max, sum[i] - sum[i - K]);
        }

        System.out.println(max);
    }
}
