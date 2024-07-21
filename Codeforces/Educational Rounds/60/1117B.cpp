#include <bits/stdc++.h>

using namespace std;



int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);

    int n, m, k; cin >> n >> m >> k;
    vector<int> v(n);
    for (int i = 0; i < n; ++i) {
        cin >> v[i];
    }
    sort(v.rbegin(),v.rend());
    if (m <= k) {
        cout << 1ll * v[0] * m;
    }
    else {
        int cnt = m / (k + 1);
        long long answ = 1ll * cnt * v[1];
        long long x = 1ll * k * v[0];
        answ += x * cnt;
        answ += 1ll * v[0] * (m % (k + 1));
        cout << answ;
    }

    return 0;
}
