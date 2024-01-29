package baekjoon;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class prob5430_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();

        while (T-- > 0) {
            Deque<Integer> deq = new LinkedList<>();    //덱 생성
            boolean reverse = false;    //reverse flag
            String command = sc.next(); //명령어
            int N = sc.nextInt();
            String str_arr = sc.next();
            boolean error = false;

            for (int i = 0; i < str_arr.length(); i++) {    //배열 덱에 삽입하기
                char c = str_arr.charAt(i);                 //StringBuilder를 사용해서 문자열 자르기

                if (c == '[')
                    continue;
                else if (c == ',' || c == ']') {
                    if (sb.length() == 0) { //StringBuilder가 0이라는 것은 삽입할 원소가 없다는 것
                        break;
                    }
                    int tmp = Integer.parseInt(sb.toString());
                    sb.setLength(0);

                    deq.add(tmp);
                } else {
                    sb.append(c);
                }
            }

            for (int i = 0; i < command.length(); i++) {    //명령어 수행
                char cmd = command.charAt(i);

                if (cmd == 'R') {   //뒤집기
                    reverse = !reverse;
                } else {
                    if (deq.size() == 0) {  //예외 처리
                        error = true;
                        break;
                    }
                    if (reverse) {  //reverse에 따라 삭제의 방향이 달라짐
                        deq.removeLast();
                    } else {
                        deq.removeFirst();
                    }
                }
            }

            if (error) {
                System.out.println("error");
                continue;
            } else {
                sb.append("[");
                while (!deq.isEmpty()) {
                    if (reverse) {  //reverse에 따라서 출력방향이 달라짐
                        sb.append(deq.removeLast());
                    } else {
                        sb.append(deq.removeFirst());
                    }
                    if (deq.size() != 0) {
                        sb.append(',');
                    }
                }
                sb.append(']');

                System.out.println(sb.toString());
                sb.setLength(0);
            }
        }

        sc.close();
        return;
    }
}
