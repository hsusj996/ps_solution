package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class prob1074 {
    static int N;
    static int r;
    static int c;
    static Queue<Integer> que;

    public static void main(String[] args) throws IOException {
        que = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1[] = br.readLine().split(" ");

        N = Integer.parseInt(s1[0]);
        r = Integer.parseInt(s1[1]);
        c = Integer.parseInt(s1[2]);

        int index_row[] = { 0, (int) Math.pow(2, N) - 1 };
        int index_col[] = { 0, (int) Math.pow(2, N) - 1 };

        int min = 0;
        int max = (int) Math.pow(Math.pow(2, N), 2) - 1;

        while (true) { // 4분면 탐색 과정
            int row_bound = index_row[0] + (index_row[1] - index_row[0]) / 2;
            int col_bound = index_col[0] + (index_col[1] - index_col[0]) / 2;
            int quadrant = 0;

            if (r >= index_row[0] && r <= row_bound && c >= index_col[0] && c <= col_bound) {       //1사분면
                index_row[1] = row_bound;
                index_col[1] = col_bound;
                quadrant = 1;
            } else if (r >= index_row[0] && r <= row_bound && c > col_bound && c <= index_col[1]) { //2사분면
                index_row[1] = row_bound;
                index_col[0] = col_bound + 1;
                quadrant = 2;
            } else if (r > row_bound && r <= index_row[1] && c >= index_col[0] && c <= col_bound) { //3사분면
                index_row[0] = row_bound + 1;
                index_col[1] = col_bound;
                quadrant = 3;
            } else if (r > row_bound && r <= index_row[1] && c > col_bound && c <= index_col[1]) {  //4사분면
                index_row[0] = row_bound + 1;
                index_col[0] = col_bound + 1;
                quadrant = 4;
            } else {
                return;
            }
            que.add(quadrant);
            if (index_row[1] == index_row[0] && index_col[1] == index_col[0]) {        //사분면 특정이 불가능하면 종료
                break;
            }
        }
        while (!que.isEmpty()) {                    //큐에서 사분면 값을 하나씩 꺼내서 범위를 탐색
            int quadrant = que.poll();

            int range = (max - min + 1) / 4;

            min = min + (range * (quadrant - 1));
            max = min + range - 1;

            if(min == max){                         //사분면이 범위가 아닌 특정 값을 가리킬때 종료
                System.out.println(min);
                break;
            }
        }

    }
}