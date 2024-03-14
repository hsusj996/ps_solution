import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class UserSolution {
    static class Monarch {
        String name;
        Monarch parent;
        Monarch next;
        int soldiers;
        boolean alive;
        int[] pos;
        List<Monarch> enemyList;

        public Monarch(String name, int soldiers, boolean alive, int x, int y) {
            this.name = name;
            this.soldiers = soldiers;
            this.alive = alive;
            this.pos = new int[] { x, y };
            this.enemyList = new ArrayList<>();
            this.parent = this;
            this.next = null;
        }
    }

    static final int[][] d = { { -1, -1, -1, 0, 1, 1, 1, 0 }, { -1, 0, 1, 1, 1, 0, -1, -1 } };
    static Monarch[][] board;
    static HashMap<String, Monarch> monarchMap;
    static int n;

    void init(int N, int mSoldier[][], char mMonarch[][][]) {
        n = N;
        board = new Monarch[N][N];
        monarchMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new Monarch(String.valueOf(mMonarch[i][j]), mSoldier[i][j], true, i, j);
                monarchMap.put(String.valueOf(mMonarch[i][j]), board[i][j]);
            }
        }
        // PrintForDebug();
    }

    void destroy() {

    }

    int ally(char mMonarchA[], char mMonarchB[]) {
        Monarch m1 = monarchMap.get(String.valueOf(mMonarchA));
        Monarch m2 = monarchMap.get(String.valueOf(mMonarchB));

        // 이미 동맹인지 확인
        Monarch m1Root = FindSet(m1);
        Monarch m2Root = FindSet(m2);

        if (m1Root.equals(m2Root)) {
            return -1;
        }

        // 적대관계가 있는지 확인
        for (Monarch cur : m1Root.enemyList) {
            Monarch enemyRoot = FindSet(cur);

            if (enemyRoot.equals(m2Root)) {
                return -2;
            }
        }

        // 아닌 경우 동맹
        Monarch m1EndNode = m1;
        while (m1EndNode.next != null) {
            m1EndNode = m1EndNode.next;
        }

        m1EndNode.next = m2Root;
        m2Root.parent = m1Root;
        m1Root.enemyList.addAll(m2Root.enemyList);

        return 1;
    }

    int attack(char mMonarchA[], char mMonarchB[], char mGeneral[]) {
        Monarch m1 = monarchMap.get(String.valueOf(mMonarchA));
        Monarch m2 = monarchMap.get(String.valueOf(mMonarchB));

        // 이미 동맹인지 확인
        Monarch m1Root = FindSet(m1);
        Monarch m2Root = FindSet(m2);

        if (m1Root.equals(m2Root)) {
            return -1;
        }

        // 동맹과 인접하는지 확인 (확인하면서 병사 모으기, 0명을 모을수도 있기 때문에 플래그 설정)
        int attackSoldiers = 0;
        boolean fightFlag = false;
        for (int idx = 0; idx < 8; idx++) {
            int newX = m2.pos[0] + d[0][idx];
            int newY = m2.pos[1] + d[1][idx];

            if (newX < 0 || newX >= n || newY < 0 || newY >= n) {
                continue;
            }

            if (m1Root == FindSet(board[newX][newY])) {
                fightFlag = true;
                int tmp = board[newX][newY].soldiers / 2;
                attackSoldiers += tmp;
                board[newX][newY].soldiers -= tmp;
            }
        }

        if (!fightFlag) {
            return -2;
        }

        // 전투 시작

        // 적대 관계 설정
        boolean flag1 = false;
        for (Monarch cur : m1Root.enemyList) {
            if (FindSet(cur) == m2Root) {
                flag1 = true;
                break;
            }
        }
        if (!flag1) {
            m1Root.enemyList.add(m2Root);
            m2Root.enemyList.add(m1Root);
        }

        // 방어측 군사 보내기
        for (int idx = 0; idx < 8; idx++) {
            int newX = m2.pos[0] + d[0][idx];
            int newY = m2.pos[1] + d[1][idx];

            if (newX < 0 || newX >= n || newY < 0 || newY >= n) {
                continue;
            }

            if (m2Root == FindSet(board[newX][newY])) {
                fightFlag = true;
                int defenseSoldiers = board[newX][newY].soldiers / 2;
                m2.soldiers += defenseSoldiers;
                board[newX][newY].soldiers -= defenseSoldiers;
            }
        }

        // 싸움 결과
        m2.soldiers -= attackSoldiers;
        if (m2.soldiers < 0) { // 공격 승
            m2.alive = false;
            Monarch m = new Monarch(String.valueOf(mGeneral), Math.abs(m2.soldiers), true, m2.pos[0], m2.pos[1]);
            board[m2.pos[0]][m2.pos[1]] = m;
            monarchMap.put(String.valueOf(mGeneral), m);

            // m1과 동맹
            m.next = m1Root;
            m1Root.parent = m;
            m.enemyList.addAll(m1Root.enemyList);

            return 1;
        } else {
            return 0;
        }
    }

    int recruit(char mMonarch[], int mNum, int mOption) {
        Monarch m = monarchMap.get(String.valueOf(mMonarch));

        if (mOption == 0) {
            return m.soldiers += mNum;
        } else if (mOption == 1) {
            int sum = 0;
            for (Monarch tmp = FindSet(m); tmp != null; tmp = tmp.next) {
                if (!tmp.alive)
                    continue;
                sum += (tmp.soldiers += mNum);
            }
            return sum;
        } else {
            return -1;
        }
    }

    Monarch FindSet(Monarch m) {
        if (m.parent.equals(m)) {
            return m;
        }

        return m.parent = FindSet(m.parent);
    }

    // void PrintForDebug() {
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < n; j++) {
    // System.out.print(board[i][j].name + "/" + board[i][j].soldiers + " ");
    // }
    // System.out.println();
    // }
    // System.out.println();
    // }
}