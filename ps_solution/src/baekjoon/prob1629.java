package baekjoon;

import java.io.*;
import java.util.*;

public class prob1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());
        long C = Integer.parseInt(st.nextToken());
        long ans = 1;

        if (A > C) {
            A = A % C;
        }

        while (true) {
            if (B == 1) {
                break;
            }

            if (B % 2 == 1) {
                ans = (ans * A) % C;
                B--;
            }
            A = (A * A) % C;
            B /= 2;
        }

        ans = (ans * A) % C;

        System.out.println(ans);
    }
}
