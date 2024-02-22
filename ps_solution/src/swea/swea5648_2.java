package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea5648_2 {
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

  static int 
  static int[] d_x = { 0, 0, -1, 1 };
  static int[] d_y = { 1, -1, 0, 0 };
  static int T, N;
  static StringBuilder result = new StringBuilder();
  static Atom[] atomArr;
  static int energySum;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");
      N = Integer.parseInt(br.readLine());

      energySum = 0;
      boolean explodeFlag = false;
      atomArr = new Atom[N];

      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        int energy = Integer.parseInt(st.nextToken());

        atomArr[i] = new Atom(x * 2, y * 2, direction, energy);
      }

      int cnt = 4000;
      while (cnt-- > 0) {
        MoveAtoms();

        Explode();
      }

      result.append(energySum).append("\n");
    }

    System.out.println(result);
  }

  private static boolean WillExplode() {
    for (int i = 0; i < N; i++) {
      int nextX1 = atomArr[i].x + d_x[atomArr[i].direction];
      int nextY1 = atomArr[i].y + d_y[atomArr[i].direction];
      for (int j = i + 1; j < N; j++) {
        int nextY2 = atomArr[j].y + d_y[atomArr[j].direction];
        int nextX2 = atomArr[j].x + d_x[atomArr[j].direction];

        int distance1 = Math.abs(atomArr[i].x - atomArr[j].x) + Math.abs(atomArr[i].y - atomArr[j].y);
        int distance2 = Math.abs(nextX1 - nextX2) + Math.abs(nextY1 - nextY2);

        if (distance2 < distance1) {
          return true;
        }
      }
    }

    return false;
  }

  private static void Explode() {
    for (int i = 0; i < N; i++) {
      if (!atomArr[i].existed) {
        continue;
      }

      Queue<Atom> explodeQ = new LinkedList<>();
      for (int j = i + 1; j < N; j++) {
        if (!atomArr[i].existed) {
          continue;
        }

        if (atomArr[i].x == atomArr[j].x && atomArr[i].y == atomArr[j].y) {
          explodeQ.add(atomArr[j]);
        }
      }

      if (explodeQ.isEmpty()) {
        continue;
      }

      atomArr[i].existed = false;
      energySum += atomArr[i].energy;

      while (!explodeQ.isEmpty()) {
        Atom cur = explodeQ.poll();
        cur.existed = false;
        energySum += cur.energy;
      }
    }
  }

  private static void MoveAtoms() {
    for (int i = 0; i < N; i++) {
      if (!atomArr[i].existed) {
        continue;
      }

      atomArr[i].x += d_x[atomArr[i].direction];
      atomArr[i].y += d_y[atomArr[i].direction];
    }
  }
}
