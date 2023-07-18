#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll a,x,b,y,t;
    cin >> a >> x >> b >> y >> t;
    cout << a + max(0ll,t-30)*21*x << " ";
    cout << b + max(0ll,t-45)*21*y ;
    return 0;
}
