package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class prob15686 {
    static int N;
    static int M;
    static int[][] city;

    static class cdnt {
        int row;
        int col;
        int depth;

        public cdnt(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public cdnt(int row, int col, int depth) {
            this(row, col);
            this.depth = depth;
        }
    }

    static List<cdnt> homeList = new ArrayList<>();
    static List<cdnt> chickentList = new ArrayList<>();
    static List<cdnt> selectedChickenList = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;
    static boolean[] chickenVisited;
    static int[] d_row = { -1, 0, 1, 0 };
    static int[] d_col = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 1) {
                    homeList.add(new cdnt(i, j));
                } else if (city[i][j] == 2) {
                    chickentList.add(new cdnt(i, j));
                }
            }
        }

        chickenVisited = new boolean[chickentList.size()];

        ClosingChicken(0, 0);

        System.out.println(ans);
    }

    static void ClosingChicken(int prev, int depth) {
        if (depth == M) {
            CheckChickenDistance();
        }

        for (int i = prev; i < chickentList.size(); i++) {
            cdnt chicken = chickentList.get(i);
            if (chickenVisited[i]) {
                continue;
            }

            chickenVisited[i] = true;
            selectedChickenList.add(chicken);
            ClosingChicken(i, depth + 1);
            selectedChickenList.remove(selectedChickenList.size() - 1);
            chickenVisited[i] = false;
        }
    }

    static void CheckChickenDistance() {
        int sum = 0;

        for (int i = 0; i < homeList.size(); i++) {
            cdnt home = homeList.get(i);

            sum += DistanceMinChicken(home);
        }
        ans = Math.min(ans, sum);
    }

    static int DistanceMinChicken(cdnt home) {
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < selectedChickenList.size(); i++) {
            cdnt chicken = selectedChickenList.get(i);
            int distance = Math.abs(chicken.row - home.row) + Math.abs(chicken.col - home.col);
            ret = Math.min(ret, distance);
        }
        return ret;
    }
}
