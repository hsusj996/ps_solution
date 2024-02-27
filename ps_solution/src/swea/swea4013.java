package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea4013 {
  static List<Integer>[] magnet;
  static StringBuilder result = new StringBuilder();
  static StringTokenizer st = null;
  static int T;
  static int K;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");

      // 초기화
      magnet = new ArrayList[4];
      for (int i = 0; i < 4; i++) {
        magnet[i] = new ArrayList<>();
      }

      // 정보 입력
      K = Integer.parseInt(br.readLine());
      for (int i = 0; i < 4; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 8; j++) {
          magnet[i].add(Integer.parseInt(st.nextToken()));
        }
      }

      // K만큼 회전
      while (K-- > 0) {
        st = new StringTokenizer(br.readLine());
        int magnetNum = Integer.parseInt(st.nextToken()) - 1;
        int direction = Integer.parseInt(st.nextToken());

        // 연쇄된 자석 회전
        if (magnetNum - 1 >= 0 && magnet[magnetNum].get(6) != magnet[magnetNum - 1].get(2)) {
          RotateLeft(magnetNum - 1, direction * (-1));
        }

        if (magnetNum + 1 < 4 && magnet[magnetNum].get(2) != magnet[magnetNum + 1].get(6)) {
          RotateRight(magnetNum + 1, direction * (-1));
        }

        // 현재 자석 회전
        if (direction == 1) {
          int tmp = magnet[magnetNum].get(7);
          magnet[magnetNum].remove(7);
          magnet[magnetNum].add(0, tmp);
        } else {
          int tmp = magnet[magnetNum].get(0);
          magnet[magnetNum].remove(0);
          magnet[magnetNum].add(tmp);
        }
      }

      // 점수 계산
      int scoreSum = 0;
      for (int i = 0; i < 4; i++) {
        scoreSum += (magnet[i].get(0) * Math.pow(2, i));
      }

      // 출력
      result.append(scoreSum).append("\n");
    }

    System.out.println(result);
  }

  private static void RotateRight(int magnetNum, int direction) {
    if (magnetNum + 1 < 4 && magnet[magnetNum].get(2) != magnet[magnetNum + 1].get(6)) {
      RotateRight(magnetNum + 1, direction * (-1));
    }

    // 현재 자석 회전
    if (direction == 1) {
      int tmp = magnet[magnetNum].get(7);
      magnet[magnetNum].remove(7);
      magnet[magnetNum].add(0, tmp);
    } else {
      int tmp = magnet[magnetNum].get(0);
      magnet[magnetNum].remove(0);
      magnet[magnetNum].add(tmp);
    }
  }

  private static void RotateLeft(int magnetNum, int direction) {
    if (magnetNum - 1 >= 0 && magnet[magnetNum].get(6) != magnet[magnetNum - 1].get(2)) {
      RotateLeft(magnetNum - 1, direction * (-1));
    }

    // 현재 자석 회전
    if (direction == 1) {
      int tmp = magnet[magnetNum].get(7);
      magnet[magnetNum].remove(7);
      magnet[magnetNum].add(0, tmp);
    } else {
      int tmp = magnet[magnetNum].get(0);
      magnet[magnetNum].remove(0);
      magnet[magnetNum].add(tmp);
    }
  }
}
