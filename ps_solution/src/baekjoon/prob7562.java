package baekjoon;

import java.util.*;
import java.io.*;

public class prob7562 {
    static int T;
    static int I;
    static boolean[][] visited;
    static int[] d_row = { -2, -1, 1, 2, 2, 1, -1, -2 };
    static int[] d_col = { 1, 2, 2, 1, -1, -2, -2, -1 };

    static class coordinate {
        int row;
        int col;
        int depth;

        public coordinate(int row, int col, int depth) {
            this.row = row;
            this.col = col;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            I = Integer.parseInt(br.readLine());
            visited = new boolean[I][I];

            st = new StringTokenizer(br.readLine());
            coordinate start_cdnt = new coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
            st = new StringTokenizer(br.readLine());
            coordinate arrival_cdnt = new coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

            Queue<coordinate> q = new LinkedList<>();
            q.add(start_cdnt);
            visited[start_cdnt.row][start_cdnt.col] = true;

            while(!q.isEmpty()){
                coordinate now = q.poll();

                if(now.row == arrival_cdnt.row && now.col == arrival_cdnt.col){
                    System.out.println(now.depth);
                    break;
                }

                for(int i=0;i<8;i++){
                    int next_row = now.row + d_row[i];
                    int next_col = now.col + d_col[i];

                    if(next_row >= 0 && next_row < I && next_col >= 0 && next_col < I && !visited[next_row][next_col]){
                        q.add(new coordinate(next_row, next_col, now.depth + 1));
                        visited[next_row][next_col] = true;
                    }
                }
            }
        }
    }
}
