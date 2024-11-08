#include <bits/stdc++.h>

// #pragma GCC target ("avx2")
// #pragma GCC optimize("O3")
// #pragma GCC optimize("unroll-loops")


#define en '\n'
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
#define cmo cout << -1 << en
#define ifyn(x) x ? cyes : cno
#define nline cout << en
#define popcount __builtin_popcount
#define popcountll __builtin_popcountll
#define uid(a, b) uniform_int_distribution<int>(a, b)(rng)
#define forn(i, n) for (int i = 0; i < int(n); ++i)

// Макрос для вывода переменных
#define watch(...) if (debug) { print_args(#__VA_ARGS__, __VA_ARGS__); }
const int debug = 1;

using namespace std;


using ll =  long long;
using ld  = long double;
using i64 = int64_t;
using i32 = int32_t;
using str = string;
// pairs
using pii = pair<int,int>;
using pll = pair<ll,ll>;

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
using vvi = vector<vi>;

vvi g, gr;
vi id;
vb used;
vi order;
int cnt = 1;

void topsort(int v) {
    used[v] = true;
    for (int u : g[v]) {
        if (!used[u])
            topsort(u);
    }
    order.pb(v);
}

void dfs(int v) {
    id[v] = cnt;
    for (int u : gr[v]) {
        if (id[u] == 0)
            dfs(u);
    }
}

i32 main() {
    int n,m; cin >> n >> m;
    g.resize(n);
    gr.resize(n);
    for (int i = 0; i < m; ++i) {
        int a,b; cin >> a >> b;
        a--; b--;
        g[a].pb(b);
        gr[b].pb(a);
    }

    id.resize(n);
    used.resize(n);
    for (int i = 0; i < n; ++i) {
        if (!used[i]) topsort(i);
    }
    reverse(all(order));
    for (int v : order) {
        if (id[v] == 0) {
            dfs(v);
            cnt++;
        }
    }
    cout << cnt - 1<< en;
    fout(id);
}
