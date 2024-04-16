package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob9466 {
  static int T;
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st = null;
  static int N;
  static int[] teamInfo;
  static boolean[] visited;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    for (int test_case = 1; test_case <= T; test_case++) {
      N = Integer.parseInt(br.readLine());
      teamInfo = new int[N + 1];

      st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= N; i++) {
        teamInfo[i] = Integer.parseInt(st.nextToken());
      }

      // 사이클 탐색 시작
      // 1번부터 사이클 여부 판단
      // 사이클 큐를 사용하여 사이클이 완성되면
      // 다음 방문때 방문하지 않도록 설정함
      visited = new boolean[N + 1];
      for(int i=1;i<=N;i++){
        Queue<
      }

    }
  }
}