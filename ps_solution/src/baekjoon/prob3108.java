package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob3108 {
    static class xy {
        int x, y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    static int N;
    static xy[][] arr;
    static int[] parents;
    static StringTokenizer st = null;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new xy[N + 1][4];
        parents = new int[N + 1];
        initSet();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            arr[i][0] = new xy(x1, y1);
            arr[i][1] = new xy(x2, y1);
            arr[i][2] = new xy(x1, y2);
            arr[i][3] = new xy(x2, y2);
        }

        arr[N][0] = new xy(0, 0);
        arr[N][1] = new xy(0, 0);
        arr[N][2] = new xy(0, 0);
        arr[N][3] = new xy(0, 0);

        for (int i = 0; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (checkRelation(arr[i], arr[j]) == 1) {
                    union(i, j);
                } else {
                    continue;
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (parents[i] == i) {
                cnt++;
            }
        }

        if (parents[N] != N) {
            cnt--;
        }

        System.out.println(cnt);
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot != bRoot) {
            parents[bRoot] = aRoot;
        }
    }

    private static int find(int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    private static int initSet() {
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
        return 0;
    }

    private static int checkRelation(xy[] r1, xy[] r2) {
        if ((r1[0].x < r2[0].x && r2[3].x < r1[3].x && r1[0].y < r2[0].y && r2[3].y < r1[3].y)
                || (r1[0].x > r2[0].x && r2[3].x > r1[3].x && r1[0].y > r2[0].y && r2[3].y > r1[3].y)
                || r1[3].x < r2[0].x || r1[0].x > r2[3].x || r1[3].y < r2[0].y || r1[0].y > r2[3].y) {
            return 0;
        }
        return 1;
    }
}
