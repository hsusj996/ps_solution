package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * prob1032
 */
public class prob1032 {
    static int N;
    static String[] inputArr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        inputArr = new String[N];
        for (int i = 0; i < N; i++) {
            inputArr[i] = br.readLine();
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inputArr[0].length(); i++) {
            char c = inputArr[0].charAt(i);
            boolean flag = true;
            for (int j = 1; j < N; j++) {
                if (c != inputArr[j].charAt(i)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                result.append(c);
            } else {
                result.append("?");
            }
        }

        System.out.println(result);
    }
}