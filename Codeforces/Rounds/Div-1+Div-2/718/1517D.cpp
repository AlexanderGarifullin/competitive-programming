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

    int n,m,k; cin >> n >> m >> k;
    vvl hor(n, vl(m)), vert(n, vl(m));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m-1; ++j) {
            cin >> hor[i][j];
        }
    }
    for (int i = 0; i < n-1; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> vert[i][j];
        }
    }

    if (k%2) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                cout << -1 << ' ' ;
            }
            nline;
        }
        return 0;
    }
    k /= 2;
    ll inf = 1e18;

    vvvl dp(n, vvl(m, vl(k+1, inf)));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            dp[i][j][0]=0;
        }
    }

    for (int kk = 1; kk <= k; ++kk) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i+1<n) {
                    dp[i][j][kk] = min(dp[i][j][kk],  dp[i+1][j][kk-1] + vert[i][j]);
                }
                if (i-1>=0) {
                    dp[i][j][kk] = min(dp[i][j][kk],  dp[i-1][j][kk-1] + vert[i-1][j]);
                }
                if (j+1 < m) {
                    dp[i][j][kk] = min(dp[i][j][kk],  dp[i][j+1][kk-1] + hor[i][j]);
                }
                if (j - 1 >= 0) {
                    dp[i][j][kk] = min(dp[i][j][kk],  dp[i][j-1][kk-1] + hor[i][j-1]);
                }
            }
        }
    }

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cout << 2*dp[i][j][k] << ' ';
        }
        nline;
    }
}
