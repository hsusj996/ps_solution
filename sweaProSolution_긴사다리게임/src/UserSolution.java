import java.util.TreeMap;

public class UserSolution {
    static final int R = 1;
    static final int L = -1;
    static TreeMap<Integer, Integer>[] horizontalMap;

    public void init() {
        horizontalMap = new TreeMap[101];
        for (int i = 1; i <= 100; i++) {
            horizontalMap[i] = new TreeMap<>();
        }
    }

    public void add(int mX, int mY) {
        horizontalMap[mX].put(mY, R);
        horizontalMap[mX + 1].put(mY, L);
    }

    public void remove(int mX, int mY) {
        horizontalMap[mX].remove(mY);
        horizontalMap[mX + 1].remove(mY);
    }

    public int numberOfCross(int mID) {
        int ret = 0;
        int curX = mID;
        int curY = 0;

        while (true) {
            Integer nextY = horizontalMap[curX].higherKey(curY);
            if (nextY == null) {
                return ret;
            }

            int dir = horizontalMap[curX].get(nextY);
            curX = dir == R ? curX + 1 : curX - 1;
            ret++;

            curY = nextY;
        }
    }

    public int participant(int mX, int mY) {
        int curX = mX;
        int curY = mY;

        while (true) {
            Integer nextY = horizontalMap[curX].lowerKey(curY);
            if (nextY == null) {
                return curX;
            }

            int dir = horizontalMap[curX].get(nextY);
            curX = dir == R ? curX + 1 : curX - 1;

            curY = nextY;
        }
    }
}
