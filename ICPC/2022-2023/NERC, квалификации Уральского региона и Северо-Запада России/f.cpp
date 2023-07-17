#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;
 
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll n, r;
    cin >> n >> r;
    vector<ll> sum(n);
    vector<ll> ans(n);
    for (int i = 0; i < n; ++i) {
        ll si;
        cin >> si;
        if (i == 0)
        {
            sum[i] = si;
        }
        else 
        sum[i] = si + sum[i-1];
    }

    for (int i = 0; i < n; ++i) {
       ll left = i;
       ll right = n-1;
       ll res = -1;
       ll minus_index;
       while (left <= right) // bsr
       {
           ll mid = (left + right) / 2;
           minus_index = 2 * i - mid - 1;
           ll minus = 0;
           if (minus_index >=0)
           {
               minus = sum[minus_index];
           }
           if (sum[mid] - minus  <= r)
           {
               res = mid - i;
               left  = mid + 1;
           }
           else
               right = mid - 1;
       }
       if (right == n - 1  && minus_index >=0 )
       {
           left = 0;
           right = minus_index;
           while (left <= right)
           {
               ll mid = (left + right) / 2;
               ll minus = 0;
               if (mid - 1 >= 0)
                   minus = sum[mid - 1];
               if (sum[n-1] - minus <= r)
               {
                   res = max(res, i - mid);
                   right = mid - 1;
               }
               else
                   left = mid + 1;
           }
       }
       ans[i] = res;
    }
    for (int i = 0; i < n; ++i) {
        cout << ans[i] << en;
    }
    return 0;
}
