#include <bits/stdc++.h>
#define en '\n'
#define f first
//#define s second
#define mp make_pair
#define  isz(x) int((x).size())
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

void solve()
{
    int n,k,x,y;
    cin >> n >> k >> x >> y;
    vector<ll> a,b;
    for (int i = 0; i <n ; ++i) {
        char c; ll v;
        cin >> c >> v;
        if (c == 'A') a.push_back(v);
        else b.push_back(v);
    }
    sort(all(a), greater<>());
    sort(all(b), greater<>());

    vl pref_a(a.size()+1), pref_b(b.size()+1);
    for (int i = 1; i <= isz(a) ; ++i) {
        pref_a[i] = pref_a[i-1] + a[i-1];
    }
    for (int i = 1; i <= isz(b) ; ++i) {
        pref_b[i] = pref_b[i-1] + b[i-1];
    }
    ll ans = -1e18L;
    for (int l = max(0,2*k - isz(b)); l <= min(2*k,isz(a)); ++l) {
        if (l <= k)
        {
            
                ans = max(ans, pref_a[l] + pref_b[2 * k - l] - (k - l+0LL) * y);
        }
        else {
                ans = max(ans, pref_a[l] + pref_b[2 * k - l] - (l - k + 0LL) * x);
        }
    }
    cout << ans << en;
}

int main()
{
      ios_base::sync_with_stdio(false);
        cin.tie(nullptr);
    int t;
    cin >> t;
    while (t--)
    {
        solve();
    }
}
