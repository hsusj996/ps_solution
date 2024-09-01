package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob9177 {
    static int N;
    static StringTokenizer st = null;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            String target = st.nextToken();

            sb.append("Data set " + (i + 1));

            if (bfs(s1, s2, target)) {
                sb.append(": yes");
            } else {
                sb.append(": no");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static boolean bfs(String s1, String s2, String target) {
        boolean[][] visited = new boolean[205][205];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));
        visited[0][0] = true;
        int targetSize = target.length();

        while (!q.isEmpty()) {
            Node cur = q.poll();

            int targetPoint = cur.p1 + cur.p2;

            if (cur.p1 < s1.length() && target.charAt(targetPoint) == s1.charAt(cur.p1) && !visited[cur.p1 + 1][cur.p2]) {
                if (targetPoint == targetSize - 1) {
                    return true;
                }

                q.add(new Node(cur.p1 + 1, cur.p2));
                visited[cur.p1 + 1][cur.p2] = true;
            }
            if (cur.p2 < s2.length() && target.charAt(targetPoint) == s2.charAt(cur.p2) && !visited[cur.p1][cur.p2 + 1]) {
                if (targetPoint == targetSize - 1) {
                    return true;
                }
                q.add(new Node(cur.p1, cur.p2 + 1));
                visited[cur.p1][cur.p2 + 1] = true;

            }
        }

        return false;
    }

    static class Node {
        int p1;
        int p2;

        public Node(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
}