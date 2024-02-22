package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class prob1197 {
  static class Edge {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

  static int V;
  static int E;
  static int[] parents;
  static Edge[] edgeArr;
  static int weightSum = 0;
  static int cnt = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    parents = new int[V + 1];
    edgeArr = new Edge[E];

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      edgeArr[i] = new Edge(from, to, weight);
    }

    Arrays.sort(edgeArr, new Comparator<Edge>() {
      @Override
      public int compare(Edge o1, Edge o2) {
        return o1.weight - o2.weight;
      }
    });

    MakeSet();

    for (int i = 0; i < E; i++) {
      if (Union(edgeArr[i].from, edgeArr[i].to)) {
        cnt++;
        weightSum += edgeArr[i].weight;
      }

      if (cnt == E - 1) {
        break;
      }
    }

    System.out.println(weightSum);
  }

  private static void MakeSet() {
    for (int i = 0; i < V; i++) {
      parents[i] = i;
    }
  }

  private static int Find(int a) {
    if (parents[a] == a) {
      return a;
    }

    return parents[a] = Find(parents[a]);
  }

  private static boolean Union(int a, int b) {
    int aRoot = Find(a);
    int bRoot = Find(b);

    if (aRoot == bRoot) {
      return false;
    }

    parents[bRoot] = aRoot;
    return true;
  }
}
