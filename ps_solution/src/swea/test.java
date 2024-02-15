package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int max = 0;
            int needDay = 0;

            // 입력받은 나무의 높이 중 최대값 찾기
            for (int i = 0; i < N; i++) {
                int height = Integer.parseInt(st.nextToken());
                max = Math.max(max, height);
            }

            // 나무의 높이를 맞추기 위한 최소 날짜 계산
            for (int i = 0; i < N; i++) {
                int diff = max - i % 2;  // 홀수 번째 날은 1, 짝수 번째 날은 2 자라기 때문에 차이를 구함
                needDay += (diff + 1) / 2; // 나무의 높이를 맞추기 위한 필요한 일수 계산
            }

            System.out.println("#" + test_case + " " + needDay);
        }
    }
}
