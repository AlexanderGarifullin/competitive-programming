#include <bits/stdc++.h>
#define en '\n'
#define f first
//#define s second
#define mp make_pair
#define  sz(x) int((x).size())
#define ins insert
#define pb push_back
#define eb emplace_back
#define ft front()
#define bk back()
#define bg(x) begin(x)
#define all(x) bg(x), end(x)
#define sor(x) sort(all(x))

using namespace std;

using ll =  long long;
using ld  = long double;
using str = string;
// pairs
using pi = pair<int,int>;
using pl = pair<ll,ll>;

template<typename T>
using V = vector<T>;
using vi = V<int>;
using vl = V<ll>;
using vpi = V<pi>;
using vpl = V<pl>;
using vb = V<bool>;


ll solve()
{
    int n;
    cin >> n;
    vi a(n);
    vb check(n);
    vi new_a;
    vb new_check;
    for (int i = 0; i < n; ++i) {
        cin >> a[i];
    }
    new_a.push_back(a[0]);
    new_check.push_back(0);
    for (int i = 1; i < n; ++i) {
        if (a[i] == a[i-1]) {
            a[i]++;
            check[i] = 1;
        }
        else if (a[i] < a[i-1])
        {
            a[i]++;
            check[i] = 1;
        }
        if (i == 1)
        {
            new_a.push_back(a[i]);
            new_check.push_back(check[i]);
        }
        else
        {
            if (a[i] != new_a.back())
            {
                new_a.push_back(a[i]);
                new_check.push_back(check[i]);
            }
        }
    }
    n = new_a.size();
    ll max_len = 1;
    if (n == 1)
    {
        return 1;
    }
    if (n == 2)
    {
        return 1 + (a[1] - a[0] <= 2);
    }
    ll last_check_true = -1;
    ll tek_len = 1;
    if (new_check[0])
        last_check_true = 0;
    for (int i = 1; i < n; ++i) {
        if (new_a[i] - new_a[i-1] == 1)
        {
            tek_len++;
            max_len = max(max_len, tek_len);
        }
        else if (new_a[i] - new_a[i-1] > 2)
        {
            max_len = max(max_len, tek_len);
            tek_len = 1;
            last_check_true = i -1;
        }
        else if (new_a[i] - new_a[i-1] == 2)
        {
            tek_len = i - last_check_true;
            last_check_true = i -1;
            max_len = max(max_len, tek_len);
        }
        if (new_check[i])
            last_check_true  = i;
    }
    max_len = max(max_len, tek_len);
    return max_len;
}

int main()
{
      ios_base::sync_with_stdio(false);
     cin.tie(nullptr);
    int t;
    cin >> t;
    while(t--)
    {
        cout << solve() << en;
    }
}
