#include <bits/stdc++.h>

using namespace std;

const int mod = 998244353;

int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);

    int q; cin >> q;
    int mmax = -1, mmay = -1;

    while(q--){

        char c; cin >> c;
        if (c == '+') {
            int x,y; cin >> x >> y;
            if (y > x) swap(x, y);

            mmax = max(mmax, x);
            mmay = max(mmay, y);
        }
        else {
            int h,w; cin >> h >> w;
            if (w > h) swap(h, w);

            bool ok = (mmax <= h && mmay <= w);

            if (ok) {
                cout << "YES\n";
            }
            else {
                cout << "NO\n";
            }
        }
    }


    return 0;
}
