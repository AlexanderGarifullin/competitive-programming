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
    string s = "NONE";
    unordered_map<string,ll> mp;
    for (int i = 0; i < n; ++i)
    {
        string s_;
        cin >> s_;
        mp[s_]++;
        if (mp[s_] > n / 2)
            s = s_;
    }
    cout << s;
    return 0;
}
