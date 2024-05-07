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

i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20);

    int n,m; cin >> n >> m;
    vvl v(n+1, vl(m+1));
    for (int i = 1; i <= n; ++i) {
        str s; cin >> s;
        for (int j = 0; j < m; ++j) {
            v[i][j+1] = s[j] - '0';
        }
    }
    vvi up(n+1, vi(m+1)),
    left(n+1, vi(m+1)),
    right(n+1, vi(m+1));

    for (int col = 1; col <= m; ++col) {
        for (int row = 1; row <= n; ++row) {
            if (v[row][col] == 0) continue;
            up[row][col]++;
            if (v[row-1][col] == 1) up[row][col] += up[row-1][col];
        }
    }

    for (int i = 1; i <=n; ++i) {
        stack<pii> st;
        for (int j = 1; j <= m; ++j) {
            if (v[i][j] == 0) continue;
            left[i][j] = 1;
            if (v[i][j-1] == 1) {
                while (!st.empty()) {
                    auto [h, cnt] = st.top();
                    if (h >= up[i][j]) {
                        st.pop();
                        left[i][j] += cnt;
                    } else {
                        break;
                    }
                }
            } else {
                st = {};
            }
            st.push({up[i][j], left[i][j]});
        }
    }
    for (int i = 1; i <= n; ++i) {
        stack<pii> st;
        for (int j = m; j >=1; --j) {
            if (v[i][j] == 0) continue;
            right[i][j] = 1;
            if (1+j <= m && v[i][j+1] == 1) {
                while (!st.empty()) {
                    auto [h, cnt] = st.top();
                    if (h >= up[i][j]) {
                        st.pop();
                        right[i][j] += cnt;
                    } else {
                        break;
                    }
                }
            } else {
                st = {};
            }
            st.push({up[i][j], right[i][j]});
        }
    }

    ll res = 0;
    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= m; ++j) {
            int h = up[i][j];
            int w = left[i][j] + right[i][j] - 1;
            res = max(res, 1ll * h * w);
        }
    }
    cout << res;
}
