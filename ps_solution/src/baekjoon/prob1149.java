package baekjoon;

import java.util.Scanner;

public class prob1149 {
    static int[][] color_cost;
    static int N;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        color_cost = new int[N][3];

        for(int i=0;i<N;i++){
            color_cost[i][0] = sc.nextInt();
            color_cost[i][1] = sc.nextInt();
            color_cost[i][2] = sc.nextInt();
        }

        for(int i=0;i<3;i++){
            paint_house(i, 0, 0);
        }

        System.out.println(ans);

        sc.close();
        return;
    }    
    public static void paint_house(int color, int cur_house, int cost){
        cost += color_cost[cur_house][color];

        if(cur_house == N - 1){
            ans = Math.min(ans, cost);
        }else{
            for(int i=0;i<3;i++){
                if(i == color){
                    continue;
                }else{
                    paint_house(i, cur_house+1, cost);
                }
            }
        }
    }
}
