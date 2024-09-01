import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class UserSolution {
    static final int[] blockCnt = { 1, 2, 2, 3, 4 };
    static final int[][][] d = {
            {
                    { 0 },
                    { 1 }
            },
            {
                    { 0, 0 },
                    { 1, 1 }
            },
            {
                    { 1, 1 },
                    { 0, 0 },
            },
            {
                    { 0, 1, 0 },
                    { 1, 0, 1 },
            },
            {
                    { 1, 0, 0, 1 },
                    { 0, 1, 1, 0 }
            }
    };
    static int[][] board;
    static List<Hubo>[] huboList;
    static boolean[][] visited;
    static int n, m;

    public void init(int mRows, int mCols, int mCells[][]) {
        n = mRows;
        m = mCols;
        board = new int[n][m];
        huboList = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            huboList[i] = new ArrayList<>();
        }
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = mCells[i][j];
            }
        }

        setHuboList();  // 최초에 init에서 모두 가능한 경우를 저장함
        for (int i = 0; i < 5; i++) {
            Collections.sort(huboList[i]);
        }
    }

    public Solution.Result putPuzzle(int mPuzzle[][]) {
        Solution.Result ret = new Solution.Result(-1, -1);

        Hubo h = checkPuzzleType(mPuzzle);
        for (Hubo c : huboList[h.type]) {
            if (IsPossiblePos(c, h)) {
                setVisited(c);
                ret.row = c.x;
                ret.col = c.y;
                break;
            }
        }

        // 디버깅용 출력
        // PrintForDebug();

        return ret;
    }
    
    // 그냥 초기화
    public void clearPuzzles() {
        visited = new boolean[n][m];
    }

    // 최초 세팅 메서드
    private void setHuboList() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 5; k++) {
                    checkType(i, j, k); // 해당 위치에서 가능한 블럭 모양을 확인함
                }
            }
        }
    }

    // x,y 기준으로 5가지 타입을 모두 확인함
    private void checkType(int x, int y, int type) {
        int hash = 0;
        int cx = x;
        int cy = y;
        for (int i = 0; i < blockCnt[type]; i++) {
            int nx = cx + d[type][0][i];
            int ny = cy + d[type][1][i];

            if (IsOutBound(nx, ny)) {   // 밖인 경우 모양을 만들 수 없음
                return;
            }

            int diff = board[nx][ny] - board[cx][cy] + 5;   // 해싱 함수

            hash += (Math.pow(10, i) * (diff)); // 해시값 더하기
            cx = nx;
            cy = ny;
        }

        Hubo h = new Hubo(x, y, type, hash);
        huboList[type].add(h);  // 삽입
    }

    private boolean IsOutBound(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }


    // private void PrintForDebug() {
    //     for (int i = 0; i < n; i++) {
    //         for (int j = 0; j < m; j++) {
    //             System.out.print(visited[i][j] ? 1 : 0);
    //         }
    //         System.out.println();
    //     }
    // }

    // put이 가능한 블록인 경우 visited 배열을 갱신함
    private void setVisited(UserSolution.Hubo c) {
        int cx = c.x;
        int cy = c.y;
        visited[cx][cy] = true;

        for (int i = 0; i < blockCnt[c.type]; i++) {
            cx += d[c.type][0][i];
            cy += d[c.type][1][i];

            visited[cx][cy] = true;
        }
    }

    // 해당 위치가 가능한지 확인함 (해시값이 같은지, 이전에 put된 블럭이 없는지)
    private boolean IsPossiblePos(UserSolution.Hubo cur, UserSolution.Hubo target) {
        // hash 비교
        if (cur.hash != target.hash) {
            return false;
        }

        // visited 비교
        int cx = cur.x;
        int cy = cur.y;

        if (visited[cx][cy]) {
            return false;
        }

        for (int i = 0; i < blockCnt[cur.type]; i++) {
            cx += d[cur.type][0][i];
            cy += d[cur.type][1][i];

            if (visited[cx][cy]) {
                return false;
            }
        }

        return true;
    }

    // 입력받은 퍼즐이 어떻게 생긴지 판단 (하드코딩 분기처리함)
    private UserSolution.Hubo checkPuzzleType(int[][] mPuzzle) {
        int type = -1;
        if (mPuzzle[0][1] > 0) {
            if (mPuzzle[0][2] > 0) {
                type = 1;
            } else if (mPuzzle[1][1] > 0) {
                type = 3;
            } else {
                type = 0;
            }
        } else if (mPuzzle[1][0] > 0) {
            if (mPuzzle[2][0] > 0) {
                type = 2;
            } else {
                type = 4;
            }
        }

        int hash = 0;
        int cx = 0;
        int cy = 0;

        for (int i = 0; i < blockCnt[type]; i++) {
            int nx = cx + d[type][0][i];
            int ny = cy + d[type][1][i];

            int diff = mPuzzle[nx][ny] - mPuzzle[cx][cy] + 5;
            hash += Math.pow(10, i) * diff;

            cx = nx;
            cy = ny;
        }

        return new Hubo(0, 0, type, hash);
    }

    static class Hubo implements Comparable<Hubo> {
        int x;
        int y;
        int type;
        int hash;

        public Hubo(int x, int y, int type, int hash) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.hash = hash;
        }

        @Override
        public int compareTo(Hubo o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }

    }
}