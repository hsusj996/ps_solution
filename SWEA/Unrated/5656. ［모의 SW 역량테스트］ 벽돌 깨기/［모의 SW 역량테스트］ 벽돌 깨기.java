import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static StringBuilder result = new StringBuilder();
    static StringTokenizer st = null;
    static int T, N, W, H;
    static int minRemainBlockCnt;
    static int[][] map;
    static int[][] simulationMap;
    static int[] dropMarblePos;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            result.append("#").append(test_case).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            minRemainBlockCnt = Integer.MAX_VALUE;
            map = new int[W][H + 1];
            dropMarblePos = new int[N];

            int[][] tmpMap = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    tmpMap[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 맵 뒤에서부터 리스트에 블록 넣기
            for (int i = 0; i < W; i++) {
                for (int j = 0; j < H; j++) {
                    map[i][j] = tmpMap[H - j - 1][i];
                }
            }

            // 구슬 중복순열
            Permutation(0);

            // 출력
            result.append(minRemainBlockCnt).append("\n");
        }

        System.out.println(result);
    }

    private static void Permutation(int depth) {
        if (depth == N) {
            // 시뮬레이션할 맵 deep copy
            simulationMap = new int[W][H + 1];
            for (int i = 0; i < W; i++) {
                simulationMap[i] = map[i].clone();
            }

            // 시뮬레이션
            Simulation();

            // 남은 블럭 카운트
            int remainBlock = 0;
            for (int i = 0; i < W; i++) {
                for (int j = 0; j <= H; j++) {
                    if (simulationMap[i][j] == 0) {
                        remainBlock += j;
                        break;
                    }
                }
            }

            // 갱신
            minRemainBlockCnt = Math.min(minRemainBlockCnt, remainBlock);

            return;
        }

        for (int i = 0; i < W; i++) {
            dropMarblePos[depth] = i;
            Permutation(depth + 1);
        }
    }

    private static void Simulation() {
        for (int i = 0; i < N; i++) {
            // 구슬 투하
            int posX = dropMarblePos[i];
            int posY = H - 1;
            for (; posY >= 0; posY--) {
                if (simulationMap[posX][posY] != 0) {
                    break;
                }
            }

            if (posY == -1) {
                return;
            }

            // 블록 폭발
            ExplodeBlock(posX, posY);

            // 블록 배열 당기기
            for (int x = 0; x < W; x++) {
                for (int y = 1; y < H; y++) {
                    if (simulationMap[x][y] != 0) {
                        int tmpY = y - 1;

                        if (simulationMap[x][tmpY] != 0) {
                            continue;
                        }

                        while (tmpY >= 0 && simulationMap[x][tmpY] == 0) {
                            tmpY--;
                        }
                        simulationMap[x][tmpY + 1] = simulationMap[x][y];
                        simulationMap[x][y] = 0;
                    }
                }
            }
        }
    }

    private static void ExplodeBlock(int x, int y) {
        int range = simulationMap[x][y];
        simulationMap[x][y] = 0;

        // 가로방향 폭발
        for (int i = x - range + 1; i <= x + range - 1; i++) {
            if (IsOutBound(i, y) || i == x) {
                continue;
            }

            if (simulationMap[i][y] <= 1) {
                simulationMap[i][y] = 0;
                continue;
            }
            ExplodeBlock(i, y);
        }

        // 세로방향 폭발
        for (int i = y - range + 1; i <= y + range - 1; i++) {
            if (IsOutBound(x, i) || i == y) {
                continue;
            }
            if (simulationMap[x][i] <= 1) {
                simulationMap[x][i] = 0;
                continue;
            }
            ExplodeBlock(x, i);
        }

    }

    private static boolean IsOutBound(int x, int y) {
        return x < 0 || x >= W || y < 0 || y >= H;
    }
}