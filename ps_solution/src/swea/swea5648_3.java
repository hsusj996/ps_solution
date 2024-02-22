package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class swea5648_3 {
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
      xy o = (xy) obj;

      if (o.x == this.x && o.y == this.y) {
        return true;
      }
      return false;
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

  static int[] d_x = { 1, -1, 0, 0 };
  static int[] d_y = { 0, 0, -1, 1 };
  static int T, N;
  static StringBuilder result = new StringBuilder();
  static Atom[] atomArr;
  static int[][] board;
  static Map<xy, List<Integer>> collisionMap;
  static int energySum;
  static int AtomCnt;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");

      N = Integer.parseInt(br.readLine());

      atomArr = new Atom[N + 1];
      board = new int[4001][4001];
      energySum = 0;
      AtomCnt = N;
      int cnt = 4000;

      for (int i = 1; i <= N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        int energy = Integer.parseInt(st.nextToken());

        atomArr[i] = new Atom(x * 2 + 2000, y * 2 + 2000, direction, energy);
        board[x * 2 + 2000][y * 2 + 2000] = i;
      }

      while (cnt-- > 0) {
        if (AtomCnt <= 1) {
          break;
        }
        collisionMap = new HashMap<>();

        MoveAtoms(); // 움직이기

        // 충돌
        Iterator<xy> keys = collisionMap.keySet().iterator();
        while (keys.hasNext()) {
          xy cur = keys.next();

          for (int atomNum : collisionMap.get(cur)) {
            energySum += atomArr[atomNum].energy;
            atomArr[atomNum].existed = false;
            board[atomArr[atomNum].x][atomArr[atomNum].y] = 0;
            AtomCnt--;
          }
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

      board[atomArr[i].x][atomArr[i].y] = 0;

      atomArr[i].x += d_x[atomArr[i].direction];
      atomArr[i].y += d_y[atomArr[i].direction];

      if (IsOutBound(atomArr[i].x, atomArr[i].y)) {
        atomArr[i].existed = false;
        AtomCnt--;
        continue;
      }

      if (board[atomArr[i].x][atomArr[i].y] == 0) { // 0이라면 그냥 삽입
        board[atomArr[i].x][atomArr[i].y] = i;
      } else { // 0이 아니라면 충돌가능
        if (collisionMap.containsKey(new xy(atomArr[i].x, atomArr[i].y))) { // 기존에 충돌 리스트가 있으면 삽입
          collisionMap.get(new xy(atomArr[i].x, atomArr[i].y)).add(i);
        } else { // 없으면 새로 원소 삽입
          List<Integer> tmp = new ArrayList<>();
          tmp.add(board[atomArr[i].x][atomArr[i].y]);
          tmp.add(i);
          collisionMap.put(new xy(atomArr[i].x, atomArr[i].y), tmp);
        }
      }
    }
  }

  private static boolean IsOutBound(int x, int y) {
    return x < 0 || x > 4000 || y < 0 || y > 4000;
  }
}
