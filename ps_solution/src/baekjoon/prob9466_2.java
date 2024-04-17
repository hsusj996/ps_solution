package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class prob9466_2 {
  static int T;
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st = null;
  static int N;
  static int[] teamInfo;
  static boolean[] visited;
  static int cnt;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    for (int test_case = 1; test_case <= T; test_case++) {
      N = Integer.parseInt(br.readLine());
      cnt = N;
      teamInfo = new int[N + 1];
      visited = new boolean[N + 1];

      st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= N; i++) {
        teamInfo[i] = Integer.parseInt(st.nextToken());
        if(teamInfo[i] == i){
            visited[i] = true;
            cnt--;
        }
      }

      // 1부터 돌면서 사이클 확인
      for(int i=1;i<=N;i++){
        if(visited[i]) continue;

        HashSet<Integer> visitedSet = new HashSet<>();
        int cur = i;
        visitedSet.add(cur);
        visited[cur] = true;
        while(true){
            int next = teamInfo[cur];

            if(visited[next]){
                break;
            }

            visitedSet.add(next);
            visited[cur] = true;
             cur = next;
        }

        // 사이클 발생
        if(visitedSet.contains(teamInfo[cur])){
            cnt--;
            int newCur = teamInfo[cur];
            while(newCur != cur){
                cnt--;
                newCur = teamInfo[newCur];
            }
        }
      }

      sb.append(cnt).append("\n");
    }

    System.out.println(sb.toString());
  }
}