import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class xy {
		int x;
		int y;

		public xy(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Person {
		int d;
		boolean status;

		public Person(int d, boolean status) {
			super();
			this.d = d;
			this.status = status;
		}
	}

	static StringBuilder result = new StringBuilder();
	static StringTokenizer st = null;
	static int T;
	static int N;
	static int[][] board;
	static List<xy> peopleList;
	static xy[] stairs;
	static int[] stairsLength;
	static int[] peopleToStairs;
	static int peopleCnt;
	static int min;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			result.append("#").append(test_case).append(" ");

			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			stairs = new xy[2];
			stairsLength = new int[2];
			peopleList = new ArrayList<>();
			peopleCnt = 0;
			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] == 1) {
						peopleCnt++;
						peopleList.add(new xy(i, j));
					}
					if (board[i][j] > 1) {
						if (stairs[0] == null) {
							stairs[0] = new xy(i, j);
							stairsLength[0] = board[i][j];
						} else {
							stairs[1] = new xy(i, j);
							stairsLength[1] = board[i][j];
						}
					}
				}
			}

			peopleToStairs = new int[peopleCnt];

			// subset
			SubSet(0);

			result.append(min).append("\n");
		}
		System.out.println(result);
	}

	private static void SubSet(int depth) {
		if (depth == peopleCnt) {
			min = Math.min(min, Simulation());

			return;
		}

		peopleToStairs[depth] = 0;
		SubSet(depth + 1);
		peopleToStairs[depth] = 1;
		SubSet(depth + 1);
	}

	private static int Simulation() {
		int cnt = peopleCnt;
		Person[] peopleArr = new Person[peopleCnt];
		for (int i = 0; i < peopleCnt; i++) {
			peopleArr[i] = new Person(getDistance(i), false); // 0 : 초기상태, 1 : 큐
		}

		Queue<Integer>[] waitingStairsQ = new ArrayDeque[2];
		waitingStairsQ[0] = new ArrayDeque<>();
		waitingStairsQ[1] = new ArrayDeque<>();

		int time = 0;
		while (cnt > 0) {
			time++;
			
			while (!waitingStairsQ[0].isEmpty() && waitingStairsQ[0].peek() == time) {
				waitingStairsQ[0].poll();
				cnt--;
			}

			while (!waitingStairsQ[1].isEmpty() && waitingStairsQ[1].peek() == time) {
				waitingStairsQ[1].poll();
				cnt--;
			}
			
			for (int i = 0; i < peopleCnt; i++) {
				if (peopleArr[i].status) {
					continue;
				}

				if (peopleArr[i].d != 0) {
					peopleArr[i].d--;
				}

				if (peopleArr[i].d == 0) {
					if (waitingStairsQ[peopleToStairs[i]].size() < 3) {
						waitingStairsQ[peopleToStairs[i]].add(time + stairsLength[peopleToStairs[i]]);
						peopleArr[i].status = true;
					}
				}
			}
		}
		return time + 1;
	}

	private static int getDistance(int num) {
		xy p = peopleList.get(num);
		return Math.abs(p.x - stairs[peopleToStairs[num]].x) + Math.abs(p.y - stairs[peopleToStairs[num]].y);
	}

}