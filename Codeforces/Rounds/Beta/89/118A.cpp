#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

ll MOD = 998244353;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    string s;
    cin >> s;
    string ans;
    for (int i = 0; i < s.size(); ++i) {
        s[i] = tolower(s[i]);
        if (s[i] == 'a' || s[i] == 'o' || s[i] == 'y' || s[i] == 'e' || s[i] == 'u' || s[i] == 'i')
            continue;
        ans += '.';
        ans += s[i];
    }
    cout << ans;
    return 0;
}
 
