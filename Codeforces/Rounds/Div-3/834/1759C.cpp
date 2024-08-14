#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;


int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll t;
    cin >> t;
    while(t--)
    {
        ll l, r, x;
        cin >> l >> r >> x;
        ll a, b;
        cin >> a >> b;
        ll mx = max(a,b);
        a = min(a,b);
        b = mx;
        if (a == b)
        {
            cout << 0 << en;
            continue;
        }
        ll dif = b - a;
        if ( dif >= x) {
            cout << 1 << en;
            continue;
        }
        else
        {
            if (r - b >= x) {
                cout << 2 << en;
                continue;
            }
            if (a - l >= x) {
                cout << 2 << en;
                continue;
            }
            if (b - x >= l && a + x <= r) {
                cout << 3 << en;
                continue;
            }
        }
        cout << -1 << en;
    }
    return 0;
}
