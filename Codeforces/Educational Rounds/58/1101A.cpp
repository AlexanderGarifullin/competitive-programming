#include <bits/stdc++.h>

using namespace std;

const int mod = 998244353;

int main(){

   // cin.tie(0); ios_base::sync_with_stdio(false);

   int t; cin >> t;
   while(t--){
       int l, r, d; cin >> l >> r >> d;
        if (d < l || d > r) {
            cout << d << "\n";
            continue;
        }
        int x = r / d;
        int cnt = x * d;
        while(cnt <= r) cnt += d;
       cout << cnt << "\n";
   }

    return 0;
}
