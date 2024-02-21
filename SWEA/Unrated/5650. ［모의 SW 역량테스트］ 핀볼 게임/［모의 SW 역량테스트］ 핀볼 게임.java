import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] DirectionConvert = {
            { 0, 0, 0, 0 },
            { 2, 3, 1, 0 },
            { 1, 3, 0, 2 },
            { 3, 2, 0, 1 },
            { 2, 0, 3, 1 },
            { 2, 3, 0, 1 }
    };
    static int[][][] wormHoleInfo;
    static int[] wormHoleCnt;
    static int[] d_row = { -1, 0, 1, 0 };
    static int[] d_col = { 0, 1, 0, -1 };
    static int[][] map;
    static int T;
    static int N;
    static StringBuilder result = new StringBuilder();
    static int MaxScore;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            result.append("#").append(test_case).append(" ");
            MaxScore = 0;
            N = Integer.parseInt(br.readLine().trim());

            map = new int[N][N];
            wormHoleCnt = new int[11];
            wormHoleInfo = new int[11][2][2];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6 && map[i][j] <= 10) {
                        if (wormHoleCnt[map[i][j]] == 0) {
                            wormHoleInfo[map[i][j]][0][0] = i;
                            wormHoleInfo[map[i][j]][0][1] = j;
                            wormHoleCnt[map[i][j]]++;
                        } else {
                            wormHoleInfo[map[i][j]][1][0] = i;
                            wormHoleInfo[map[i][j]][1][1] = j;
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        for (int k = 0; k < 4; k++) {
                            try {
                                MaxScore = Math.max(MaxScore, getScore(i, j, k));
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            result.append(MaxScore).append("\n");
        }

        System.out.println(result);
    }

    private static int getScore(int row, int col, int direction) {
        int ret = 0;
        int curRow = row;
        int curCol = col;

        while (true) {
            int nextRow = curRow + d_row[direction];
            int nextCol = curCol + d_col[direction];

            if (IsOutBound(nextRow, nextCol)) {
                direction += 2;
                direction %= 4;
                ret++;

                curRow = nextRow;
                curCol = nextCol;
                continue;
            }

            if (map[nextRow][nextCol] == -1 || (nextRow == row && nextCol == col)) {
                break;
            }

            if (map[nextRow][nextCol] >= 1 && map[nextRow][nextCol] <= 5) {
                direction = DirectionConvert[map[nextRow][nextCol]][direction];
                ret++;
            }

            if (map[nextRow][nextCol] >= 6 && map[nextRow][nextCol] <= 10) {
                int wormHoleNum = map[nextRow][nextCol];

                if (nextRow == wormHoleInfo[wormHoleNum][0][0] &&
                        nextCol == wormHoleInfo[wormHoleNum][0][1]) {
                    nextRow = wormHoleInfo[wormHoleNum][1][0];
                    nextCol = wormHoleInfo[wormHoleNum][1][1];
                } 
                else {
                    nextRow = wormHoleInfo[wormHoleNum][0][0];
                    nextCol = wormHoleInfo[wormHoleNum][0][1];
                }
            }

            curRow = nextRow;
            curCol = nextCol;
        }

        return ret;
    }

    private static boolean IsOutBound(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}