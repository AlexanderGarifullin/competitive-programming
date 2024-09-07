#include <bits/stdc++.h>

using namespace std;


int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);

    int n, m; cin >> n>> m;

    int maxCnt = 0;
    vector<vector<int>> v(m);
    for (int i = 0; i < n; ++i) {
        int s, r; cin >> s >> r;
        s--;
        v[s].push_back(r);
        maxCnt = max(maxCnt, (int)v[s].size());
    }

    vector<long long> sums(maxCnt);
    for (int i = 0; i < m; ++i) {
        sort(v[i].begin(), v[i].end(), greater<>());
        long long s = 0;
        for (int j = 0; j < v[i].size(); ++j) {
            s += v[i][j];
            if (s > 0) sums[j] += s;
        }
    }
   cout <<*max_element(sums.begin(),sums.end());

    return 0;
}
