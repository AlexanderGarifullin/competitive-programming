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
        ll n;
        cin >> n;
        string s ;
        cin >> s;
        ll a = 0;

        
        for (int i = 0; i < s.size(); ++i) {
            string ns ="";
            vector <ll> c(10);
            ll craz = 0;
            ll cpovt = 0;
            ll maxl = i + 100;
            if (maxl > n)
                maxl = s.size();
            ll max_od = -1;
            for (int j = i; j < maxl; ++j) {
                ns += s[j];
                if (c[s[j] - '0'] == 0) {
                    craz++;
                }
                c[s[j] - '0']++;
                if (c[s[j] - '0'] > max_od)
                    max_od = c[s[j] - '0'] ;
                if (max_od > 10)
                    break;
                if (max_od> craz)
                    continue;
                a++;
            }
        }
        cout << a<< en;
  }
    return 0;
}
