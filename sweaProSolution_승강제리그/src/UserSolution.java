import java.util.ArrayDeque;
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

    static TreeSet<Player>[] League;
    static Queue<Player>[] commitQ;
    static int capacity;
    static int playerCnt, leagueCnt;

    void init(int N, int L, int mAbility[]) {
        playerCnt = N;
        leagueCnt = L;
        League = new TreeSet[L];
        commitQ = new Queue[L];
        for (int i = 0; i < L; i++) {
            League[i] = new TreeSet<>();
            commitQ[i] = new ArrayDeque<>();
        }

        capacity = N / L;
        int idx = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < capacity; j++) {
                League[i].add(new Player(idx, mAbility[idx]));
                idx++;
            }
        }

        return;
    }

    int move() {
        int ret = 0;
        // swap할 선수 뽑기
        for (int i = 0; i < leagueCnt - 1; i++) {
            Player p1 = League[i].last();
            Player p2 = League[i + 1].first();
            ret += (p1.id + p2.id);
            commitQ[i].add(p2);
            commitQ[i + 1].add(p1);

            League[i].remove(p1);
            League[i + 1].remove(p2);
        }

        // commit
        for (int i = 0; i < leagueCnt; i++) {
            while (!commitQ[i].isEmpty()) {
                League[i].add(commitQ[i].poll());
            }
        }

        return ret;
    }

    int trade() {
        int ret = 0;
        // swap할 선수 뽑기
        for (int i = 0; i < leagueCnt - 1; i++) {
            Player p1 = SearchMedianPlayer(i);
            Player p2 = League[i + 1].first();
            ret += (p1.id + p2.id);
            commitQ[i].add(p2);
            commitQ[i + 1].add(p1);

            League[i].remove(p1);
            League[i + 1].remove(p2);
        }

        // commit
        for (int i = 0; i < leagueCnt; i++) {
            while (!commitQ[i].isEmpty()) {
                League[i].add(commitQ[i].poll());
            }
        }

        return ret;
    }

    private static Player SearchMedianPlayer(int idx) {
        int tmpMidAbil = (League[idx].first().ability + League[idx].last().ability) / 2;
        int tmpMidId = (League[idx].first().id + League[idx].last().id) / 2;

        Player tmpMidPlayer = new Player(tmpMidId, tmpMidAbil);
        NavigableSet<Player> lowerNVSet = League[idx].tailSet(tmpMidPlayer, false);
        NavigableSet<Player> upperNVSet = League[idx].headSet(tmpMidPlayer, true);

        int sizeDiff = lowerNVSet.size() - upperNVSet.size();
        if (sizeDiff > 0) {
            Iterator<Player> iter = lowerNVSet.iterator();
            for (int i = 0; i < sizeDiff / 2; i++) {
                iter.next();
            }
            return iter.next();
        } else {
            Iterator<Player> iter = upperNVSet.descendingIterator();
            for (int i = 0; i < Math.abs(sizeDiff) / 2; i++) {
                iter.next();
            }
            return iter.next();
        }
    }

    public void PrintForDebug(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<leagueCnt;i++){
            for(Iterator<Player> iter = League[i].iterator();iter.hasNext();){
                sb.append(iter.next().id).append(" ");
            }
            sb.append(" / ");
        }

        sb.append("\n");

        for(int i=0;i<leagueCnt;i++){
            for(Iterator<Player> iter = League[i].iterator();iter.hasNext();){
                sb.append(iter.next().ability).append(" ");
            }
            sb.append(" / ");
        }

        System.out.println(sb.toString() + "\n");
    }
}