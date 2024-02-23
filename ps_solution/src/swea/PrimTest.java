package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PrimTest {
  static class Vertex implements Comparable<Vertex> {
    int no;
    int weight;
    Vertex next;

    public Vertex(int no, int weight, Vertex next) {
      this.no = no;
      this.weight = weight;
      this.next = next;
    }

    @Override
    public int compareTo(Vertex o) {
      return this.weight - o.weight;
    }
  }

  static StringBuilder result = new StringBuilder();
  static StringTokenizer st = null;
  static int T, V, E;
  static Vertex[] adjList;
  static boolean[] visited;
  static int[] minEdge;
  static long weightSum;
  static int cnt;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");

      st = new StringTokenizer(br.readLine());
      V = Integer.parseInt(st.nextToken());
      E = Integer.parseInt(st.nextToken());

      adjList = new Vertex[V + 1];
      visited = new boolean[V + 1];
      minEdge = new int[V + 1];
      weightSum = 0;
      cnt = 0;

      for (int i = 0; i < E; i++) {
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());

        adjList[from] = new Vertex(to, weight, adjList[from]);
        adjList[to] = new Vertex(from, weight, adjList[to]);
      }

      Arrays.fill(minEdge, Integer.MAX_VALUE);
      minEdge[1] = 0;

      PriorityQueue<Vertex> pq = new PriorityQueue<>();
      pq.offer(new Vertex(1, 0, null));

      while (!pq.isEmpty()) {
        Vertex minVertex = pq.poll();

        if (visited[minVertex.no]) {
          continue;
        }

        weightSum += minVertex.weight;
        visited[minVertex.no] = true;

        if (++cnt == V) {
          break;
        }

        for (Vertex temp = adjList[minVertex.no]; temp != null; temp = temp.next) {
          if (!visited[temp.no] && temp.weight < minEdge[temp.no]) {
            minEdge[temp.no] = temp.weight;
            pq.offer(new Vertex(temp.no, minEdge[temp.no], null));
          }
        }
      }

      result.append(weightSum).append("\n");
    }

    System.out.println(result);
  }
}
