#include <bits/stdc++.h>
using namespace std;

int n, m;
vector<bool> visited;
vector<vector<int>> adjList;

void dfs(int node) {
    if(visited[node]) {
        return;
    }
    visited[node] = true;
    for(int theNode : adjList[node]) {
        dfs(theNode);
    }
}

int main() {
    cin >> n >> m;
    visited.resize(n);
    adjList.resize(n);

    vector<int> regs;

    for(int i=0; i<m; i++) {
        int t1, t2;
        cin >> t1 >> t2;
        adjList[t1-1].push_back(t2-1);
        adjList[t2-1].push_back(t1-1);
    }

    for(int i=0; i<n; i++) {
        if(!visited[i]) {
            regs.push_back(i);
            dfs(i);
        }
    }

    cout << regs.size() - 1 << "\n";
    for(int i=1; i<regs.size(); i++) {
        cout << regs[0]+1 << " " << regs[i]+1 << "\n";
    }
}