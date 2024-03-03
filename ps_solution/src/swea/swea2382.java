package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class swea2382 {
    static class xy {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof xy)) {
                return false;
            }
            xy o = (xy) obj;
            return this.x == o.x && this.y == o.y;
        }

    }

    static class Group implements Comparable<Group> {
        int x;
        int y;
        int direction;
        int microbeCnt;
        boolean alive;

        public Group(int x, int y, int direction, int microbeCnt) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.microbeCnt = microbeCnt;
            this.alive = true;
        }

        @Override
        public int compareTo(Group o) {
            return o.microbeCnt - this.microbeCnt;
        }
    }

    static List<Group> groupList;
    static Map<xy, PriorityQueue<Group>> moveMap;
    static StringBuilder result = new StringBuilder();
    static StringTokenizer st = null;
    static int T, N, M, K;
    static int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            result.append("#").append(test_case).append(" ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            groupList = new ArrayList<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                switch (direction) {
                    case 1:
                        direction = 0;
                        break;
                    case 2:
                        direction = 2;
                        break;
                    case 3:
                        direction = 3;
                        break;
                    case 4:
                        direction = 1;
                        break;
                    default:
                        break;
                }

                groupList.add(new Group(x, y, direction, cnt));
            }

            while (M-- > 0) {
                Simulation();
            }

            int sum = 0;
            for (Group c : groupList) {
                sum += c.microbeCnt;
            }

            result.append(sum).append("\n");
        }
        System.out.println(result);
    }

    private static void Simulation() {
        moveMap = new HashMap<>();

        // 이동
        for (int i = groupList.size() - 1; i >= 0; i--) {
            Group g = groupList.get(i);
            if (g.microbeCnt == 0) {
                groupList.remove(g);
                continue;
            }

            int newX = g.x + d[0][g.direction];
            int newY = g.y + d[1][g.direction];

            if (IsOutBound(newX, newY)) {
                g.microbeCnt /= 2;
                g.direction = (g.direction + 2) % 4;
            }

            g.x = newX;
            g.y = newY;
            xy cur = new xy(newX, newY);

            if (moveMap.containsKey(cur)) {
                moveMap.get(cur).add(g);
            } else {
                PriorityQueue<Group> pq = new PriorityQueue<>();
                pq.add(g);
                moveMap.put(cur, pq);
            }
        }
        // 충돌처리
        for (Iterator<xy> iter = moveMap.keySet().iterator(); iter.hasNext();) {
            PriorityQueue<Group> curQ = moveMap.get(iter.next());

            Group host = curQ.poll();
            while (!curQ.isEmpty()) {
                Group c = curQ.poll();
                host.microbeCnt += c.microbeCnt;
                groupList.remove(c);
            }
        }
    }

    private static boolean IsOutBound(int x, int y) {
        return x <= 0 || x >= N - 1|| y <= 0 || y >= N - 1;
    }
}
