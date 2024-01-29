package baekjoon;

import java.io.IOException;
import java.util.Scanner;

public class prob1107_2 {
    static int N;
    static int M;

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        boolean button[] = new boolean[10];

        for (int i = 0; i < 10; i++) {
            button[i] = true;
        }
        for (int i = 0; i < M; i++) {
            button[sc.nextInt()] = false;
        }

        int ans = Math.abs(N - 100);

        for (int tmp = 0; tmp <= 999999; tmp++) {
            String str = String.valueOf(tmp);
            int len = str.length();

            boolean check = true;
            for (int j = 0; j < len; j++) {
                if (!button[str.charAt(j) - '0']) {
                    check = false;
                    break;
                }
            }
            if (check) {
                int min = Math.abs(N - tmp) + len;
                ans = Math.min(min, ans);
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
