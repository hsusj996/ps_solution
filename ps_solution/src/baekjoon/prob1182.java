package baekjoon;

import java.io.*;
import java.util.*;

public class prob1182 {
    static int N;
    static int S;
    static int[] arr;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        back_tracking(-1, 0);

        System.out.println(cnt);
    }

    static void back_tracking(int prev, int sum) {
        if (prev == N - 1) {
            return;
        }

        for (int i = prev + 1; i < N; i++) {
            if (sum + arr[i] == S) {
                cnt++;
            }
            back_tracking(i, sum + arr[i]);
        }
    }
}
