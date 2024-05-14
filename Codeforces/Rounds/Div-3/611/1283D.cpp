#include <bits/stdc++.h>

// #pragma GCC target ("avx2")
// #pragma GCC optimize("O3")
// #pragma GCC optimize("unroll-loops")


#define en '\n'
//#define mp make_pair
#define  isz(x) int((x).size())
#define ins insert
#define pb push_back
#define eb emplace_back
#define bg(x) begin(x)
#define all(x) bg(x), end(x)
#define rall(x) rbegin(x), rend(x)
#define sortlargetosmall(x) sort(all(x), greater<>())
#define sortsmalltolarge(x) sort(all(x), less<>())
#define fin(x) for (auto &it: x) cin >> it
#define fout(x) for (auto &it: x) cout << it << ' '; cout << en
#define finpairs(x) for (auto &it: x) cin >> it.first >> it.second
#define foutpairs(x) for (auto &it: x) cout << it.first << ' '<<it.second << ' '; cout << en
#define cno cout << "NO" << en
#define cyes cout << "YES" << en
#define PI acos(-1.0L)
#define forn(start, end) for(int i = start; i < end; ++i)
#define cmo cout << -1 << en
#define ifyn(x) x ? cyes : cno
#define nline cout << en



using namespace std;

#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;
template<typename T>
using ordered_set = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;

using ll =  long long;
using ld  = long double;
using i64 = int64_t;
using i32 = int32_t;
using str = string;
// pairs
using pii = pair<int,int>;
using pll = pair<ll,ll>;
using graph = vector<set<int>>;

template<typename T>
using V = vector<T>;
using vi = V<int>;
using vl = V<ll>;
using vpii = V<pii>;
using vpll = V<pll>;
using vb = V<bool>;
using vc = V<char>;
using vs = V<string>;
using vld = V<ld>;

using vvc = V<vc>;
using vvb = V<vb>;
using vvi = V<vi>;
using vvl = V<vl>;
using vvs = V<vs>;
using vvpii = V<vpii>;
using vvpll = V<vpll>;
using vvld = V<vld>;

using vvvi = V<vvi>;
using vvvl = V<vvl>;

using ull = unsigned long long;

using tiii = tuple<int,int,int>;
using tlll = tuple<ll,ll,ll>;

template<typename T>
using pqsmalleststart = priority_queue<T, vector<T>, greater<T>>;
template<typename T>
using pqmaximumstart = priority_queue<T, vector<T>, less<T>>;

template<typename C> void reuniq(C& c) { c.erase(unique(all(c)), end(c));}

signed main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20);

    int n,m; cin >> n >> m;
    vi x(n); fin(x); sort(all(x));
    vpii v;
    for (int i = 0; i < n; ++i) {
        if (v.empty()) {
            v.pb({x[i],x[i]});
        } else {
            if (x[i] - 1 == x[i-1]) {
                v.back().second = x[i];
            } else {
                v.pb({x[i],x[i]});
            }
        }
    }
    set<int> use;
    set<int> order;
    queue<pii> q;
    for(auto [left, right] : v) {
        use.ins(left);
        use.ins(right);
        if (use.find(left - 1) == use.end() && order.find(left - 1) == order.end()) {
            q.push({left - 1, 1});
            order.ins(left - 1);
        }
        if (use.find(right + 1) == use.end()  &&  order.find(right+1) == order.end()) {
            q.push({right + 1, 1});
            order.ins(right + 1);
        }
    }
    ll ans = 0;
    vi vv;
    while(!q.empty() && m) {
        auto [pos, dist ] = q.front();
        q.pop();
        m--;
        vv.pb(pos);
        use.ins(pos);
        ans += dist;
        if (use.find(pos - 1) == use.end() && order.find(pos - 1) == order.end()) {
            q.push({pos - 1, dist + 1});
            order.ins(pos - 1);
        }
        if (use.find(pos + 1) == use.end()  && order.find(pos+1) == order.end()) {
            q.push({pos + 1, dist + 1});
            order.ins(pos + 1);
        }
    }
    cout << ans << en;
    fout(vv);
}
