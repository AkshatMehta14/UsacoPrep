#include <bits/stdc++.h>
using namespace std;

int main() {
    int n, k;
    cin >> n >> k;

    vector<int> zeroes(n);

    for(int i=0; i<k; i++) {
        int t1, t2;
        cin >> t1 >> t2;

        zeroes[t1-1]++;
        if(t2 != n) {
            zeroes[t2]--;
        }
    }

    for(int i=1; i<n; i++) {
        zeroes[i] += zeroes[i-1];
    }

    sort(zeroes.begin(), zeroes.end());

    cout << zeroes[(n/2)];
}