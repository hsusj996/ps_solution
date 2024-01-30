package baekjoon;

import java.util.*;

public class prob1072 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int y = sc.nextInt();

        int target = (int) ((double) y / x * 100);

        if (target >= 99) {
            System.out.println(-1);
            sc.close();
            return;
        }

        int n = Integer.toString(x).length();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append("0");
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 10; j++) {
                long tmp = (int) (Integer.parseInt(sb.toString()) + j * Math.pow(10, n - i - 1));

                int z = (int) ((double) (y + tmp) / (x + tmp) * 100);

                if (z > target) {
                    sb.replace(i, i + 1, Integer.toString(j - 1));
                    break;
                }
            }
        }
        System.out.println(Integer.parseInt(sb.toString()) + 1);

        sc.close();
    }
}