package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob1005 {
    static class Building implements Comparable<Building> {
        int num;
        int remainTime;
        List<Building> prevList;
        List<Building> nextList;

        public Building(int num, int remainTime) {
            this.num = num;
            this.remainTime = remainTime;
            this.prevList = new ArrayList<>();
            this.nextList = new ArrayList<>();
        }

        @Override
        public int compareTo(Building o) {
            return this.remainTime - o.remainTime;
        }
    }

    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;
    static int T;
    static int N, K, W;
    static Building[] buildings;
    static PriorityQueue<Building> pq;
    static boolean[] complete;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            complete = new boolean[N + 1];

            buildings = new Building[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                buildings[i] = new Building(i, Integer.parseInt(st.nextToken()));
            }
        
            pq = new PriorityQueue<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int b1 = Integer.parseInt(st.nextToken());
                int b2 = Integer.parseInt(st.nextToken());

                buildings[b1].nextList.add(buildings[b2]);
                buildings[b2].prevList.add(buildings[b1]);
            }

            W = Integer.parseInt(br.readLine());

            List<Integer> rootList = getFirstBuilding();
            int time = Integer.MAX_VALUE;
            for(int first:rootList){
                time = Math.min(time, Simulation(first));
            }

            sb.append(time).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int Simulation(int first) {
        pq.add(buildings[first]);

        int time = 0;
        while(true && !pq.isEmpty()){
            Building cur = pq.poll();
            int timeCost = cur.remainTime;
            time += timeCost;
            complete[cur.num] = true;

            if(cur.num == W){
                return time;
            }

            PriorityQueue<Building> tempQ = new PriorityQueue<>();
            while(!pq.isEmpty()){
                Building b = pq.poll();
                b.remainTime -= timeCost;
                tempQ.add(b);
            }
            pq = tempQ;

            for(Building next: cur.nextList){
                boolean flag = true;
                for(Building prev: next.prevList){
                    if(!complete[prev.num]){
                        flag = false;
                        break;
                    }
                }

                if(flag){
                    pq.add(next);
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static List<Integer> getFirstBuilding() {
        List<Integer> ret = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (buildings[i].prevList.size() == 0) {
                ret.add(i);
            }
        }
        return ret;
    }
}
