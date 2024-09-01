import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

class UserSolution {
    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static class Node {
        int edgeCnt;
        ArrayList<Edge> edgeList;

        public Node() {
            this.edgeCnt = 0;
            this.edgeList = new ArrayList<>();
        }

        public void addEdge(int to, int weight) {
            this.edgeCnt++;
            Edge edge = new Edge(to, weight);
            this.edgeList.add(edge);
        }

        public void sortEdge() {
            Collections.sort(this.edgeList);
        }
    }

    final int INF = 1_000_000_000;
    HashMap<Integer, Node> nodeMap;
    HashMap<Integer, Node> revNodeMap;
    int roadCnt;
    int cityCnt;

    public int init(int N, int sCity[], int eCity[], int mCost[]) {
        nodeMap = new HashMap<>();
        revNodeMap = new HashMap<>();
        roadCnt = N;
        for (int i = 0; i < N; i++) {
            int s = sCity[i];
            int e = eCity[i];
            int c = mCost[i];

            if (nodeMap.containsKey(s)) {
                nodeMap.get(s).addEdge(e, c);
            } else {
                nodeMap.put(s, new Node());
                nodeMap.get(s).addEdge(e, c);
            }
            if (revNodeMap.containsKey(e)) {
                revNodeMap.get(e).addEdge(s, c);
            } else {
                revNodeMap.put(e, new Node());
                revNodeMap.get(e).addEdge(s, c);
            }
        }

        return cityCnt = nodeMap.size();
    }

    public void add(int sCity, int eCity, int mCost) {
        nodeMap.get(sCity).addEdge(eCity, mCost);
        revNodeMap.get(eCity).addEdge(sCity, mCost);
    }

    public int cost(int mHub) {
        HashMap<Integer, Integer> minMap = dijkstra(nodeMap, mHub);
        HashMap<Integer, Integer> revMinMap = dijkstra(revNodeMap, mHub);

        int ret = 0;
        for (Integer n : minMap.keySet()) {
            ret += minMap.get(n);
            ret += revMinMap.get(n);
        }

        return ret;
    }

    public HashMap<Integer, Integer> dijkstra(HashMap<Integer, Node> map, int start) {
        HashMap<Integer, Integer> minMap = new HashMap<>();
        for (Integer n : map.keySet()) {
            minMap.put(n, INF);
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        minMap.put(start, 0);

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            Node node = map.get(cur.to);
            for (Edge e : node.edgeList) {
                int dist = cur.weight + minMap.get(cur.to);
                if (minMap.get(e.to) > dist) {
                    minMap.replace(e.to, dist);
                    pq.add(e);
                }
            }
        }

        return minMap;
    }
}
