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
    ll t;
    cin >> t;
    while (t--)
    {
        ll n, m;
        cin >> n >> m;
        ll a = 0;
        for (int i = 1; i <= m; ++i) {
            a+= i;
        }
        for (int i = 2; i <= n; ++i) {
            a += m * i;
        }
        cout << a << en;
    }
    return 0;
}
 
