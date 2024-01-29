package baekjoon;

import java.io.*;
import java.util.*;

public class prob12865 {
	static int N;
	static int K;

	static class item {
		int weight;
		int value;

		public item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	static item[] itemArr;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		itemArr = new item[N + 1];
		dp = new int[K + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			itemArr[i] = new item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				int v = dp[i][j - 1];
				if (itemArr[j].weight <= i) {
					v = Math.max(v, itemArr[j].value + dp[i - itemArr[j].weight][j - 1]);
				}
				dp[i][j] = v;
			}
		}

		System.out.println(dp[K][N]);
	}

}
