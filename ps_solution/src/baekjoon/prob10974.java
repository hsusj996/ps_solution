package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class prob10974 {
    static boolean[] visited;
    static int N;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        dfs(0);

        System.out.println(sb);
    }

    static void dfs(int depth) {
        if (depth == N) {
            for (int i : list) {
                sb.append(i + " ");
            }
            sb.append("\n");
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            list.add(i);
            dfs(depth + 1);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
