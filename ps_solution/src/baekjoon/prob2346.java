package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class prob2346 {
    static Deque<balloon> deq = new ArrayDeque<>();
    static int N;

    static class balloon {
        int idx;
        int num;

        public balloon(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            deq.add(new balloon(i, Integer.parseInt(st.nextToken())));
        }

        while (true) {
            balloon now = deq.poll();
            result.append(now.idx).append(" ");

            if (deq.isEmpty()) {
                break;
            }
            if (now.num > 0) {
                while (now.num > 1) {
                    deq.add(deq.poll());
                    now.num--;
                }
            } else {
                while (now.num < 0) {
                    deq.addFirst(deq.pollLast());
                    now.num++;
                }
            }
        }

        System.out.println(result);
    }
}
