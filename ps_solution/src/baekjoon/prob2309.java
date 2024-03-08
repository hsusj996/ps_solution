package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class prob2309 {
    static int[] selected = new int[7];
    static int[] tall = new int[9];
    static boolean flag;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            tall[i] = sc.nextInt();
        }

        bruteforce(0, 0);
        sc.close();
    }

    private static void bruteforce(int prev, int depth) {
        if (flag) {
            return;
        }

        if (depth == 7) {
            int sum = 0;
            for (int i = 0; i < 7; i++) {
                sum += selected[i];
            }

            if (sum == 100) {
                flag = true;
                Arrays.sort(selected);
                for (int i = 0; i < 7; i++) {
                    System.out.println(selected[i]);
                }
            }

            return;
        }

        for (int i = prev; i < 9; i++) {
            selected[depth] = tall[i];
            bruteforce(i + 1, depth + 1);
        }
    }
}
