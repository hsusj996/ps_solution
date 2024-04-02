package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class prob3584 {
    static class Node {
        int num;
        Node parent;
        List<Node> childrenList;

        public Node(int num) {
            this.num = num;
            childrenList = new ArrayList<>();
        }
    }

    static int T;
    static StringTokenizer st = null;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            Node[] nodeArr = new Node[N + 1];
            for (int i = 1; i <= N; i++) {
                nodeArr[i] = new Node(i);
            }
            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                nodeArr[parent].childrenList.add(nodeArr[child]);
                nodeArr[child].parent = nodeArr[parent];
            }

            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            // v1 기준으로 루트까지의 이동 경로 set에 기록
            HashSet<Integer> v1Set = new HashSet<>();
            int cur = v1;
            v1Set.add(cur);
            while (nodeArr[cur].parent != null) {
                cur = nodeArr[cur].parent.num;
                v1Set.add(cur);
            }

            // v2 기준으로 루트까지 이동.. 하면서 set을 통해 공통 조상 확인
            int commonAncestor = 0;
            cur = v2;
            if (v1Set.contains(cur)) {
                commonAncestor = cur;
            } else {
                while (nodeArr[cur].parent != null) {
                    cur = nodeArr[cur].parent.num;
                    if (v1Set.contains(cur)) {
                        commonAncestor = cur;
                        break;
                    }
                }
            }

            System.out.println(commonAncestor);
        }
    }
}