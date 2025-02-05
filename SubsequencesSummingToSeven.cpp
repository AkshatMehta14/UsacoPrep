#include <bits/stdc++.h>
using namespace std;

int main() {
    freopen("div7.in", "r", stdin);
    freopen("div7.out", "w", stdout);

    int n;
    cin >> n;

    vector<int> arr(n);
    vector<int> pref(n+1);
    pref[0] = 0;

    for(int i=0; i<n; i++) {
        cin >> arr[i];
        pref[i+1] = (pref[i]+arr[i])%7;
    }

    vector<pair<int, int>> nums;
    for(int i=0; i<7; i++) {
        nums.push_back({-1, -1});
    }

    for(int i=0; i<n; i++) {
        if(nums[pref[i]].first == -1) {
            nums[pref[i]].first = i;
        }
        nums[pref[i]].second = i;
    }

    int longest = 0;

    for(int i=0; i<7; i++) {
        longest = max(longest, nums[i].second - nums[i].first);
    }

    cout << longest;
}