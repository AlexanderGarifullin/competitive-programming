#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll n;
    cin >> n;
    ll a = 0;
    for (int i = 0; i < n; ++i) {
        string s ;
        cin >> s;
        ll gl = 0;
        ll slg = 0;
        ll sls = 0;
        for (int j = 0; j < s.size(); ++j) {
            if (s[j] != 'a' && s[j] != 'e' && s[j] != 'i' && s[j] != 'o' && s[j] != 'u' && s[j] != 'y')
                gl++;
            if (j % 2 == 0)
                if (s[j] != 'a' && s[j] != 'e' && s[j] != 'i' && s[j] != 'o' && s[j] != 'u' && s[j] != 'y')
                    slg++;
             if (j % 2 == 1)
                if (s[j] == 'a' || s[j] == 'e' || s[j] == 'i' || s[j] == 'o' || s[j] == 'u' || s[j] == 'y')
                    slg++;
            if (j % 2 == 0)
                if (s[j] == 'a' || s[j] == 'e' || s[j] == 'i' || s[j] == 'o' || s[j] == 'u' || s[j] == 'y')
                    sls++;
             if (j % 2 == 1)
                if (s[j] != 'a' && s[j] != 'e' && s[j] != 'i' && s[j] != 'o' && s[j] != 'u' && s[j] != 'y')
                    sls++;
        }
        a += min(gl,min(slg,sls));
    }
    cout << a;
    return 0;
}
