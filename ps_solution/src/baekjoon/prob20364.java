package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class prob20364 {
    static HashSet<Integer> groundSet = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        groundSet.add(1);

        for (int i = 0; i < Q; i++) {
            int x = Integer.parseInt(br.readLine());
            boolean flag = false;
            int blockNode = -1;
            for (int cur = x; cur > 1; cur /= 2) {
                if (groundSet.contains(cur)) {
                    blockNode = cur;
                    flag = true;
                }
            }

            if (flag) {
                sb.append(blockNode);
            } else {
                groundSet.add(x);
                sb.append(0);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
