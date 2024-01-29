package baekjoon;

import java.util.*;
import java.io.*;

public class prob16953 {
    static long A;
    static long B;
    static boolean flag;

    static class node {
        long num;
        int depth;

        public node(long num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        Queue<node> q = new LinkedList<>();

        q.add(new node(A, 0));
        while (!q.isEmpty()) {
            node now = q.poll();

            if (now.num == B) {
                flag = true;
                System.out.println(now.depth + 1);
                break;
            }

            long caseA = now.num * 2;
            long caseB = now.num * 10 + 1;

            if (caseA <= B) {
                q.add(new node(caseA, now.depth + 1));
            }
            if (caseB <= B) {
                q.add(new node(caseB, now.depth + 1));
            }
        }

        if (!flag) {
            System.out.println(-1);
        }
    }
}
