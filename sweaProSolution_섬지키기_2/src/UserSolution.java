import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.awt.Point;

class UserSolution {
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    static class Candidate {
        int x;
        int y;
        boolean horizontal;
        boolean reverse;

        public Candidate(int x, int y, boolean horizontal, boolean reverse) {
            this.x = x;
            this.y = y;
            this.horizontal = horizontal;
            this.reverse = reverse;
        }

    }

    static List<Candidate>[] candidates;
    static int[][] newBoard;
    static int[][] initBoard;
    static int n;

    public void init(int N, int mMap[][]) {
        n = N;
        candidates = new ArrayList[10001];
        for (int i = 0; i <= 10000; i++) {
            candidates[i] = new ArrayList<>();
        }
        newBoard = new int[n + 2][n + 2];
        initBoard = new int[n + 2][n + 2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i + 1][j + 1] = initBoard[i + 1][j + 1] = mMap[i][j];
            }
        }

        for (int len = 2; len <= 5; len++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j + len - 1 <= n; j++) {
                    int hash = 0;
                    for (int k = 0; k + 1 < len; k++) {
                        hash = hash * 10 + initBoard[i][j + k + 1] - initBoard[i][j + k] + 5;
                    }
                    candidates[hash].add(new Candidate(i, j, true, false));

                    int reverseHash = 0;
                    for (int k = len - 1; k - 1 >= 0; k--) {
                        reverseHash = reverseHash * 10 + initBoard[i][j + k - 1] - initBoard[i][j + k] + 5;
                    }

                    if (reverseHash != hash) {
                        candidates[reverseHash].add(new Candidate(i, j, true, true));
                    }
                }
            }

            for (int i = 1; i + len - 1 <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int hash = 0;
                    for (int k = 0; k + 1 < len; k++) {
                        hash = hash * 10 + initBoard[i + k + 1][j] - initBoard[i + k][j] + 5;
                    }
                    candidates[hash].add(new Candidate(i, j, false, false));

                    int reverseHash = 0;
                    for (int k = len - 1; k - 1 >= 0; k--) {
                        reverseHash = reverseHash * 10 + initBoard[i + k - 1][j] - initBoard[i + k][j] + 5;
                    }
                    if (reverseHash != hash) {
                        candidates[reverseHash].add(new Candidate(i, j, false, true));
                    }
                }
            }
        }

    }

    public int numberOfCandidate(int M, int mStructure[]) {
        if (M == 1) {
            return n * n;
        }
        int hash = 0;
        for (int i = 0; i + 1 < M; i++) {
            hash = hash * 10 + mStructure[i] - mStructure[i + 1] + 5;
        }

        return candidates[hash].size();
    }

    public int maxArea(int M, int mStructure[], int mSeaLevel) {
        int ret = -1;
        if (M == 1) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    newBoard[i][j] = initBoard[i][j] + mStructure[0];
                    ret = Math.max(ret, remainArea(newBoard, mSeaLevel));
                    newBoard[i][j] = initBoard[i][j];
                }
            }

            return ret;
        }

        int hash = 0;
        for (int i = 0; i + 1 < M; i++) {
            hash = hash * 10 + mStructure[i] - mStructure[i + 1] + 5;
        }

        for (Candidate candidate : candidates[hash]) {
            if (candidate.horizontal) {
                int height = mStructure[0] + (candidate.reverse ? initBoard[candidate.x][candidate.y + M - 1]
                        : initBoard[candidate.x][candidate.y]);
                for (int i = 0; i < M; i++) {
                    newBoard[candidate.x][candidate.y + i] = height;
                }
                ret = Math.max(ret, remainArea(newBoard, mSeaLevel));
                for (int i = 0; i < M; i++) {
                    newBoard[candidate.x][candidate.y + i] = initBoard[candidate.x][candidate.y + i];
                }
            } else {
                int height = mStructure[0] + (candidate.reverse ? initBoard[candidate.x + M - 1][candidate.y]
                        : initBoard[candidate.x][candidate.y]);
                for (int i = 0; i < M; i++) {
                    newBoard[candidate.x + i][candidate.y] = height;
                }
                ret = Math.max(ret, remainArea(newBoard, mSeaLevel));
                for (int i = 0; i < M; i++) {
                    newBoard[candidate.x + i][candidate.y] = initBoard[candidate.x + i][candidate.y];
                }
            }
        }

        return ret;
    }

    private int remainArea(int[][] board, int mSeaLevel) {
        int ret = 0;
        boolean[][] visited = new boolean[n + 2][n + 2];
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + d[0][i];
                int ny = cur.y + d[1][i];

                if (IsOutBound(nx, ny) || visited[nx][ny] || board[nx][ny] >= mSeaLevel) {
                    continue;
                }

                q.add(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!visited[i][j]) {
                    ret++;
                }
            }
        }

        return ret;
    }

    private boolean IsOutBound(int nx, int ny) {
        return nx < 0 || nx >= n + 2 || ny < 0 || ny >= n + 2;
    }
}