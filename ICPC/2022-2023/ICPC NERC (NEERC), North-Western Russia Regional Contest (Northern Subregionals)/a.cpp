#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

int main()
{
    vector<ll> v(4);
    bool fl = true;
    for (int i = 0; i < 4; ++i) {
        cin >> v[i];
        if (i > 0 && v[i] != v[i-1])
            fl = false;
    }
    ll b;
    cin >> b;
    if (fl)
        cout << 1 ;
    else
    {
        std::sort(v.begin(), v.end());

        for (int i = 1; i < 4; ++i) {
            if (v[0] + b  != v[i])
            {
                fl = true;
                break;
            }
        }
        if (fl)
            cout << 0 ;
        else
            cout << 1;
    }
}
