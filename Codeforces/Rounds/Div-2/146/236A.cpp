#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;


int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    string s;
    cin >> s;
   set<ll> st;
    for (int i = 0; i < s.size(); ++i) {
       st.insert(s[i]-'a');
    }
    if (st.size() % 2 == 1)
        cout << "IGNORE HIM!";
    else
        cout <<"CHAT WITH HER!" ;
    return 0;
}
