package baekjoon;

import java.io.IOException;
import java.util.Scanner;

public class prob1107 {
    static int N;
    static int M;
    static boolean button[];

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        button = new boolean[10];

        for (int i = 0; i < 10; i++) {
            button[i] = true;
        }
        for (int i = 0; i < M; i++) {
            button[sc.nextInt()] = false;
        }
        int ans = 0;

        int bias = 0; // N에서의 거리

        while (true) { // bias만큼 떨어진 거리의 채널이 가능한지를 검색
            if (check(N + bias)) {
                ans = bias + (int) (Math.log10(N) + 1);
                break;
            }

            if (check(N - bias) && (N - bias) >= 0) {
                ans = bias + (int) (Math.log10(N) + 1);
                break;
            }
            bias++;
        }
        if (ans <= (Math.abs(N - 100))) { // 100에서 직접 이동과 비교 후 작은 값 선택
            System.out.println(ans);
        } else {
            System.out.println((Math.abs(N - 100)));
        }
        sc.close();
    }

    public static boolean check(int n) { // 가능한 채널인지 체크
        String str = String.valueOf(n);

        for (int i = 0; i < str.length(); i++) {
            if (!button[str.charAt(i) - '0']) {
                return false;
            }
        }
        return true;
    }
}
