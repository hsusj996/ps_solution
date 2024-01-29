package baekjoon;

import java.util.*;
import java.io.*;

public class prob2512 {
    static int N;
    static Queue<Integer> q;
    static int budget;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        q = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            q.add(Integer.parseInt(st.nextToken()));
        }

        budget = Integer.parseInt(br.readLine());

        while (!q.isEmpty()) {
            int qsize = q.size();
            int avg = budget / qsize;
            if(avg == 0){
                break;
            }
            int allocated_budget = 0;
            for (int i = 0; i < qsize; i++) {
                int need = q.poll();

                if (need <= avg) {
                    if(need > allocated_budget){
                        allocated_budget = need;
                    }
                    budget -= need;
                } else {
                    if(avg > allocated_budget){
                        allocated_budget = avg;
                    }
                    budget -= avg;
                    q.add(need - avg);
                }
            }
            ans += allocated_budget;
        }

        System.out.println(ans);

        return;
    }
}
