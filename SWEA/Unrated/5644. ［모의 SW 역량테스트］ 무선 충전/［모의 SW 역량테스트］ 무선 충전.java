import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static StringBuilder result = new StringBuilder();
	static int T, M, A;
	static int[] d_row = { 0, -1, 0, 1, 0 };
	static int[] d_col = { 0, 0, 1, 0, -1 };
	static User userA;
	static User userB;
	static BatteryCharger[] BCArr;

	static class User {
		int row;
		int col;
		int chargeSum;
		int[] MoveInfo;

		public User(int row, int col, int size) {
			this.row = row;
			this.col = col;
			this.MoveInfo = new int[size];
			this.chargeSum = 0;
		}

		public void setMoveInfo(String[] s) {
			for (int i = 0; i < s.length; i++) {
				MoveInfo[i] = Integer.parseInt(s[i]);
			}
		}

		public void charge(List<BatteryCharger> chargerList) {
			if (chargerList.size() == 0) {
				return;
			}

			for (int i = 0; i < chargerList.size(); i++) {
				if (!chargerList.get(i).canCharge) {
					continue;
				}

				this.chargeSum += chargerList.get(i).power;
				chargerList.get(i).canCharge = false;
				break;
			}
		}

		public void move(int n) {
			this.row += d_row[MoveInfo[n]];
			this.col += d_col[MoveInfo[n]];
		}
	}

	static class BatteryCharger implements Comparable<BatteryCharger> {
		int row;
		int col;
		int range;
		int power;
		boolean canCharge;

		public BatteryCharger(int row, int col, int range, int power) {
			this.row = row;
			this.col = col;
			this.range = range;
			this.power = power;
			this.canCharge = true;
		}

		public boolean IsInRange(User user) {
			return (Math.abs(user.row - this.row) + Math.abs(user.col - this.col)) <= this.range;
		}

		@Override
		public int compareTo(BatteryCharger o) {
			return o.power - this.power;
		}
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			userA = new User(1, 1, M);
			userB = new User(10, 10, M);
			BCArr = new BatteryCharger[A];

			userA.setMoveInfo(br.readLine().split(" "));
			userB.setMoveInfo(br.readLine().split(" "));

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				int range = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());

				BCArr[i] = new BatteryCharger(row, col, range, power);
			}
			Arrays.sort(BCArr);

			Charge();

			for (int i = 0; i < M; i++) {
				Simulation(i);
			}

			result.append("#").append(test_case).append(" ").append(userA.chargeSum + userB.chargeSum).append("\n");
		}

		System.out.println(result);
	}

	private static void Charge() {
		List<BatteryCharger> InRangeChargersA = new ArrayList<>();
		List<BatteryCharger> InRangeChargersB = new ArrayList<>();

		for (int i = 0; i < A; i++) {
			if (BCArr[i].IsInRange(userA)) {
				InRangeChargersA.add(BCArr[i]);
			}
			if (BCArr[i].IsInRange(userB)) {
				InRangeChargersB.add(BCArr[i]);
			}
		}

//		Collections.sort(InRangeChargersA);
//		Collections.sort(InRangeChargersB);

		if (InRangeChargersA.size() == 1 && InRangeChargersB.size() == 1) {
			if (InRangeChargersA.get(0) == InRangeChargersB.get(0)) {
				userA.chargeSum += InRangeChargersA.get(0).power / 2;
				userB.chargeSum += InRangeChargersA.get(0).power / 2;
				return;
			}
		}

		if (InRangeChargersA.size() > 1 && InRangeChargersB.size() > 1) {
			if (InRangeChargersA.get(0) == InRangeChargersB.get(0)) {
				if (InRangeChargersA.get(1).power > InRangeChargersB.get(1).power) {
					userB.charge(InRangeChargersB);
					userA.charge(InRangeChargersA);
				} else {
					userA.charge(InRangeChargersA);
					userB.charge(InRangeChargersB);
				}

				for (BatteryCharger bc : BCArr) {
					bc.canCharge = true;
				}
				return;
			}
		}

		if (InRangeChargersA.size() <= InRangeChargersB.size()) {
			userA.charge(InRangeChargersA);
			userB.charge(InRangeChargersB);
		} else {
			userB.charge(InRangeChargersB);
			userA.charge(InRangeChargersA);
		}

		for (BatteryCharger bc : BCArr) {
			bc.canCharge = true;
		}
	}

	private static void Simulation(int n) {
		userA.move(n);
		userB.move(n);

		Charge();
	}

}