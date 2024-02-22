package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class swea5648 {
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

  static class Collision {
    int Atom1;
    int Atom2;
    int collisionTime;

    public Collision(int Atom1, int Atom2, int collisionTime) {
      this.Atom1 = Atom1;
      this.Atom2 = Atom2;
      this.collisionTime = collisionTime;
    }
  }

  static int[] d_x = { 0, 1, 0, -1 };
  static int[] d_y = { 1, 0, -1, 0 };
  static int T, N;
  static StringBuilder result = new StringBuilder();
  static Atom[] atomArr;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");
      N = Integer.parseInt(br.readLine());
      int energySum = 0;
      atomArr = new Atom[N];

      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        switch (direction) {
          case 0:
            direction = 0;
            break;
          case 1:
            direction = 2;
            break;
          case 2:
            direction = 3;
            break;
          case 3:
            direction = 1;
            break;
          default:
            break;
        }
        int energy = Integer.parseInt(st.nextToken());

        atomArr[i] = new Atom(x * 2, y * 2, direction, energy);
      }

      PriorityQueue<Collision> collsionQ = CheckCollision();

      while (!collsionQ.isEmpty()) {
        List<Collision> collisionList = new ArrayList<>();
        Collision cur = collsionQ.poll();
        collisionList.add(cur);
        while (!collsionQ.isEmpty() && collsionQ.peek().collisionTime == cur.collisionTime) {
          collisionList.add(collsionQ.poll());
        }

        Set<Integer> CollisionSet = new HashSet<>();
        for (Collision c : collisionList) {
          if (!atomArr[c.Atom1].existed || !atomArr[c.Atom2].existed) {
            continue;
          }

          CollisionSet.add(c.Atom1);
          CollisionSet.add(c.Atom2);
        }

        for (int c : CollisionSet) {
          atomArr[c].existed = false;
          energySum += atomArr[c].energy;
        }
      }

      result.append(energySum).append("\n");
    }

    System.out.println(result);
  }

  private static PriorityQueue<Collision> CheckCollision() {
    PriorityQueue<Collision> q = new PriorityQueue<>(new Comparator<Collision>() {

      @Override
      public int compare(Collision o1, Collision o2) {
        return o1.collisionTime - o2.collisionTime;
      }

    });

    for (int i = 0; i < N; i++) {
      if (!atomArr[i].existed) {
        continue;
      }

      for (int j = i + 1; j < N; j++) {
        int time = -1;
        if (!atomArr[i].existed) {
          continue;
        }

        // 대각선 비교
        if (Math.abs(atomArr[i].x - atomArr[j].x) == Math.abs(atomArr[i].y - atomArr[j].y)) {
          int nextX1 = atomArr[i].x + d_x[atomArr[i].direction];
          int nextY1 = atomArr[i].y + d_y[atomArr[i].direction];
          int nextX2 = atomArr[j].x + d_x[atomArr[j].direction];
          int nextY2 = atomArr[j].y + d_y[atomArr[j].direction];

          if (Math.abs(atomArr[i].x - atomArr[j].x) + Math.abs(atomArr[i].y - atomArr[j].y) > Math.abs(nextX1 - nextX2)
              + Math.abs(nextY1 - nextY2)) {
            if ((atomArr[i].direction + 1) % 4 == atomArr[j].direction
                || (atomArr[i].direction + 3) % 4 == atomArr[j].direction) {
              time = Math.abs(atomArr[i].x - atomArr[j].x);
            }
          }
        }

        // 직선 비교
        if (atomArr[i].x == atomArr[j].x || atomArr[i].y == atomArr[j].y) {
          int nextX1 = atomArr[i].x + d_x[atomArr[i].direction];
          int nextY1 = atomArr[i].y + d_y[atomArr[i].direction];
          int nextX2 = atomArr[j].x + d_x[atomArr[j].direction];
          int nextY2 = atomArr[i].y + d_y[atomArr[j].direction];

          if (Math.abs(atomArr[i].x - atomArr[j].x)
              + Math.abs(atomArr[i].y - atomArr[j].y) > Math.abs(nextX1 - nextX2) + Math.abs(nextY1 - nextY2)) {
            time = (Math.abs(atomArr[i].x - atomArr[j].x)
                + Math.abs(atomArr[i].y - atomArr[j].y)) / 2;
          }
        }

        if (time == -1) {
          continue;
        }

        q.add(new Collision(i, j, time));
      }
    }
    return q;
  }
}
