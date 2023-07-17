#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;
 
int main()
{
    ll k, n;
    cin >> n >> k;
    ll sum = 0;
    ll mult_ind = 1;
    vector<ll> v(n);
    for (int i = 0; i < n; ++i) {
        cin >> v[i];
    }
    sort(v.begin(),v.end(), [](int a, int b){return a > b;});
    for (int i = 0; i < n / k; ++i) {
        for (int j = 0; j < k; ++j) {
            sum += v[i*k + j] * mult_ind;
        }
        mult_ind++;
    }
    for (int i = n - n % k; i < n ; ++i) {
        sum += v[i] * mult_ind;
    }
    cout << sum;
}
