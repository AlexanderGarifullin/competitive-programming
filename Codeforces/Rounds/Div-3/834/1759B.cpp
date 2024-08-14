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
        ll m, s;
        cin >> m >> s;
        ll mx = -1;
        ll s0= 0;
        for (int i = 0; i < m; ++i) {
            ll x;
            cin >> x;
            if (x > mx)
                mx = x;
            s0+=x;
        }
        ll s_ = 2 * (s0 + s);
        ll a = -1;
        bool fl = 0;
        for (int i = 1; i *i <= s_ ; ++i) {
            if (s_ % i == 0)
            {
               if ((double)i == (double) s_/ i - 1)
               {
                   if (i >= mx)
                   {
                       fl = 1;
                       break;
                   }
               }
                if ((double)(s_ /i) == (double)s_ /(s_/i) - 1)
                {
                    if (s_ /i >= mx)
                    {
                        fl = 1;
                        break;
                    }
                }
                if (i > 1 && (double)i-1 == (double)s_ / i)
                {
                    if (i - 1>= mx)
                    {
                        fl = 1;
                        break;
                    }
                }
                if ((double)(s_ /i) - 1 == (double)s_ /(s_/i))
                {
                    if (s_ /i - 1>= mx)
                    {
                        fl = 1;
                        break;
                    }
                }
            }
        }
        if (fl)
            cout << "YES" << en;
        else
            cout << "NO" << en;
    }
    return 0;
}
