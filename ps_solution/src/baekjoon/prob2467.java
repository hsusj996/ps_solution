package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob2467 {
  static int N;
  static int[] solution;
  static int pointerL;
  static int pointerR;
  static int minL;
  static int minR;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    solution = new int[N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      solution[i] = Integer.parseInt(st.nextToken());
    }

    f();

    System.out.println(solution[minL] + " " + solution[minR]);
  }

  private static void f() {
    minL = pointerL = 0;
    minR = pointerR = N - 1;

    int sum = Integer.MAX_VALUE;
    while (pointerL < pointerR) {
      if (sum > Math.abs(solution[pointerL] + solution[pointerR])) {
        minL = pointerL;
        minR = pointerR;
        sum = Math.abs(solution[pointerL] + solution[pointerR]);
      }

      int caseA = Math.abs(solution[pointerL] + solution[pointerR - 1]);
      int caseB = Math.abs(solution[pointerL + 1] + solution[pointerR]);

      if (caseA < caseB) {
        pointerR--;
      } else {
        pointerL++;
      }
    }
  }
}
