#include <bits/stdc++.h>
using namespace std;

int main() {
    int t;
    cin >> t;

    for(int f=0; f<t; f++) {
        int n;
        cin >> n;

        vector<int> reg(n);
        vector<int> left(n);
        vector<int> right(n);

        for(int i=0; i<n; i++) {
            cin >> reg[i];
            left[i] = reg[i]+i;
            right[i] = reg[i]-i;
        }

        for(int i=1; i<n; i++) {
            left[i] = max(left[i], left[i-1]);
        }

        for(int i=n-2; i>=0; i--) {
            right[i] = max(right[i], right[i+1]);
        }

        int answer = 0;

        for(int i=1; i<n-1; i++) {
            answer = max(answer, left[i-1] + reg[i] + right[i+1]);
        }

        cout << answer << "\n";
    }
}