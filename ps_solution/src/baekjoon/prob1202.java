package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
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
      if (this.m == o.m) {
        return o.v - this.v;
      }

      return this.m - o.m;
    }

  }

  static StringTokenizer st = null;
  static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
  static Jewel[] jewels;
  static int[] bagArr;
  static int N, K;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    jewels = new Jewel[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    Arrays.sort(jewels);

    bagArr = new int[K];
    for (int i = 0; i < K; i++) {
      bagArr[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(bagArr);

    long ans = 0;
    for (int i = 0, j = 0; i < K; i++) {
      while (j < N && jewels[j].m <= bagArr[i]) {
        pq.add(jewels[j++].v);
      }

      if (!pq.isEmpty()) {
        ans += pq.poll();
      }
    }

    System.out.println(ans);
  }
}