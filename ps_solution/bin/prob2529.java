package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob2529 {
    static int k;
    static char[] arr;
    static int[] numArr;
    static boolean[] visited;
    static String min = "100000000000";
    static String max = "-100000000000";

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());
        arr = new char[k];
        numArr = new int[k + 1];
        visited = new boolean[10];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        for (int i = 0; i <= 9; i++) {
            numArr[0] = i;
            visited[i] = true;
            Combi(1, i);
            visited[i] = false;
        }

        System.out.println(max);
        System.out.println(min);
    }

    private static void Combi(int depth, int prev) {
        if (depth == k + 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= k; i++) {
                sb.append(numArr[i]);
            }

            long iMin = Long.parseLong(min);
            long iMax = Long.parseLong(max);

            if(iMin > Long.parseLong(sb.toString())){
                min = sb.toString();
            }
            if(iMax < Long.parseLong(sb.toString())){
                max = sb.toString();
            }
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (visited[i]) {
                continue;
            }

            if (arr[depth - 1] == '<' && prev > i) {
                continue;
            } else if (arr[depth - 1] == '>' && prev < i) {
                continue;
            }

            numArr[depth] = i;
            visited[i] = true;
            Combi(depth + 1, i);
            visited[i] = false;
        }
    }
}
