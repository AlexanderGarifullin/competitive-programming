#include <bits/stdc++.h>

using namespace std;


int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);

    int t; cin >> t;
    while(t--) {
        int n, x, y, d;
        cin >> n >> x >> y >> d;

        if (abs(x-y)%d==0) {
            cout << abs(x-y)/d << "\n";
            continue;
        }
        bool del1 = (y - 1) % d == 0;
        bool del2 = (n - y) % d == 0;
        if (!del1 && !del2) {
            cout << -1 << "\n";
            continue;
        }
        int cnt1 = -1, cnt2 = -1;
        if (del1) cnt1 = (x - 1 + d - 1) / d + (y - 1) / d;
        if (del2) cnt2 = (n - x - 1 + d) / d + (n - y) / d;
        int answ = 1e9;
        if (del1) answ = min(answ, cnt1);
        if (del2) answ = min(answ, cnt2);
        cout << answ << "\n";
    }
    return 0;
}
