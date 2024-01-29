package baekjoon;

import java.util.Scanner;

public class prob11659 {
    static int N;
    static int M;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        while (M-- > 0) {
            int left = sc.nextInt();
            int right = sc.nextInt();

            int sum = 0;
            for (int i = left - 1; i <= right - 1; i++) {
                sum += arr[i];
            }
            System.out.println(sum);
        }
        sc.close();
        return;
    }
}
