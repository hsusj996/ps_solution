package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class prob15663 {
    static int N;
    static int M;

    static class num {
        int n;
        int cnt;

        public num(int n) {
            this.n = n;
            this.cnt = 1;
        }
    }

    static Set<String> ans = new LinkedHashSet<>();
    static List<num> list = new ArrayList<>();
    static int[] buf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        buf = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            boolean flag = false;
            for (num t : list) {
                if (input == t.n) {
                    t.cnt++;
                    flag = true;
                    break;
                }
            }
            if (!flag)
                list.add(new num(input));
        }

        list.sort((o1, o2) -> o1.n - o2.n);

        BackTracking(0);

        StringBuilder sb = new StringBuilder();
        for (String s : ans) {
            sb.append(s + "\n");
        }

        System.out.println(sb);
    }

    static void BackTracking(int depth) {
        if (depth == M) {
            StringBuilder sb2 = new StringBuilder();
            for (int n : buf) {
                sb2.append(n).append(' ');
            }
            ans.add(sb2.toString());
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).cnt == 0) {
                continue;
            }
            list.get(i).cnt--;
            buf[depth] = list.get(i).n;
            BackTracking(depth + 1);
            list.get(i).cnt++;
        }
    }
}
