package baekjoon;

import java.util.Scanner;

public class prob5525_2 {
    static int N;
    static int M;
    static String S;
    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder("I");
        N = sc.nextInt();
        M = sc.nextInt();
        S = sc.next();

        for (int i = 0; i < N; i++) {
            sb.append("OI");
        }
        String target = sb.toString();
        checkString(target);

        System.out.println(ans);
        sc.close();
        return;
    }

    public static void checkString(String target) {
        for (int i = 0; i < M; i++) {
            if (S.charAt(i) == 'I') {
                int oi_cnt = 0;
                while (true) {
                    if (i + 2 >= M) {
                        break;
                    }
                    if (S.substring(i + 1, i + 3).compareTo("OI") == 0) {
                        oi_cnt++;
                        i += 2;
                    } else {
                        break;
                    }
                }
                if (oi_cnt >= N) {
                    ans += (oi_cnt - N + 1);
                }
            }
        }
        return;
    }
}
