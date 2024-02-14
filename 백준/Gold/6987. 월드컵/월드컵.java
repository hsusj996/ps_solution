import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] ScoreBoard;
	static int result;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ScoreBoard = new int[7][3];
		for (int i = 0; i < 4; i++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 6; j++) {
				for (int k = 0; k < 3; k++) {
					ScoreBoard[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			int[][] ScoreBoardTmp = new int[7][3];
			BackTracking(1, 2, ScoreBoardTmp);
			sb.append(result + " ");
		}

		System.out.println(sb);
	}

	private static void BackTracking(int teamA, int teamB, int[][] scoreBoardTmp) {
		if (teamA == 6) {
			for (int i = 1; i <= 6; i++) {
				for (int j = 0; j < 3; j++) {
					if (scoreBoardTmp[i][j] != ScoreBoard[i][j]) {
						return;
					}
				}
			}
			result = 1;
			return;
		}
		int nextA = teamA;
		int nextB = teamB + 1;
		if (nextB > 6) {
			nextA++;
			nextB = nextA + 1;
		}

		// A 승리
		scoreBoardTmp[teamA][0]++;
		scoreBoardTmp[teamB][2]++;
		if (scoreBoardTmp[teamA][0] <= ScoreBoard[teamA][0] && scoreBoardTmp[teamB][2] <= ScoreBoard[teamB][2]) {
			BackTracking(nextA, nextB, scoreBoardTmp);
		}
		scoreBoardTmp[teamA][0]--;
		scoreBoardTmp[teamB][2]--;

		// 무승부
		scoreBoardTmp[teamA][1]++;
		scoreBoardTmp[teamB][1]++;
		if (scoreBoardTmp[teamA][1] <= ScoreBoard[teamA][1] && scoreBoardTmp[teamB][1] <= ScoreBoard[teamB][1]) {
			BackTracking(nextA, nextB, scoreBoardTmp);
		}
		scoreBoardTmp[teamA][1]--;
		scoreBoardTmp[teamB][1]--;

		// 패배
		scoreBoardTmp[teamA][2]++;
		scoreBoardTmp[teamB][0]++;
		if (scoreBoardTmp[teamA][2] <= ScoreBoard[teamA][2] && scoreBoardTmp[teamB][0] <= ScoreBoard[teamB][0]) {
			BackTracking(nextA, nextB, scoreBoardTmp);
		}
		scoreBoardTmp[teamA][2]--;
		scoreBoardTmp[teamB][0]--;
	}

}