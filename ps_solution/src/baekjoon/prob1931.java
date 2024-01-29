package baekjoon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class prob1931 {
    static class Meeting { // 회의 클래스
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        Meeting meeting_list[] = new Meeting[N];

        for (int i = 0; i < N; i++) {
            meeting_list[i] = new Meeting(sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(meeting_list, new Comparator<Meeting>() { // 정렬
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if (o1.end == o2.end) { // 종료시간이 같은 경우 시작시간을 오름차순으로
                    return o1.start - o2.start;
                }
                return o1.end - o2.end; // 종료시간 오름차순 정렬
            }
        });

        int end = 0;
        int ans = 0;

        for (int i = 0; i < N; i++) {
            if (end <= meeting_list[i].start) { //이전 종료 이후 시작인 회의 선택
                end = meeting_list[i].end;
                ans++;
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
