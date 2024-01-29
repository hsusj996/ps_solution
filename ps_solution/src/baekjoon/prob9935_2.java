package baekjoon;

import java.util.*;
import java.io.*;

public class prob9935_2 {
	static String str;
	static String bomb;
	static Stack<Character> stk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		stk = new Stack<>();
		str = br.readLine();
		bomb = br.readLine();

		for (int i = 0; i < str.length(); i++) {
			stk.add(str.charAt(i));
			if (stk.peek() == bomb.charAt(bomb.length() - 1)) {
				Stack<Character> stk2 = new Stack<>();
				for (int j = 0; j < bomb.length(); j++) {
					if (!stk.isEmpty() && stk.peek() == bomb.charAt(bomb.length() - 1 - j)) {
						stk2.add(stk.pop());
						continue;
					} else {
						while (!stk2.isEmpty()) {
							stk.add(stk2.pop());
						}
						break;
					}
				}
			}
		}

		for (int i = 0; i < stk.size(); i++) {
			sb.append(stk.get(i));
		}
		if (sb.toString().equals("")) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb.toString());
		}
	}
}
