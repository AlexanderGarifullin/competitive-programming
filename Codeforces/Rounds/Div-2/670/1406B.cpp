#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll q;
    cin >> q;
    while(q--) {
        ll n;
        cin >> n;
        vector<ll> v(n);
        ll mx = -1e9;
        for (int i = 0; i < n; ++i) {
            cin >> v[i];
            mx = max(mx, v[i]);
        }
        sort(v.begin(), v.end(), [](ll a, ll b) { return abs(a) > abs(b); });
       if (mx < 0)
       {
           cout << v[n-1] * v[n-2] *v[n-3] * v[n-4] * v[n-5] << en;
           continue;
       }
        ll mult = 1;
        for (int i = 0; i < 5; ++i) {
            mult *= v[i];
        }
        for (int i = 5; i < n; ++i) {
            for (int j = 0; j < 5; ++j) {
                ll tek_mult = v[i];
                for (int k = 0; k < 5; ++k) {
                    if (k != j)
                        tek_mult *= v[k];
                }
                mult = max(mult, tek_mult);
            }
        }
        cout <<  mult << en;
    }
    return 0;
}

 
