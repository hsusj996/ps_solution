package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob2252 {
  static StringBuilder result = new StringBuilder();
  static int N, M;
  static ArrayList<Integer>[] graph;
  static int[] InDegree;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    InDegree = new int[N + 1];
    graph = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      graph[i] = new ArrayList<Integer>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int studentA = Integer.parseInt(st.nextToken());
      int studentB = Integer.parseInt(st.nextToken());

      graph[studentA].add(studentB);
      InDegree[studentB]++;
    }

    BFS();

    System.out.println(result);
  }

  private static void BFS() {
    Queue<Integer> q = new LinkedList<>();
    for (int i = 1; i <= N; i++) {
      if (InDegree[i] == 0) {
        q.add(i);
      }
    }

    while (!q.isEmpty()) {
      int cur = q.poll();
      result.append(cur + " ");

      for (int i = 0; i < graph[cur].size(); i++) {
        int next = graph[cur].get(i);
        InDegree[next]--;

        if (InDegree[next] == 0) {
          q.add(next);
        }
      }
    }
  }
}
