package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class prob10972 {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;
    static int N;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int idx = -1;
        for (int i = N - 1; i >= 1; i--) {
            if (arr[i] > arr[i - 1]) {
                idx = i - 1;
                break;
            }
        }

        if (idx == -1) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < idx; i++) {
            visited[arr[i]] = true;
        }
        while (true) {
            if (!visited[++arr[idx]]) {
                visited[arr[idx]] = true;
                break;
            }
        }

        for (int i = 0; i <= idx; i++) {
            sb.append(arr[i]).append(" ");
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb.toString());
    }

    private static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[i - 1];
        arr[i - 1] = tmp;
    }
}