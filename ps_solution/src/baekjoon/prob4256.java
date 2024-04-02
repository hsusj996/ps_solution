package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob4256 {
    static class Node{
        int num;
        Node parentNode;
        Node leftChild;
        Node rightChild;
        public Node(int num) {
            this.num = num;
        }
    }
    static StringTokenizer st = null;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int[] preOrder;
    static int[] inOrder;
    static int idx;
    static Node[] binaryTree;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            preOrder = new int[N];
            inOrder = new int[N];
            binaryTree = new Node[N + 1];
            for(int i=1;i<=N;i++){
                binaryTree[i] = new Node(i);
            }
            idx = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                preOrder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                inOrder[i] = Integer.parseInt(st.nextToken());
            }

            PostOrder(0, N - 1);

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void PostOrder(int from, int to) {
        if(from > to){
            return;
        }

        int cur = preOrder[idx++];
        int curPos = -1;
        for(int i=from;i<=to;i++){
            if(inOrder[i] == cur){
                curPos = i;
                break;
            }
        }

        PostOrder(from, curPos - 1);
        PostOrder(curPos + 1, to);
        sb.append(cur).append(" ");
    }

}
