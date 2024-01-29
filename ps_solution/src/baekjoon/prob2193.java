package baekjoon;

import java.util.*;

public class prob2193 {
    static int N;
    static int[] binary;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        bfs(1, 1);

        System.out.println(ans);
    }

    static void bfs(int prev, int depth) {
        if (depth == N) {
            ans++;
            return;
        }

        if (prev == 0) {
            bfs(1, depth + 1);
            bfs(0, depth + 1);
        } else {
            bfs(0, depth + 1);
        }
    }
}
