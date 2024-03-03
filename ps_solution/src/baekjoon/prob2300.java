package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prob2300 {
    static class xy implements Comparable<xy> {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(xy o) {
            if (this.x == o.x) {
                return Math.abs(this.y) - Math.abs(o.y);
            }
            return this.x - o.x;
        }
    }

    static int N;
    static xy[] buildings;
    static StringTokenizer st = null;
    static int[] DP;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DP = new int[N + 1];
        buildings = new xy[N + 1];
        buildings[0] = new xy(-2000000, 0);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            buildings[i] = new xy(x, y);
        }

        Arrays.sort(buildings);

        for (int i = 1; i <= N; i++) {
            int d = 0;
            DP[i] = Integer.MAX_VALUE;
            for (int j = i; j >= 1; j--) {
                d = Math.max(d, buildings[i].x - buildings[j].x);
                d = Math.max(d, Math.abs(buildings[j].y) * 2);

                DP[i] = Math.min(DP[i], DP[j - 1] + d);
            }
        }

        System.out.println(DP[N]);
    }
}
