#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n' 

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll t;
    cin >> t;
    while (t--)
    {
        ll n;
        cin >> n;
        bool fl = 0;
        vector<string> m(n);
        for (int i = 0; i < n; ++i) {
            string s;
            cin >> s;
            m[i] = s;
        }
        for (int i = 0; i < n- 1 && !fl; ++i) {
            for (int j = 0; j < n- 1 && !fl; ++j) {
                if (m[i][j] == '1' && m[i][j+1] != '1' &&  m[i+1][j] != '1')
                {
                   fl = 1;
                   break;
                }
            }
        }
        if (fl)
            cout << "NO" << en;
        else
            cout << "YES" << en;
    }
    return 0;
}
