package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1213 {
    static char centerChar = '0';
    static int[] cntArr = new int[27];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int strSize = input.length();

        for (int i = 0; i < strSize; i++) {
            cntArr[input.charAt(i) - 'A']++;
        }

        boolean flag = true;
        for (int i = 0; i < 27; i++) {
            if (cntArr[i] % 2 == 1) {
                if (centerChar != '0') {
                    flag = false;
                }
                centerChar = (char) (i + 'A');
            }
        }

        if (!flag) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 27; i++) {
            while (cntArr[i] >= 2) {
                sb.append((char) (i + 'A'));
                cntArr[i] -= 2;
            }
        }
        String str1 = sb.toString();
        if (strSize % 2 == 1) {
            sb.append(centerChar);
        }
        for (int i = str1.length() - 1; i >= 0; i--) {
            sb.append(str1.charAt(i));
        }

        System.out.println(sb.toString());
    }
}
