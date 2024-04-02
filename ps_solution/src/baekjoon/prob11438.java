package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob11438 {
    static class Node {
        int n;
        int depth;

        public Node(int n, int depth) {
            this.n = n;
            this.depth = depth;
        }
    }

    static int N;
    static int M;
    static StringTokenizer st = null;
    static StringBuilder result = new StringBuilder();
    static List<Integer>[] treeInfo;
    static int[][] parentsArr;
    static int[] depthArr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        treeInfo = new ArrayList[N + 1];
        parentsArr = new int[N + 1][19];
        depthArr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            treeInfo[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            treeInfo[v1].add(v2);
            treeInfo[v2].add(v1);
        }

        // 부모자식 정보가 없는 트리를 가공하기
        boolean[] visited = new boolean[N + 1];
        Queue<Node> q = new ArrayDeque<>();
        visited[1] = true;
        q.add(new Node(1, 1));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            depthArr[cur.n] = cur.depth;

            for (int c : treeInfo[cur.n]) {
                if (!visited[c]) {
                    parentsArr[c][0] = cur.n;
                    visited[c] = true;
                    q.add(new Node(c, cur.depth + 1));
                }
            }
        }

        for (int j = 1; j <= 18; j++) {
            for (int i = 1; i <= N; i++) {
                parentsArr[i][j] = parentsArr[parentsArr[i][j - 1]][j - 1];
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            result.append(LCS(v1, v2) + "\n");
        }

        System.out.println(result);
    }

    private static int LCS(int v1, int v2) {
        // 한쪽 끌어올리기
        if (depthArr[v1] > depthArr[v2]) {
            for (int i = 18; i >= 0; i--) {
                if (depthArr[parentsArr[v1][i]] >= depthArr[v2]) {
                    v1 = parentsArr[v1][i];
                }
            }
        } else {
            for (int i = 18; i >= 0; i--) {
                if (depthArr[parentsArr[v2][i]] >= depthArr[v1]) {
                    v2 = parentsArr[v2][i];
                }
            }
        }

        // 공통 조상 찾기
        if (v1 == v2) {
            return v1;
        } else {
            for (int i = 18; i >= 0; i--) {
                if (parentsArr[v1][i] != parentsArr[v2][i]) {
                    v1 = parentsArr[v1][i];
                    v2 = parentsArr[v2][i];
                }
            }
        }

        return parentsArr[v1][0];
    }
}
