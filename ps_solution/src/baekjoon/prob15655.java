package baekjoon;

import java.util.Arrays;
import java.util.Scanner;


public class prob15655 {
    static int N;
    static int M;
    static int[] numArr;
    static int[] selectedNum;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        numArr = new int[N];
        selectedNum = new int[M];

        for (int i = 0; i < N; i++) {
            numArr[i] = sc.nextInt();
        }

        Arrays.sort(numArr);
        BackTracking(0, 0);
        System.out.println(result);

        sc.close();
    }

    private static void BackTracking(int prev, int depth) {
        if (depth == M) {
            for (int i : selectedNum) {
                result.append(i + " ");
            }
            result.append("\n");
            return;
        }

        for (int i = prev; i < N; i++) {
            selectedNum[depth] = numArr[i];
            BackTracking(i + 1, depth + 1);
        }
    }
}
