package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob17073 {
  static StringTokenizer st = null;
  static int N, W;
  static int cnt = 0;
  static int[] edgeCnt;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());
    edgeCnt = new int[N + 1];

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());

      edgeCnt[u]++;
      edgeCnt[v]++;
    }

    for(int i=2;i<=N;i++){
      if(edgeCnt[i] == 1){
        cnt++;
      }
    }

    System.out.println((double)W / cnt);
  }
}
