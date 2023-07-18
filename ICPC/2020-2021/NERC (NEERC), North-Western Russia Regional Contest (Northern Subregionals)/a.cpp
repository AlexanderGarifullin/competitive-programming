#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll y;
    cin >> y;
    if (y == 2006)
        cout << "PetrSU, ITMO" ;
    else if (y == 1996 || y == 1997 || y == 2000 || y == 2007 || y == 2008 || y == 2013 || y == 2018)
        cout << "SPbSU";
    else cout << "ITMO";
    return 0;
}
