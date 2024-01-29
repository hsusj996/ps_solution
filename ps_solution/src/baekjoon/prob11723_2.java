package baekjoon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class prob11723_2 {
    static int M;
    static HashSet<Integer> set;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        set = new HashSet<>();

        while (M-- > 0) {
            String cmd = sc.next();

            if (cmd.equals("add")) {
                int n = sc.nextInt();
                set.add(n);
            } else if (cmd.equals("remove")) {
                int n = sc.nextInt();
                set.remove(n);
            } else if (cmd.equals("check")) {
                int n = sc.nextInt();
                if (set.contains(n)) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (cmd.equals("toggle")) {
                int n = sc.nextInt();
                if (set.contains(n)) {
                    set.remove(n);
                } else {
                    set.add(n);
                }
            } else if (cmd.equals("all")) {
                set = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20));
            } else if (cmd.equals("empty")) {
                set.removeAll(set);
            } else {
                System.out.println("error : wrong cmd");
            }
        }
        sc.close();
        return;
    }
}
