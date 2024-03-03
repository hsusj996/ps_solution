package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea1952 {
    static StringBuilder result = new StringBuilder();
    static StringTokenizer st = null;
    static int T;
    static int[] cost;
    static int[] plan;
    static int Bound1Month;
    static int costSum;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            result.append("#").append(test_case).append(" ");
            cost = new int[4];
            plan = new int[13];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }
            costSum = Integer.MAX_VALUE;

            DFS(1, 0);


            // for (int i = 1; i <= 12; i++) {
            //     if (plan[i] == 0) {
            //         continue;
            //     }

            //     int cost1Month = 0;
            //     for (int j = i; j <= 12 && j < i + 3; j++) {
            //         if (plan[j] > 0) {
            //             cost1Month += Math.min(cost[1], plan[j] * cost[0]);
            //         }
            //     }

            //     if (cost1Month > cost[2]) {
            //         costSum += cost[2];
            //         i += 2;
            //     } else {
            //         costSum += Math.min(cost[1], plan[i] * cost[0]);
            //     }
            // }
            costSum = Math.min(costSum, cost[3]);

            result.append(costSum).append("\n");
        }
        System.out.println(result);
    }

    private static void DFS(int prev, int sum) {
        if(prev > 12){
            costSum = Math.min(costSum, sum);
            return;
        }

        if(plan[prev] == 0){
            DFS(prev + 1, sum);
        } else{
            DFS(prev + 3, sum + cost[2]);
            DFS(prev + 1, sum + Math.min(cost[1], cost[0] * plan[prev]));
        }
    }
}
