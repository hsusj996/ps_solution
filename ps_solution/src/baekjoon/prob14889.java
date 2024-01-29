package baekjoon;

import java.io.*;
import java.util.*;

public class prob14889 {
    static int N;
    static int[][] info;
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        info = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, -1);

        System.out.println(ans);
    }

    static void dfs(int depth, int prev_select) {
        if (depth == N / 2) {
            ArrayList<Integer> selected_1 = new ArrayList<>();
            ArrayList<Integer> selected_2 = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    selected_1.add(i);
                } else {
                    selected_2.add(i);
                }
            }

            int ability_1 = Cal_Ability(selected_1);
            int ability_2 = Cal_Ability(selected_2);

            int dif = Math.abs(ability_1 - ability_2);
            if (ans > dif) {
                ans = dif;
            }

            return;
        }

        for (int i = prev_select + 1; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            dfs(depth + 1, i);
            visited[i] = false;
        }
    }

    static int Cal_Ability(ArrayList<Integer> team) {
        int sum = 0;

        for (int i = 0; i < team.size(); i++) {
            for (int j = i + 1; j < team.size(); j++) {
                sum += (info[team.get(i)][team.get(j)] + info[team.get(j)][team.get(i)]);
            }
        }
        return sum;
    }
}
