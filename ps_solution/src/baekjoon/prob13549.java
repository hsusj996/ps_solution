package baekjoon;

import java.io.*;
import java.util.*;

public class prob13549 {
	static int N;
	static int K;
	static boolean[] visited = new boolean[100001];

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

		System.out.println(bfs());
	}

	static int bfs() {
		Deque<cdnt> dq = new ArrayDeque<>();
		dq.add(new cdnt(N, 0));

		while (!dq.isEmpty()) {
			cdnt now = dq.poll();
			if (now.pos == K) {
				return now.depth;
			}
			
			if(!visited[now.pos]) {
				visited[now.pos] = true;
			}

			cdnt next1 = new cdnt(now.pos + 1, now.depth + 1);
			cdnt next2 = new cdnt(now.pos - 1, now.depth + 1);
			cdnt next3 = new cdnt(now.pos * 2, now.depth);

			if (isPossible(next3)) {
				dq.addFirst(next3);
			}
			if (isPossible(next1)) {
				dq.add(next1);
			}
			if (isPossible(next2)) {
				dq.add(next2);
			}

		}

		return -1;
	}

	static boolean isPossible(cdnt c) {
		return c.pos >= 0 && c.pos <= 100000 && !visited[c.pos];
	}
}
