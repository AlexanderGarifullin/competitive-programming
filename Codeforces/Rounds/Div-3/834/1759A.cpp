#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

bool check(string &s)
{
    if (!(s[0] == 'Y' || s[0] == 'e' || s[0] == 's'))
        return false;
    for (int i = 1; i < s.size(); ++i) {
        if (s[i] == s[i-1])
            return  false;
        if (s[i] == 'Y' && s[i-1] != 's')
            return false;
        if (s[i] == 'e' && s[i-1] != 'Y')
            return false;
        if (s[i] == 's' && s[i-1] != 'e')
            return false;
        if (!(s[i] == 'Y' || s[i] == 'e' || s[i] == 's'))
            return false;
    }
    return true;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll t;
    cin >> t;
    while(t--)
    {
        string s;
        cin >> s;
        if (check(s))
            cout <<"YES" << en;
        else
            cout <<"NO" << en;
    }
    return 0;
}
