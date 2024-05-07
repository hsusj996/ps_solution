package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea1288 {
    static int T;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ");
            int N = Integer.parseInt(br.readLine());
            int N1 = N;
            int x = 0;
            while (true) {
                // N의 자리수 조사
                int N2 = N;
                // 몇째자리인지 파악
                int p = 10;
                while (p-- > 0) {
                    if (N2 >= Math.pow(10, p)) {
                        break;
                    }
                }

                // 각 자리수 파악
                while (p >= 0) {
                    int n = (int) (N2 / (Math.pow(10, p)));
                    // 비트 연산
                    x |= (1 << n);
                    // N2 수정
                    N2 = (int) (N2 % (Math.pow(10, p)));
                    p--;
                }

                if (x == (1 << 10) - 1) {
                    sb.append(N).append("\n");
                    break;
                }

                N += N1;
            }

        }
        System.out.println(sb.toString());
    }
}
