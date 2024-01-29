package baekjoon;

import java.util.*;
import java.io.*;

public class prob1406 {
    static String s;
    static LinkedList<Character> s_link;
    static int cursur;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s_link = new LinkedList<>();
        s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            s_link.add(s.charAt(i));
        }
        cursur = s.length();

        M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if (cmd.equals("L")) {
                cmd_L();
            } else if (cmd.equals("D")) {
                cmd_D();
            } else if (cmd.equals("B")) {
                cmd_B();
            } else if (cmd.equals("P")) {
                String input = st.nextToken();
                cmd_P(input);
            } else {
                System.out.println("invalid cmd");
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s_link.size();i++){
            sb.append(s_link.get(i));
        }

        System.out.println(sb.toString());
    }

    static void cmd_L() {
        if (cursur == 0) {
            return;
        }
        cursur--;
        return;
    }

    static void cmd_D() {
        if (cursur == s_link.size()) {
            return;
        }
        cursur++;
        return;
    }

    static void cmd_B() {
        if (cursur == 0) {
            return;
        }
        s_link.remove(cursur - 1);
        cursur--;
        return;
    }

    static void cmd_P(String input) {
        s_link.add(cursur, input.charAt(0));
        cursur++;
        return;
    }
}
