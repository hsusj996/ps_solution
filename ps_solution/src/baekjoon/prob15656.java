package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prob15656 {
    static StringBuilder result = new StringBuilder();
    static int N;
    static int M;
    static int[] arr;
    static int[] selectedNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        selectedNum = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        BackTracking(0);

        System.out.println(result);
    }

    private static void BackTracking(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                result.append(selectedNum[i] + " ");
            }
            result.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            selectedNum[depth] = arr[i];
            BackTracking(depth + 1);
        }
    }
}
