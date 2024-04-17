package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob1202 {
  static class Jewel implements Comparable<Jewel> {
    int m;
    int v;

    public Jewel(int m, int v) {
      this.m = m;
      this.v = v;
    }

    @Override
    public int compareTo(Jewel o) {
      if (this.v == o.v) {
        return this.m - o.m;
      }

      return o.v - this.v;
    }

  }

  static StringTokenizer st = null;
  static PriorityQueue<Jewel> pq = new PriorityQueue<>();
  static List<Integer> bagList = new ArrayList<>();
  static int N, K;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      pq.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }

    for (int i = 0; i < K; i++) {
      bagList.add(Integer.parseInt(br.readLine()));
    }
    Collections.sort(bagList);

    int sum = greedy();
    System.out.println(sum);
  }

  private static int greedy() {
    int ret = 0;
    while (!pq.isEmpty()) {
      Jewel j = pq.poll();

      boolean can = binary_search(j.m);
      if (can) {
        ret += j.v;
      }
    }

    return ret;
  }

  private static boolean binary_search(int target) {
    int start = 0;
    int end = bagList.size() - 1;

    while (start <= end) {
      int mid = (start + end) / 2;

      int midV = bagList.get(mid);
      if (midV == target) {
        bagList.remove(mid);
        return true;
      }

      if (midV < target) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }

      // TODO: bs 나머지 처리
    }
  }
}
