package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob2720 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] change = new int[4];
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            change[0] = N / 25;
            N %= 25;
            change[1] = N / 10;
            N %= 10;
            change[2] = N / 5;
            N %= 5;
            change[3] = N / 1;

            for (int j = 0; j < 4; j++) {
                System.out.print(change[j] + " ");
            }
            System.out.println();
        }
    }
}
