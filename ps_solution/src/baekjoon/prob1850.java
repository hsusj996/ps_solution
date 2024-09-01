package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1850 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        long a = Long.parseLong(s[0]);
        long b = Long.parseLong(s[1]);

        if(a < b){
            long tmp = a;
            a = b;
            b = tmp;
        }

        long gcd = getGCD(a, b);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<gcd;i++){
            sb.append("1");
        }

        System.out.println(sb.toString());
    }

    private static long getGCD(long a, long b) {
        while(b > 0){
            long tmp = a;
            a = b;
            b = tmp % b;
        }

        return a;
    }
}
