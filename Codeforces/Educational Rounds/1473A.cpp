#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;
void  solve ()
{
    int d, n;
    cin >> n >> d;
    int min1 = 101, min2 = 101, max1 = -1;
    for (int i = 0; i < n; ++i) {
        int x;
        cin >> x;
        if (x  > max1)
            max1 = x;
        if (x < min1)
        {
            min2 = min1;
            min1 = x;
        }
        else if (x < min2)
            min2 = x;
    }
    if (max1 <= d || min1 + min2 <= d)
        cout << "YES" << en;
    else
        cout << "NO" << en;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    while(t--)
    {
        solve();
    }
}
