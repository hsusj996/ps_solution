package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class prob11723 {
    static int M;
    static int[] map = new int[21];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        Arrays.fill(map, 0);

        while (M-- > 0) {
            String cmd = sc.next();

            if (cmd.equals("add")) {
                int n = sc.nextInt();
                map[n] = 1;
            } else if (cmd.equals("remove")) {
                int n = sc.nextInt();
                map[n] = 0;
            } else if (cmd.equals("check")) {
                int n = sc.nextInt();
                if (map[n] == 1) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (cmd.equals("toggle")) {
                int n = sc.nextInt();
                map[n] = (map[n] == 1) ? 0 : 1;
            } else if (cmd.equals("all")) {
                Arrays.fill(map, 1);
            } else if (cmd.equals("empty")) {
                Arrays.fill(map, 0);
            } else {
                System.out.println("error : wrong cmd");
            }
        }
        sc.close();
        return;
    }
}
