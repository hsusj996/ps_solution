package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class prob16235 {
  static class Ground {
    List<Integer> treeList;
    int sumEnergy;
    int deadEnergy;

    Ground() {
      sumEnergy = 5;
      treeList = new ArrayList<>();
      deadEnergy = 0;
    }

    public void commit() {
      this.sumEnergy += this.deadEnergy;
    }
  }

  static int N, M, K;
  static int[][] A;
  static Ground[][] map;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    map = new Ground[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        A[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int old = Integer.parseInt(st.nextToken());

      map[row][col].treeList.add(old);
    }

    while (K-- > 0) {
      Spring();
      Summer();
      Autumn();
      Winter();
    }

    System.out.println(SumOfTree());
  }

  private static void Winter() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'Winter'");
  }

  private static void Autumn() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'Autumn'");
  }

  private static void Summer() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'Summer'");
  }

  private static void Spring() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        map[i][j].treeList.sort(null);

        for (Iterator<Integer> iter = map[i][j].treeList.iterator(); iter.hasNext();) {
          Integer tree = iter.next();

          if (tree > map[i][j].sumEnergy) {
            map[i][j].deadEnergy += tree / 2;
            iter.remove();
          } else {
            map[i][j].sumEnergy -= tree;
            tree++;
          }
        }
      }
    }
  }

  private static int SumOfTree() {
    int ret = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        ret += map[i][j].treeList.size();
      }
    }

    return ret;
  }
}
