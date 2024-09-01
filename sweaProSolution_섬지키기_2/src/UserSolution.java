import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.awt.Point;

class UserSolution {
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    /*
     * 블록 후보
     * horizontal : 수평 or 수직
     * reverse : 반대 여부
     */
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

    /*
     * numberOfCandidate의 호출 횟수는 최대 150,000이기 때문에
     * update에 속하는 init에서 데이터의 갱신을 맡김
     * 
     * 블록은 한 점을 기준으로 90도씩 돌아갈 수 있고
     * 길이는 1 ~ 5 (단, 1은 예외 처리가 가능하기 때문에 2 ~ 5)
     * 
     * 한 점을 기준으로 우, 하단의 블록을 모두 탐색함
     * 블록은 뒤집힐 수도 있기 때문에 뒤집은 경우도 빠뜨리지 말고 탐색
     * 
     * numberOfCandidate에서 탐색을 하지 않아도 조회 가능케 하기 위해서
     * 해싱을 사용
     * 
     * 각 블록은 고유한 숫자를 가짐. (예를 들어, 1 2 1)
     * 하지만 1 2 1과 3 4 3은 같은 블록 후보로 처리해야함
     * (2 1 2) 와 같은 블록을 두었을 떄 모두 같은 높이가 되기 때문에
     * 두 경우 모두 후보로 사용될 수 있음
     * 
     * 1 2 1, 3 4 3은 절대적 숫자 값은 다르지만 상대적인 변화량을 같음
     * (1, -1)
     * 따라서, 변화량을 기준으로 해싱값을 생성해야함
     * 어차피 블록을 이루는 숫자는 1 ~ 5 사이이기 때문에 약간의 bias만 준다면
     * 0 ~ 9 사이의 한 자리수로 표현가능함
     * 
     * (1, 2, 1)을 해싱값으로 나타내면 64 (bias는 +5)
     * 
     * 반복문의 개수가 상당하기 떄문에 인덱스 설정에 유의해야함
     */
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

    /*
     * 블록의 변화량을 반대로 읽으면
     * 뒤집힌 모양의 블록 정보를 얻을 수 있음
     * 따라서, mStructure는 반대로 읽어야함
     */
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

    /*
     * 1인 경우에는 모든 구역에 들어갈 수 있기 때문에 완탐을 수행
     * 
     * 아닌 경우에는 mStructure의 해싱값과 같은 블록 후보를 찾고
     * 블록 후보에 하나씩 대입해가며 floodfill 알고리즘을 수행
     * max를 갱신
     */
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

    /*
     * floodfill 알고리즘 실행 메서드
     * 높이가 가장 낮은 (0, 0)부터 bfs 시작
     */
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