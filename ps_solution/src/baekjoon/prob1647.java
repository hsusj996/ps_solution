package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class prob1647 {
  static class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      return this.weight - o.weight;
    }
  }

  static StringTokenizer st = null;
  static int N, M;
  static Edge[] edgeArr;
  static int[] parents;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 입력
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    parents = new int[N + 1];
    edgeArr = new Edge[M];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      edgeArr[i] = new Edge(from, to, weight);
    }

    // 1. 크루스칼 수행
    // 1.1. 초기화
    MakeSet();

    // 1.2. 간선 정렬
    Arrays.sort(edgeArr);

    // 1.3. 반복문 수행
    int weightSum = 0;
    List<Edge> spanningEdgeList = new ArrayList<>();
    for (Edge cur : edgeArr) {
      if (!Union(cur.from, cur.to)) {
        continue;
      }

      weightSum += cur.weight;
      spanningEdgeList.add(cur); // 스패닝 구성하는 간선 정보 저장
    }

    // 2. 포함된 간선 중 가중치가 가장 큰 간선 찾기
    int maxWeight = 0;

    for (Edge cur : spanningEdgeList) {
      if (cur.weight > maxWeight) {
        maxWeight = cur.weight;
      }
    }

    // 3. 출력
    System.out.println(weightSum - maxWeight);
  }

  private static boolean Union(int a, int b) {
    int aRoot = FindSet(a);
    int bRoot = FindSet(b);

    if (aRoot == bRoot) {
      return false;
    }

    parents[bRoot] = aRoot;
    return true;
  }

  private static int FindSet(int a) {
    if (parents[a] == a) {
      return a;
    }

    return parents[a] = FindSet(parents[a]);
  }

  private static void MakeSet() {
    for (int i = 1; i <= N; i++) {
      parents[i] = i;
    }
  }
}
