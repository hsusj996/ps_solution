#include <bits/stdc++.h>
using namespace std;

int dist[1005];
bool vis[1005];
int pre[1005]; // 다익스트라 갱신할 때 이전 노드 저장
vector<pair<int, int>> adjList[1005]; // 통신 시간, 노드 번호
int cnt; // 트리에 구성된 노드 수
int n, m;
priority_queue<pair<int, int>> pq; // 통신 시간, 노드 번호

int main() {

    cin >> n >> m;
    for(int i=0; i<m; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        adjList[u].push_back({w, v});
        adjList[v].push_back({w, u});
    }

    fill(dist+1, dist+1+n, 1000000000);

    pq.push({0, 1});
    dist[1] = 0;

    while(!pq.empty()) {

        // 1. 최소 거리 정점 구하기
        auto cur = pq.top();
        pq.pop();
        int minVertex = cur.second;
//        cout << cur.second << ' ' << cur.first << '\n';

        if(vis[minVertex]) continue;

        vis[minVertex] = true;

        cnt++;

        for(auto tmp : adjList[minVertex]) {
            if(dist[tmp.second] > dist[minVertex] + tmp.first) {
                dist[tmp.second] = dist[minVertex] + tmp.first;
                pq.push({-dist[tmp.second], tmp.second});
                pre[tmp.second] = minVertex;
            }
        }

        if(cnt == n-1) {
            break;
        }
    }

    cout << cnt << '\n';
    for(int i=2; i<=n; i++) {
        if(pre[i] != 0) {
            cout << pre[i] << ' ' << i  << '\n';
        }
    }
}