package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1932 {
    static int[] prev_arr;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        prev_arr = new int[1];
        prev_arr[0] = Integer.parseInt(br.readLine());

        for (int i = 2; i <= N; i++) {
            int[] arr = new int[i];

            String[] str = br.readLine().split(" ");
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    arr[j] = prev_arr[0] + Integer.parseInt(str[j]);
                } else if (j == i - 1) {
                    arr[j] = prev_arr[j - 1] + Integer.parseInt(str[j]);
                } else {
                    arr[j] = Math.max(prev_arr[j - 1], prev_arr[j]) + Integer.parseInt(str[j]);
                }
            }

            prev_arr = arr;
        }

        for (int i = 0; i < prev_arr.length; i++) {
            if (max < prev_arr[i]) {
                max = prev_arr[i];
            }
        }

        System.out.println(max);
    }
}