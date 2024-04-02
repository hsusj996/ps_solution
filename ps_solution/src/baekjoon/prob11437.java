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

public class prob11437 {
    static int N;
    static int M;
    static StringTokenizer st = null;
    static StringBuilder result = new StringBuilder();
    static List<Integer>[] treeInfo;
    static int[] parentsArr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        treeInfo = new ArrayList[N + 1];
        parentsArr = new int[N + 1];

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
        Queue<Integer> q = new ArrayDeque<>();
        visited[1] = true;
        q.add(1);
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int n : treeInfo[cur]) {
                if (!visited[n]) {
                    parentsArr[n] = cur;
                    visited[n] = true;
                    q.add(n);
                }
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
        // v1 기준으로 루트까지의 이동 경로 set에 기록
        HashSet<Integer> v1Set = new HashSet<>();
        int cur = v1;
        v1Set.add(cur);
        while (parentsArr[cur] != 0) {
            cur = parentsArr[cur];
            v1Set.add(cur);
        }

        // v2 기준으로 루트까지 이동.. 하면서 set을 통해 공통 조상 확인
        int commonAncestor = 0;
        cur = v2;
        if (v1Set.contains(cur)) {
            commonAncestor = cur;
        } else {
            while (parentsArr[cur] != 0) {
                cur = parentsArr[cur];
                if (v1Set.contains(cur)) {
                    commonAncestor = cur;
                    break;
                }
            }
        }

        return commonAncestor;
    }
}
