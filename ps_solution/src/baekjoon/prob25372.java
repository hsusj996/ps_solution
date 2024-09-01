package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob25372 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            boolean flag = true;

            if(s.length() >= 10 || s.length() <= 5){
                sb.append("no").append("\n");
                continue;
            }

            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    continue;
                }
                flag = false;
                break;
            }

            sb.append(flag ? "yes" : "no").append("\n");
        }

        System.out.println(sb.toString());
    }
}
