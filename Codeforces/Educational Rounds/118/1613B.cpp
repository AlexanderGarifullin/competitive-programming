#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

ll MOD = 998244353;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    vector<int> v(300003);
    for (int i = 1; i <=300000 ; ++i) {
        v[i] = v[i-1] ^ i;
    }
    ll t;
    cin >> t;
    while (t--)
    {
        ll n;
        cin >> n;
        vector<int> v(n);
        for (int i = 0; i < n; ++i) {
            cin >> v[i];
        }
        std::sort(v.begin(), v.end());
        for (int i = 0; i < n / 2; ++i) {
            cout << v[1 + i]  << " " <<   v[0] << en;
        }

    }
    return 0;
}
 
