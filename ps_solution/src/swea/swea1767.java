package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class swea1767 {
    static int[][] map;
    static int T, N;
    static StringBuilder result = new StringBuilder();
    static int maxConnectedCore;
    static int sumOfWireLength;
    static List<Cdnt> coreList;
    static int[] d_row = { -1, 0, 1, 0 };
    static int[] d_col = { 0, 1, 0, -1 };

    static class Cdnt {
        int row;
        int col;

        Cdnt(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            maxConnectedCore = 0;
            sumOfWireLength = 0;

            N = Integer.parseInt(br.readLine());
            map = new int[N + 1][N + 1];
            coreList = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        coreList.add(new Cdnt(i, j));
                    }
                }
            }

            BackTracking(0, 0);

            result.append("#" + test_case + " " + sumOfWireLength + "\n");
        }

        System.out.println(result);
    }

    private static void BackTracking(int depth, int connectedCoreCnt) {
        if (depth == coreList.size()) {
            if (connectedCoreCnt >= maxConnectedCore) {
                int sum = 0;
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (map[i][j] == 2) {
                            sum++;
                        }
                    }
                }

                if (connectedCoreCnt > maxConnectedCore) {
                    maxConnectedCore = connectedCoreCnt;
                    sumOfWireLength = sum;
                } else {
                    sumOfWireLength = Math.min(sumOfWireLength, sum);
                }
            }
            return;
        }

        int row = coreList.get(depth).row;
        int col = coreList.get(depth).col;

        if(row == 1 || row == N || col == 1 || col == N){
            BackTracking(depth + 1, connectedCoreCnt + 1);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int newRow = row;
            int newCol = col;
            boolean flag = false;
            if (IsPossibleWireSet(newRow, newCol, i)) {
                flag = true;
                while (true) {
                    newRow += d_row[i];
                    newCol += d_col[i];
                    if (IsOutBound(newRow, newCol)) {
                        break;
                    }

                    map[newRow][newCol] = 2;
                }
            }

            if (flag) {
                BackTracking(depth + 1, connectedCoreCnt + 1);
                // 철거
                while (true) {
                    newRow -= d_row[i];
                    newCol -= d_col[i];

                    if (newRow == row && newCol == col) {
                        break;
                    }

                    map[newRow][newCol] = 0;
                }
            } else {
                BackTracking(depth + 1, connectedCoreCnt);
            }
        }
    }

    private static boolean IsPossibleWireSet(int row, int col, int direction) {
        while (true) {
            row += d_row[direction];
            col += d_col[direction];

            if (IsOutBound(row, col)) {
                return true;
            }

            if (map[row][col] != 0) {
                return false;
            }
        }
    }

    private static boolean IsOutBound(int row, int col) {
        return row < 1 || row > N || col < 1 || col > N;
    }
}