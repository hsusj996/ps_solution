package baekjoon;

import java.util.Scanner;

public class prob14500 {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 0;
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                arr[i][j] = sc.nextInt();
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                visited[i][j] = true;
                DFS(i, j, arr[i][j], 1);

                visited[i][j] = false;

                remain(i, j, arr[i][j], 1);
            }
        }

        System.out.println(ans);
        sc.close();
        return;
    }
    public static void DFS(int row, int col, int sum, int cnt){
        if(cnt == 4){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i=0;i<4;i++){
            int cur_row = row + dx[i];
            int cur_col = col + dy[i];

            if(cur_row < 0 || cur_row >= N || cur_col < 0 || cur_col >= M){
                continue;
            }

            if(!visited[cur_row][cur_col]){
                visited[cur_row][cur_col] = true;
                DFS(cur_row, cur_col, sum + arr[cur_row][cur_col], cnt+1);
                visited[cur_row][cur_col] = false;
            }
        }
    }
    public static void remain(int row, int col, int sum, int cnt){
        if(cnt == 4){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i=0;i<4;i++){
            int cur_row = row + dx[i];
            int cur_col = col + dy[i];

            if(cur_row < 0 || cur_row >= N || cur_col < 0 || cur_col >= M){
                continue;
            }

            if(!visited[cur_row][cur_col]){
                visited[cur_row][cur_col] = true;
                remain(row, col, sum + arr[cur_row][cur_col], cnt+1);
                visited[cur_row][cur_col] = false;
            }
        }
    }
}
