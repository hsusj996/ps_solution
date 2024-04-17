package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob13460 {
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };
    static StringTokenizer st = null;
    static int N, M;
    static int[] redPos = new int[2];
    static int[] bluePos = new int[2];
    static int[] holePos = new int[2];
    static char[][] board;
    static int minCnt = 11;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'R') {
                    redPos[0] = i;
                    redPos[1] = j;
                } else if (board[i][j] == 'B') {
                    bluePos[0] = i;
                    bluePos[1] = j;
                } else if (board[i][j] == '0') {
                    holePos[0] = i;
                    holePos[1] = j;
                }
            }
        }

        BackTracking(0);
    }

    private static void BackTracking(int depth) {
        if (depth >= minCnt) {
            return;
        }

        int[] prevRedPos = redPos.clone();
        int[] prevBluePos = bluePos.clone();
        int result = 0;
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    result = Up();
                    break;
                case 1:
                    result = Right();
                    break;
                case 2:
                    result = Down();
                    break;
                case 3:
                    result = Left();
                    break;
                default:
                    break;
            }

            if (result == 1) {
                if (holePos[0] == redPos[0] && holePos[1] == redPos[1]) {
                    minCnt = depth;
                }
                return;
            } else {
                BackTracking(depth + 1);
            }

            redPos = prevRedPos.clone();
            bluePos = prevBluePos.clone();
        }
    }

    private static int Left() {
        if(redPos[1] < bluePos[1]){
            while(board[redPos[0]][redPos[1] - 1] == '.'){
                redPos[1]--;
            }
            while(board[bluePos[0]][bluePos[1] - 1] == '.'){
                bluePos[1]--;
            }
        } else{

        }
    }

    private static int Down() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Down'");
    }

    private static int Right() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Right'");
    }

    private static int Up() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Up'");
    }
}
