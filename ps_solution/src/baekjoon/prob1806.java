package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob1806 {
  static StringTokenizer st = null;
  static int N, S;
  static int[] arr;
  static int[] sum;
  static int pointerLeft;
  static int pointerRight;

  public static void main(String[] args) throws IOException {
    // 1. 입력 및 초기화
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    pointerLeft = 1;
    pointerRight = 1;

    st = new StringTokenizer(br.readLine());
    sum = new int[N + 1];
    arr = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      sum[i] += sum[i - 1] + arr[i];
    }

    // 2. S합이 이상되는 최초 right 포인터 찾기
    for (int i = 1; i <= N; i++) {
      if (sum[i] >= S) {
        pointerRight = i + 1;
        break;
      }
    }

    // 예외처리
    if (pointerRight == 0) {
      System.out.println(0);
      return;
    }

    // 3. 포인터를 이동시켜 가능한 경우 모두 탐색
    int curlength = pointerRight - 1;
    int curSum = sum[pointerRight - 1];
    int minLength = curlength;
    while (pointerLeft <= pointerRight && pointerRight <= N + 1) {
      if (curSum >= S) {
        minLength = Math.min(minLength, curlength);
        curSum -= arr[pointerLeft++];
        curlength--;
      } else {
        if (pointerRight > N) {
          break;
        }

        curSum += arr[pointerRight++];
        curlength++;
      }
    }

    // 4. 출력
    System.out.println(minLength);
  }
}
