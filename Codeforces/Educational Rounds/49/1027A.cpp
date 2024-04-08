#include <bits/stdc++.h>

using namespace std;


int main(){

    cin.tie(0);
    ios_base::sync_with_stdio(false);

    int t; cin >> t;
    for (int i = 0; i <  t; ++i) {
        int n; cin >> n;
        string s; cin >> s;
        bool ok = true;
        for (int j = 0; j < n/2; ++j) {
            if (s[j] == s[n-j-1]) continue;
            else {
                if (abs(s[j]-s[n-j-1]) ==2  ) continue;
                ok = false;
                break;
            }
        }
        cout << (ok ? "YES\n"  : "NO\n") ;
    }


    return 0;
}
