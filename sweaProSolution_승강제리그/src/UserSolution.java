import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.TreeSet;

class UserSolution {
    static class Player implements Comparable<Player> {
        int id;
        int ability;

        public Player(int id, int ability) {
            this.id = id;
            this.ability = ability;
        }

        @Override
        public int compareTo(UserSolution.Player o) {
            if (this.ability == o.ability) {
                return this.id - o.id;
            }
            return o.ability - this.ability;
        }
    }

    static TreeSet<Player>[][] League;
    static int capacity;
    static int playerCnt, leagueCnt;

    void init(int N, int L, int mAbility[]) {
        playerCnt = N;
        leagueCnt = L;
        League = new TreeSet[L][2];
        for (int i = 0; i < L; i++) {
            League[i][0] = new TreeSet<>();
            League[i][1] = new TreeSet<>();
        }

        capacity = N / L;
        int idx = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < capacity; j++) {
                if (League[i][0].size() == League[i][1].size()) {
                    League[i][1].add(new Player(idx, mAbility[idx]));
                } else {
                    League[i][0].add(new Player(idx, mAbility[idx]));
                }

                if (League[i][0].size() != 0 && League[i][1].size() != 0) {
                    if (League[i][0].last().compareTo(League[i][1].first()) > 0) {
                        Player p1 = League[i][0].last();
                        Player p2 = League[i][1].first();

                        League[i][0].remove(p1);
                        League[i][1].remove(p2);

                        League[i][0].add(p2);
                        League[i][1].add(p1);
                    }
                }
                idx++;
            }
        }

        return;
    }

    int move() {
        int ret = 0;
        // swap할 선수 뽑기
        for (int i = 0; i < leagueCnt - 1; i++) {
            Player p1 = League[i][1].last();
            Player p2 = League[i + 1][0].first();
            ret += (p1.id + p2.id);

            League[i][1].remove(p1);
            League[i + 1][0].remove(p2);

            League[i][1].add(p2);
            League[i + 1][0].add(p1);
        }

        for (int i = 0; i < leagueCnt; i++) {
            while (League[i][0].last().compareTo(League[i][1].first()) > 0) {
                Player p1 = League[i][0].last();
                Player p2 = League[i][1].first();

                League[i][0].remove(p1);
                League[i][1].remove(p2);

                League[i][0].add(p2);
                League[i][1].add(p1);
            }
        }

        return ret;
    }

    int trade() {
        int ret = 0;
        // swap할 선수 뽑기
        for (int i = 0; i < leagueCnt - 1; i++) {
            Player p1 = League[i][1].first();
            Player p2 = League[i + 1][0].first();
            ret += (p1.id + p2.id);

            League[i][1].remove(p1);
            League[i + 1][0].remove(p2);

            League[i][1].add(p2);
            League[i + 1][0].add(p1);
        }

        for (int i = 0; i < leagueCnt; i++) {
            while (League[i][0].last().compareTo(League[i][1].first()) > 0) {
                Player p1 = League[i][0].last();
                Player p2 = League[i][1].first();

                League[i][0].remove(p1);
                League[i][1].remove(p2);

                League[i][0].add(p2);
                League[i][1].add(p1);
            }
        }

        return ret;
    }

    // private static Player SearchMedianPlayer(int idx) {
    // int tmpMidAbil = (League[idx].first().ability + League[idx].last().ability) /
    // 2;
    // int tmpMidId = (League[idx].first().id + League[idx].last().id) / 2;

    // Player tmpMidPlayer = new Player(tmpMidId, tmpMidAbil);
    // NavigableSet<Player> lowerNVSet = League[idx].tailSet(tmpMidPlayer, false);
    // NavigableSet<Player> upperNVSet = League[idx].headSet(tmpMidPlayer, true);

    // int sizeDiff = lowerNVSet.size() - upperNVSet.size();
    // if (sizeDiff > 0) {
    // Iterator<Player> iter = lowerNVSet.iterator();
    // for (int i = 0; i < sizeDiff / 2; i++) {
    // iter.next();
    // }
    // return iter.next();
    // } else {
    // Iterator<Player> iter = upperNVSet.descendingIterator();
    // for (int i = 0; i < Math.abs(sizeDiff) / 2; i++) {
    // iter.next();
    // }
    // return iter.next();
    // }
    // }

    // public void PrintForDebug() {
    // StringBuilder sb = new StringBuilder();
    // for (int i = 0; i < leagueCnt; i++) {
    // sb.append(" [ ");
    // for (Iterator<Player> iter = League[i][0].iterator(); iter.hasNext();) {
    // sb.append(iter.next().id).append(" ");
    // }
    // sb.append(" / ");
    // for (Iterator<Player> iter = League[i][1].iterator(); iter.hasNext();) {
    // sb.append(iter.next().id).append(" ");
    // }
    // sb.append(" ] ");
    // }

    // sb.append("\n");

    // for (int i = 0; i < leagueCnt; i++) {
    // sb.append(" [ ");
    // for (Iterator<Player> iter = League[i][0].iterator(); iter.hasNext();) {
    // sb.append(iter.next().ability).append(" ");
    // }
    // sb.append(" / ");
    // for (Iterator<Player> iter = League[i][1].iterator(); iter.hasNext();) {
    // sb.append(iter.next().ability).append(" ");
    // }
    // sb.append(" ] ");
    // }
    // System.out.println(sb.toString() + "\n");
    // }
}