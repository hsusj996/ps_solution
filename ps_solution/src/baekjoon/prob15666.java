package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class prob15666 {
    static int N;
    static int M;
    static int[] arr;
    static int[] buf;
    static Set<String> ans = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        buf = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        BackTracking(0, 0);

        ans.forEach(System.out::println);
    }

    static void BackTracking(int prev, int depth) {
        if (depth == M) {
            StringBuilder sb = new StringBuilder();
            for (int n : buf) {
                sb.append(n).append(' ');
            }
            ans.add(sb.toString());
            return;
        }

        for (int i = prev; i < N; i++) {
            buf[depth] = arr[i];
            BackTracking(i, depth + 1);
        }
    }
}
