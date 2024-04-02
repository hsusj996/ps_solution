package baekjoon;

import java.util.Scanner;

public class prob9372 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            for (int i = 0; i <= M; i++) {
                sc.nextLine();
            }

            sb.append(N - 1).append("\n");
        }

        System.out.println(sb.toString());
    }
}
