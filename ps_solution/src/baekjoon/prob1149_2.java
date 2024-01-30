package baekjoon;

import java.util.Scanner;

public class prob1149_2 {
    static int[][] color_cost;
    static int[][] min_cost;
    static int N;
    static int ans;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        color_cost = new int[N][3];
        min_cost = new int[N][3];

        for(int i=0;i<N;i++){
            color_cost[i][0] = sc.nextInt();
            color_cost[i][1] = sc.nextInt();
            color_cost[i][2] = sc.nextInt();
        }

        min_cost[0][0] = color_cost[0][0];
        min_cost[0][1] = color_cost[0][1];
        min_cost[0][2] = color_cost[0][2];

        ans = Math.min(paint_house(N - 1, 0), Math.min(paint_house(N - 1, 1), paint_house(N - 1, 2)));
        System.out.println(ans);

        sc.close();
        return;
    }  

    public static int paint_house(int N, int color){
        if(min_cost[N][color] == 0){
            if(color == 0){
                min_cost[N][0] = Math.min(paint_house(N - 1, 1), paint_house(N - 1, 2)) + color_cost[N][0];
            }else if(color == 1){
                min_cost[N][1] = Math.min(paint_house(N - 1, 0), paint_house(N - 1, 2)) + color_cost[N][1];
            }else{
                min_cost[N][2] = Math.min(paint_house(N - 1, 0), paint_house(N - 1, 1)) + color_cost[N][2];
            }
        }

        return min_cost[N][color];
    }
}
