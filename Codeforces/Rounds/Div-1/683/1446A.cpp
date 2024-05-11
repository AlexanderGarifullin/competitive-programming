#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

struct classcomp {
    bool operator() (const ll& lhs, const ll& rhs) const
    {return lhs>rhs;}
};

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll t;
    cin >> t;
    while (t--)
    {
        ll n, w;
        cin >> n >> w;
        ll pol = (w + 1) / 2;
        bool fl = 0;
        ll ind_ans = 0;
        ll s = 0;
        vector<ll> numbers(n);
        vector<ll> index;
        for (int i = 0; i < n; ++i) {
            cin >> numbers[i];
        }
        for (int i = 0; i < n; ++i) {
           if (numbers[i]> w)
               continue;
           if (numbers[i] >= pol) {
                fl = 1;
                ind_ans = i + 1;
                break;
           }
           if (s >= pol)
               break;
           s += numbers[i];
           index.push_back(i+1);
        }
        if (fl)
        {
            cout << 1 << en << ind_ans << en;
        }
        else
        {
            if (s < pol)
                cout << -1 << en;
            else
            {
                cout << index.size() << en;
                for(auto &i: index)
                {
                    cout << i << " ";
                }
                cout << en;
            }

        }
    }
    return 0;
}
