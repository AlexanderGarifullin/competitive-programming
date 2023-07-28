a#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

ll MOD = 998244353;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n;
    cin >> n;
    int a =0;
    set<int> st ;
    while (n--)
    {
        char c;
        int x;
        cin >> c >> x;
        if (c == '+')
            st.insert(x);
        else
        {
            if (st.count(x))
                st.erase(x);
            else
                a++;
        }
        a = max(a, (int)st.size());
    }
    cout << a;
    return 0;
}
 
