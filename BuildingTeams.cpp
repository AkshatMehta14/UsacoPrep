#include <bits/stdc++.h>
using namespace std;

int n, m;
vector<int> visited;
vector<vector<int>> adjList;

bool impossible = false;

void dfs(int node, int team) {
    if(impossible) {
        return; 
    }
    if(visited[node] == team) {
        return;
    } else if(visited[node] != 0) {
        impossible = true;
    }
    visited[node] = team;
    for(int theNode : adjList[node]) {
        dfs(theNode, team%2 + 1);
    }
}

int main() {
    cin >> n >> m;

    visited.resize(n);
    adjList.resize(n);

    for(int i=0; i<m; i++) {
        int friend1, friend2;
        cin >> friend1 >> friend2;
        adjList[friend1-1].push_back(friend2-1);
        adjList[friend2-1].push_back(friend1-1);
    }

    for(int i=0; i<n; i++) {
        if(visited[i] == 0) {
            dfs(i, 1);
        }
    }

    if(impossible) {
        cout << "IMPOSSIBLE";
        return 0;
    }

    for(int i=0; i<n; i++) {
        cout << visited[i] << " ";
    }
}