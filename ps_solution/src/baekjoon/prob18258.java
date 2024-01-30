package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class prob18258 {
	static Queue<Integer> q = new LinkedList<>();
	static int N;
	static StringBuilder result = new StringBuilder();
	static int rear;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String[] cmd = br.readLine().split(" ");

			if (cmd[0].equals("push")) {
				rear = Integer.parseInt(cmd[1]);
				q.add(rear);
				continue;
			} else if (cmd[0].equals("pop")) {
				if (q.isEmpty()) {
					result.append(-1);
				} else {
					result.append(q.poll());
				}
			} else if (cmd[0].equals("size")) {
				result.append(q.size());
			} else if (cmd[0].equals("empty")) {
				if (q.isEmpty()) {
					result.append(1);
				} else {
					result.append(0);
				}
			} else if (cmd[0].equals("front")) {
				if (q.isEmpty()) {
					result.append(-1);
				} else {
					result.append(q.peek());
				}
			} else if (cmd[0].equals("back")) {
				if (q.isEmpty()) {
					result.append(-1);
				} else {
					result.append(rear);
				}
			} else {
				return;
			}
			result.append("\n");
		}

		System.out.println(result);
	}

}
