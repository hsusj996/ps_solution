package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob2696 {
  static StringBuilder result = new StringBuilder();
  static StringTokenizer st = null;
  static int T;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      List<Integer> medianList = new ArrayList<>();
      PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
      PriorityQueue<Integer> minHeap = new PriorityQueue<>();

      int N = Integer.parseInt(br.readLine());

      for (int i = 0; i < N; i++) {
        if (i % 10 == 0) {
          st = new StringTokenizer(br.readLine());
        }
        int n = Integer.parseInt(st.nextToken());
        // 삽입
        if (maxHeap.size() == minHeap.size()) {
          maxHeap.add(n);
        } else {
          minHeap.add(n);
        }

        // 삽입 후 각 heap의 peek를 확인 후 swap
        if (maxHeap.size() > 0 && minHeap.size() > 0) {
          if (maxHeap.peek() > minHeap.peek()) {
            int i1 = maxHeap.poll();
            int i2 = minHeap.poll();

            maxHeap.add(i2);
            minHeap.add(i1);
          }
        }

        // i가 홀수일 때마다 확인
        if ((i + 1) % 2 == 0) {
          continue;
        }
        medianList.add(maxHeap.peek());
      }

      // result에 결과 삽입
      result.append(medianList.size());
      for (int i = 0; i < medianList.size(); i++) {
        if (i % 10 == 0) {
          result.append("\n");
        }
        result.append(medianList.get(i)).append(" ");
      }
      result.append("\n");
    }

    System.out.println(result);
  }
}
