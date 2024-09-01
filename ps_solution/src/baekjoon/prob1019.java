package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1019 {
    static int count = 1;
    static int[] arr = new int[10];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int B = Integer.parseInt(br.readLine());
        int A = 1;

        while (A <= B) {
            while (B % 10 != 9 && A <= B) {
                check(B);
                B--;
            }

            while (A % 10 != 0 && A <= B) {
                check(A);
                A++;
            }

            if (A > B) {
                break;
            }

            A /= 10;
            B /= 10;

            for (int i = 0; i < 10; i++) {
                arr[i] += (B - A + 1) * count;
            }

            count *= 10;
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void check(int n) {
        while (n > 0) {
            arr[n % 10] += count;
            n /= 10;
        }
    }
}
