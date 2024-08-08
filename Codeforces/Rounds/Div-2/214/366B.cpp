#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;


int main()
{
   ios_base::sync_with_stdio(false);
   cin.tie(NULL);
    int n, k;
    cin >> n >> k;
    vector<int> sum (k,0);
    for (int i = 0; i < n; ++i) {
        int x;
        cin >> x;
        sum[i % k] +=x;
    }
    int mins = 1e9;
    int ind = -1;
    for (int i = 0; i < k; ++i) {
        if (sum[i] < mins)
        {
        mins = sum[i];
        ind = i + 1;
        }
    }
    cout << ind;
}
