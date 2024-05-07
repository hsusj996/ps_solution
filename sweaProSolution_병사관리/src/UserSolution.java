import java.util.HashMap;
import java.util.LinkedList;

class UserSolution {
    static class SoldierInfo {
        int team;
        int score;

        public SoldierInfo(int team, int score) {
            this.team = team;
            this.score = score;
        }

    }

    static LinkedList<Integer>[][] soldierList;
    static HashMap<Integer, SoldierInfo> soldierMap;

    public void init() {
        soldierList = new LinkedList[6][6];
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                soldierList[i][j] = new LinkedList<>();
            }
        }

        soldierMap = new HashMap<>();
    }

    public void hire(int mID, int mTeam, int mScore) {
        SoldierInfo soldier = new SoldierInfo(mTeam, mScore);

        soldierList[mTeam][mScore].add(mID);
        soldierMap.put(mID, soldier);
    }

    public void fire(int mID) {
        SoldierInfo soldier = soldierMap.get(mID);

        soldierList[soldier.team][soldier.score].remove(Integer.valueOf(mID));
    }

    public void updateSoldier(int mID, int mScore) {
        SoldierInfo soldier = soldierMap.get(mID);

        soldierList[soldier.team][soldier.score].remove(Integer.valueOf(mID));
        soldierList[soldier.team][mScore].add(mID);

        soldierMap.replace(mID, new SoldierInfo(soldier.team, mScore));
    }

    public void updateTeam(int mTeam, int mChangeScore) {
        LinkedList<Integer>[] tmpList = new LinkedList[6];
        for (int i = 1; i <= 5; i++) {
            tmpList[i] = new LinkedList<>();
        }
        for (int i = 1; i <= 5; i++) {
            int sum = i + mChangeScore;

            if (sum > 5) {
                sum = 5;
            } else if (sum < 1) {
                sum = 1;
            }

            tmpList[sum] = soldierList[mTeam][i];
        }

        soldierList[mTeam] = tmpList;
    }

    public int bestSoldier(int mTeam) {
        int max = -1;
        for (int i = 5; i > 0; i--) {
            if (soldierList[mTeam][i].size() != 0) {
                for (int id : soldierList[mTeam][i]) {
                    if (id > max) {
                        max = id;
                    }
                }
                break;
            }
        }

        return max;
    }
}