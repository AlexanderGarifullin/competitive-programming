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

using vvi = V<vi>;



ll solve() {
    int n;
    cin >> n;
    ll ans = 1;
    for (int i = 0; i < n ; ++i) {
        int x;
        cin >> x;
        ans *= x;
    };
    return (ans + (n-1)) * 2022 ;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    while (t--)
    {
      cout << solve() << en;
    }
}
