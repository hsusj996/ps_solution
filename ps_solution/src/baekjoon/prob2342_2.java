package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob2342_2 {
    static int[][][] dp;
    static List<Integer> inputList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        inputList = new ArrayList<>();
        while(true){
            int v = Integer.parseInt(st.nextToken());
            if(v == 0){
                break;
            }

            inputList.add(v);
        }
        dp = new int[5][5][inputList.size() + 1];

        int result = solve(0, 0, 0);
        System.out.println(result);
    }
    private static int solve(int left, int right, int idx) {
        if(idx == inputList.size()){
            return 0;
        }

        if(dp[left][right][idx] != 0){
            return dp[left][right][idx];
        }

        int next = inputList.get(idx);
        dp[left][right][idx] = Math.min(solve(next, right, idx + 1) + energy(left, next), solve(left, next, idx + 1) + energy(right, next));

        return dp[left][right][idx];
    }
    private static int energy(int before, int after) {
        if(before == 0){
            return 2;
        }

        int diff = (before + 4 - after) % 4;

        if(diff == 0){
            return 1;
        } else if(diff == 1 || diff == 3){
            return 3;
        } else{
            return 4;
        }
    }
}
