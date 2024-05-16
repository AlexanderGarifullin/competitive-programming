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

    int n; cin >> n;
    vvi c(4, vi(n+1));
    for (int i = 1; i <= 3; ++i) {
        for (int j = 1; j <= n; ++j) {
            cin >> c[i][j];
        }
    }
    vvi g(n+1);
    vi st(n+1);
    bool can = true;
    for (int i = 0; i < n-1; ++i) {
        int u,v; cin >> u >> v;
        g[u].pb(v);
        g[v].pb(u);
        st[u]++; st[v]++;
        if (st[u]>2 || st[v] > 2) {
            can = false;
        }
    }
    if (!can) {
        cmo;
        return 0;
    }
    int root = -1;
    for (int i = 1; i <= n; ++i) {
        if (st[i] == 1) {
            root = i;
            break;
        }
    }
    ll best = 1e18;

    vi cc;
    for (int rootColor = 1; rootColor <= 3; ++rootColor) {
        for (int nextColor = 1; nextColor <= 3; ++nextColor) {
            if (rootColor == nextColor) continue;
            vi color(n+1);
            color[root] = rootColor;
            color[g[root][0]] = nextColor;
            ll val = c[rootColor][root] + c[nextColor][g[root][0]];
            function<void(int,int,int)> dfs = [&](int curV, int predV, int predPredV) {
                if (predV != root) {
                    color[curV] = 6 - color[predV] - color[predPredV];
                    val += c[color[curV]][curV];
                }
                for (int next : g[curV]) {
                    if (color[next] != 0) continue;
                    dfs(next, curV, predV);
                }
            };
            dfs(g[root][0], root, 0);
            bool ok = true;
            function<void(int,int,int)> dfs2 = [&](int curV, int predV, int predPredV) {
                if (predV != root) {
                    set<int> st = {color[curV], color[predV], color[predPredV]};
                    if (isz(st) != 3) {
                        ok = false;
                        return;
                    }
                }
                for (int next: g[curV]) {
                    if (next == predV) continue;
                    dfs2(next, curV, predV);
                }
            };
            dfs2(g[root][0],root,0);
            if (ok && best > val)  {
                best = val;
                cc = color;
            }
        }
    }
    
    cout << best << en;
    for (int i = 1; i <= n; ++i) {
        cout << cc[i] << ' ';
    }
}
