package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class prob5397 {
    static int T;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String input = br.readLine();

            Deque<Character> dq1 = new ArrayDeque<>();
            Deque<Character> dq2 = new ArrayDeque<>();
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                switch (c) {
                    case '<':
                        if (dq1.isEmpty()) {
                            continue;
                        }
                        dq2.addFirst(dq1.removeLast());
                        break;
                    case '>':
                        if (dq2.isEmpty()) {
                            continue;
                        }
                        dq1.addLast(dq2.removeFirst());
                        break;
                    case '-':
                        if (dq1.isEmpty()) {
                            continue;
                        }
                        dq1.removeLast();
                        break;
                    default:
                        dq1.addLast(c);
                        break;
                }
            }

            dq1.forEach(o -> sb.append(o));
            dq2.forEach(o -> sb.append(o));
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
