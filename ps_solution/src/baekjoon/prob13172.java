package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob13172 {
    static int M;
    static long N = 1;
    static long S = 0;
    static int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long s = Long.parseLong(st.nextToken());

            S = N * s + n * S;
            N = N * n;
            N %= MOD;
            S %= MOD;
        }

        if (S % N != 0) {
            System.out.println((Fermet(N, MOD - 2) * S) % MOD);
        } else {
            System.out.println(S / N);
        }
    }

    static long Fermet(long base, long exponent) {
        long coef = 1;
        while (true) {
            if (exponent == 0) {
                break;
            }

            if (exponent % 2 == 1) {
                coef *= base;
                exponent--;
                coef %= MOD;
            } else {
                base *= base;
                base %= MOD;
                exponent /= 2;
            }
        }

        return coef;
    }
}
