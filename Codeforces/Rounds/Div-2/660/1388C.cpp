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


void print(__int128 x) {
    if (x < 0) {
        putchar('-');
        x = -x;
    }
    if (x > 9) print(x / 10);
    putchar(x % 10 + '0');
}

vvi g;
bool ok = true;
vi p,h;
vi sumP;
vi cntGood;

void dfs(int cur, int pred) {
    sumP[cur] += p[cur];
    ll nextCntGood = 0;
    for (int next: g[cur]) {
        if (next == pred) continue;
        dfs(next, cur);
        sumP[cur] += sumP[next];
        nextCntGood += cntGood[next];
    }
    cntGood[cur] = h[cur] + sumP[cur];
    if (cntGood[cur] % 2) ok = false;
    cntGood[cur] /= 2;
    if (!(0 <= cntGood[cur] && cntGood[cur]  <= sumP[cur])) ok = false;
    if (nextCntGood > cntGood[cur]) ok = false;
}




i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20);

    int t_; cin >> t_; while(t_--) {
        int n,m; cin >> n >> m;
        p = vi(n+1);
        for (int i = 1; i <= n; ++i) {
            cin >> p[i];
        }
        h = vi(n+1);
        for (int i = 1; i <= n; ++i) {
            cin >> h[i];
        }
        ok = true;
        g = vvi(n+1);
        for (int i = 0; i < n - 1; ++i) {
            int x,y; cin >> x >> y;
            g[x].pb(y);
            g[y].pb(x);
        }
        sumP = vi(n+1);
        cntGood = vi(n+1);
        dfs(1,1);
        ifyn(ok);
    }
}
