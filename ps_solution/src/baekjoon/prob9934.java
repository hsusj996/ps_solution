package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob9934 {
    static int[][] tree;
    static int[] nodeCnt;
    static int[] arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        int N = (int) Math.pow(2, K) - 1;
        arr = new int[(int) Math.pow(2, K) - 1];
        nodeCnt = new int[K];
        tree = new int[K][];
        for (int i = 0; i < K; i++) {
            tree[i] = new int[(int) Math.pow(2, i)];
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Recursive(0, N - 1, 0);

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < nodeCnt[i]; j++) {
                System.out.print(tree[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void Recursive(int start, int end, int depth) {
        int mid = (start + end) / 2;

        tree[depth][nodeCnt[depth]++] = arr[mid];
        if (mid - start == 1 && end - mid == 1) {
            tree[depth + 1][nodeCnt[depth + 1]++] = arr[start];
            tree[depth + 1][nodeCnt[depth + 1]++] = arr[end];
            return;
        }

        Recursive(start, mid - 1, depth + 1);
        Recursive(mid + 1, end, depth + 1);
    }
}
