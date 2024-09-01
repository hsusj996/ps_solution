package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob28702 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = new String[3];
        for (int i = 0; i < 3; i++) {
            s[i] = br.readLine();
        }

        for (int i = 0; i < 3; i++) {
            if (s[i].equals("Fizz") || s[i].equals("Buzz") || s[i].equals("FizzBuzz")) {
                continue;
            } else {
                int n = Integer.parseInt(s[i]);
                n += (3 - i);
                if (n % 3 == 0 && n % 5 == 0) {
                    System.out.println("FizzBuzz");
                } else if (n % 3 == 0) {
                    System.out.println("Fizz");
                } else if (n % 5 == 0) {
                    System.out.println("Buzz");
                } else {
                    System.out.println(n);
                }
                break;
            }
        }
    }
}
