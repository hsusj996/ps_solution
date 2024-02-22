import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
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

  static class Atom {
    int x;
    int y;
    int direction;
    int energy;
    boolean existed;

    public Atom(int x, int y, int direction, int energy) {
      this.x = x;
      this.y = y;
      this.direction = direction;
      this.energy = energy;
      this.existed = true;
    }
  }

  static int[] d_x = { 0, 0, -1, 1 };
  static int[] d_y = { 1, -1, 0, 0 };
  static int T, N;
  static StringBuilder result = new StringBuilder();
  static Atom[] atomArr;
  static Map<xy, Integer> collisionMap;
  static Set<Integer> collisionSet;
  static int energySum;
  static int AtomCnt;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");

      N = Integer.parseInt(br.readLine());

      atomArr = new Atom[N + 1];
      energySum = 0;
      AtomCnt = N;

      for (int i = 1; i <= N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        int energy = Integer.parseInt(st.nextToken());

        atomArr[i] = new Atom(2 * (1000 + x), 2 * (1000 + y), direction, energy);
      }

      while (true) {
        if (AtomCnt <= 1) {
          break;
        }
        collisionMap = new HashMap<>();
        collisionSet = new HashSet<>();

        MoveAtoms(); // 움직이기

        // 충돌
        for (Iterator<Integer> iter = collisionSet.iterator(); iter.hasNext();) {
          int atomNum = iter.next();
          energySum += atomArr[atomNum].energy;
          atomArr[atomNum].existed = false;
          AtomCnt--;
        }
      }

      result.append(energySum).append("\n");
    }

    System.out.println(result);
  }

  private static void MoveAtoms() {
    for (int i = 1; i <= N; i++) {
      if (!atomArr[i].existed) {
        continue;
      }

      atomArr[i].x += d_x[atomArr[i].direction];
      atomArr[i].y += d_y[atomArr[i].direction];

      if (IsOutBound(atomArr[i].x, atomArr[i].y)) {
        atomArr[i].existed = false;
        AtomCnt--;
        continue;
      }

      xy cdnt = new xy(atomArr[i].x, atomArr[i].y);

      if (collisionMap.containsKey(cdnt)) {
        collisionSet.add(i);
        collisionSet.add(collisionMap.get(cdnt));
      } else {
        collisionMap.put(cdnt, i);
      }
    }
  }

  private static boolean IsOutBound(int x, int y) {
    return x < 0 || x > 4000 || y < 0 || y > 4000;
  }
}