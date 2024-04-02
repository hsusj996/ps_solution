package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prob2250 {
    static class Node {
        int num;
        Node parent;
        Node leftChild;
        Node rightChild;

        public Node(int num) {
            this.num = num;
        }
    }

    static StringTokenizer st = null;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static Node[] nodeArr;
    static int[] levelMin;
    static int[] levelMax;
    static int idx = 1;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodeArr = new Node[N + 1];
        levelMin = new int[N + 1];
        Arrays.fill(levelMin, N);
        levelMax = new int[N + 1];
        Arrays.fill(levelMax, 0);


        for (int i = 1; i <= N; i++) {
            nodeArr[i] = new Node(i);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            if (left != -1) {
                nodeArr[num].leftChild = nodeArr[left];
                nodeArr[left].parent = nodeArr[num];
            }
            if (right != -1) {
                nodeArr[num].rightChild = nodeArr[right];
                nodeArr[right].parent = nodeArr[num];
            }
        }

        int root = -1;
        for(int i=1;i<=N;i++){
            if(nodeArr[i].parent == null){
                root = i;
            }
        }

        preOrder(nodeArr[root], 1);

        int maxWidthLevel = 0;
        int maxWidth = 0;
        for(int i=1;i<=N;i++){
            int width = levelMax[i] - levelMin[i] + 1;
            if(maxWidth < width){
                maxWidthLevel = i;
                maxWidth = width;
            }
        }

        System.out.println(maxWidthLevel + " " + maxWidth);
    }

    private static void preOrder(Node n, int level) {
        if(n.leftChild != null){
            preOrder(n.leftChild, level + 1);
        }

        levelMin[level] = Math.min(levelMin[level], idx);
        levelMax[level] = Math.max(levelMax[level], idx);
        idx++;

        if(n.rightChild != null){
            preOrder(n.rightChild, level + 1);
        }
    }
}
