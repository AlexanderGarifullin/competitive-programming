#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll n1,n2,n12;
    cin >> n1 >> n2 >> n12;
    cout << floor((n1+1)*(n2+1)/ (double(n12 + 1)) - 1);
    return 0;
}
