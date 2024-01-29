package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class prob10812 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        while (M-- > 0) {
            int[] rotateArr = new int[N];
            Arrays.fill(rotateArr, -1);

            int i = sc.nextInt() - 1;
            int j = sc.nextInt() - 1;
            int k = sc.nextInt() - 1;

            int index_rotate = i;

            for (int index = k; index <= j; index++) {
                rotateArr[index_rotate++] = arr[index];
            }
            for (int index = i; index < k; index++) {
                rotateArr[index_rotate++] = arr[index];
            }

            for (int index = 0; index < N; index++) {
                if (rotateArr[index] != -1) {
                    arr[index] = rotateArr[index];
                }
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(arr[i] + " ");
        }

        System.out.println(sb.toString());

        sc.close();
        return;
    }
}
