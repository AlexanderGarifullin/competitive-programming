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
    ll c = 0;
    ll pred;
    cin >> pred;
    c++;
    for (int i = 1; i < n; ++i) {
        ll x;
        cin >> x;
        if (x != pred)
            c++;
        pred = x;
    }
    cout << c;
    return 0;
}
