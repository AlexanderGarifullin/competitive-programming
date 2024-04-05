#include <bits/stdc++.h>
#define ll long long
using namespace std;

ll t, n;
ll get_perm(ll m, ll k)
{
    if (m == k || k == 0)
        return 1;
    if (k!= 1)
        return get_perm(m-1,k) + get_perm(m-1,k-1);
    return m;
}

int main() {
   ios_base::sync_with_stdio(false);
    cin.tie(0);
    cin >> t;
    while(t--)
    {
        cin >> n;
        ll m = 10 - n;
        while(n--)
        {
            ll x;
            cin >> x;
        }
        cout << get_perm(m,2) * 6 << "\n";
    }
    return 0;
}
 
