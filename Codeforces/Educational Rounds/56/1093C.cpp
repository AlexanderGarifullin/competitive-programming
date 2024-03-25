#include <bits/stdc++.h>

using namespace std;


int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);

    int n; cin >> n;
    vector<long long> a(n);
    vector<long long > v(n/2);
    for (int i = 0; i < n/2; ++i) {
        cin >> v[i];
    }

    for (int i = 0; i < n/2; ++i) {
        if (i == 0) {
            a.back() = v[0];
        }
        else {
            if (a[n-i] >= v[i]) {
                a[i] = a[i-1];
                a[n-1-i] = v[i] - a[i];
            }
            else {
                long long dif = v[i] - a[n-i];
                a[i] = max(dif, a[i-1]);
                a[n-1-i] = v[i] - a[i];
            }
        }
    }
    for (int i = 0; i < n; ++i) {
        cout << a[i] << ' ';
    }
    return 0;
}
