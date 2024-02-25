import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static final int BIAS = 300;

    static class xy {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof xy)) {
                return false;
            }

            xy o = (xy) obj;

            return this.x == o.x && this.y == o.y;
        }

    }

    static class Cell implements Comparable<Cell> {
        xy pos;
        int ActivateTime;
        int value;

        public Cell(xy pos, int ActivateTime, int value) {
            this.pos = pos;
            this.ActivateTime = ActivateTime;
            this.value = value;
        }

        @Override
        public int compareTo(Cell o) {
            return this.ActivateTime - o.ActivateTime;
        }

    }

    static StringBuilder result = new StringBuilder();
    static StringTokenizer st = null;
    static int T, N, M, K;
    static int[] d_row = { -1, 0, 1, 0 };
    static int[] d_col = { 0, 1, 0, -1 };
    static boolean[][] cellStateBoard;
    static Map<xy, Integer> BreedMap;
    static PriorityQueue<Cell> breedCellpq;
    static PriorityQueue<Integer> liveCellpq;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            result.append("#").append(test_case).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cellStateBoard = new boolean[BIAS * 2 + N][BIAS * 2 + M];
            breedCellpq = new PriorityQueue<>();
            liveCellpq = new PriorityQueue<>();

            // 줄기세포 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int v = Integer.parseInt(st.nextToken());

                    if (v == 0) {
                        continue;
                    }

                    cellStateBoard[BIAS + i][BIAS + j] = true;
                    breedCellpq.add(new Cell(new xy(BIAS + i, BIAS + j), v, v));
                    liveCellpq.add(v + v);
                }
            }

            // 시뮬레이션
            Simulation();

            result.append(liveCellpq.size()).append("\n");
        }

        System.out.println(result);
    }

    private static void Simulation() {
        // K 시간동안 시뮬 실행
        int k;
        BreedMap = new HashMap<>();
        for (k = 1; k <= K; k++) {
            // 번식 맵 탐색하며 번식 정보 commit
            for (Iterator<xy> iter = BreedMap.keySet().iterator(); iter.hasNext();) {
                xy cur = iter.next();
                int value = BreedMap.get(cur);

                cellStateBoard[cur.x][cur.y] = true;
                breedCellpq.add(new Cell(cur, k + value, value));
                liveCellpq.add(k + value + value);
            }

            BreedMap.clear();

            // 현재 시각(k)에서 죽은 줄기세포 확인
            while (!liveCellpq.isEmpty() && liveCellpq.peek() == k) {
                liveCellpq.poll();
            }

            // 현재 시각(k)에서 활성화된 줄기세포에서 번식 수행
            while (!breedCellpq.isEmpty() && breedCellpq.peek().ActivateTime == k) {
                Cell cur = breedCellpq.poll();

                // 4방향 번식 가능성 탐색
                for (int i = 0; i < 4; i++) {
                    int newX = cur.pos.x + d_row[i];
                    int newY = cur.pos.y + d_col[i];

                    // 현재 배양되어 있다면
                    if (cellStateBoard[newX][newY]) {
                        continue;
                    }

                    // 가능하다면 이전에 배양될 경우와 경쟁
                    xy newPos = new xy(newX, newY);
                    if (BreedMap.containsKey(newPos)) {
                        if (BreedMap.get(newPos) < cur.value) {
                            BreedMap.replace(newPos, cur.value);
                        }
                    } else {
                        BreedMap.put(newPos, cur.value);
                    }
                }
            }
        }
    }
}