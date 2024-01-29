package baekjoon;

import java.io.*;
import java.util.*;

public class prob12851 {
	static int N;
	static int K;
	static int min_cnt = 0;
	static int[] min_d;

	static class cdnt {
		int pos;
		int depth;

		public cdnt(int pos, int depth) {
			this.pos = pos;
			this.depth = depth;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		min_d = new int[100001];
		Arrays.fill(min_d, Integer.MAX_VALUE);
		BFS(N);

		System.out.println(min_d[K]);
		System.out.println(min_cnt);
	}

	static void BFS(int start) {
		Queue<cdnt> q = new LinkedList<>();
		q.add(new cdnt(start, 0));

		while (!q.isEmpty()) {
			cdnt now = q.poll();

			if (now.pos == K) {
				min_cnt++;
				continue;
			}

			cdnt[] next = new cdnt[3];
			next[0] = new cdnt(now.pos + 1, now.depth + 1);
			next[1] = new cdnt(now.pos - 1, now.depth + 1);
			next[2] = new cdnt(now.pos * 2, now.depth + 1);

			for (cdnt next_cdnt : next) {
				if (isOutBound(next_cdnt) || min_d[next_cdnt.pos] < next_cdnt.depth) {
					continue;
				}

				min_d[next_cdnt.pos] = next_cdnt.depth;
				q.add(next_cdnt);
			}
		}
	}

	static boolean isOutBound(cdnt c) {
		return c.pos < 0 || c.pos > 100000;
	}
}
