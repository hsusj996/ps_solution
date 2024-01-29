package baekjoon;

import java.io.*;

public class prob14888 {
    static int N;
    static int[] arr;
    static String[] op_arr;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] input_num = br.readLine().split(" ");
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input_num[i]);
        }

        String[] input_op = br.readLine().split(" ");
        op_arr = new String[N - 1];
        visited = new boolean[N - 1];
        int cnt = 0;

        for (int i = 0; i < Integer.parseInt(input_op[0]); i++) {
            op_arr[cnt++] = "+";
        }
        for (int i = 0; i < Integer.parseInt(input_op[1]); i++) {
            op_arr[cnt++] = "-";
        }
        for (int i = 0; i < Integer.parseInt(input_op[2]); i++) {
            op_arr[cnt++] = "*";
        }
        for (int i = 0; i < Integer.parseInt(input_op[3]); i++) {
            op_arr[cnt++] = "/";
        }

        dfs(arr[0], 0);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int cur, int depth) {
        if (depth == N - 1) {
            max = Math.max(max, cur);
            min = Math.min(min, cur);
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            dfs(cal(op_arr[i], cur, arr[depth + 1]), depth + 1);
            visited[i] = false;
        }
    }

    static int cal(String op, int operand1, int operand2) {
        switch (op.charAt(0)) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                return Integer.MAX_VALUE + 1;
        }
    }
}
