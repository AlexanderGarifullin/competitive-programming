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

map<int,set<int>> can;
map<int,set<int>> use;

i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20);

    int x0,y0, x1, y1; cin >> x0 >> y0 >> x1 >> y1;
    int n; cin >> n;
    for (int i = 0; i < n; ++i) {
        int r; cin >> r;
        int a,b; cin >> a >> b;
        for (int j = a; j <= b; ++j) {
            can[r].ins(j);
        }
    }
    int r = -1;
    queue<tiii> q;
    q.push({x0,y0, 0});
    while(!q.empty()) {
        auto [x,y,val] = q.front();
        if (x == x1 && y == y1) {
            r = val;
            break;
        }
        q.pop();
        for (auto [dx,dy] : vpii{
                {1,1},{0, 1},{-1, 1},
                {1, 0}, {-1, 0},
                {-1, -1},{0, -1},{1, -1},
        }) {
            if (1 <= dx + x  && dx + x <= 1e9 &&
            1 <= dy + y && dy + y <= 1e9) {
                int x2 = x + dx;
                int y2 = y + dy;
                if (can.find(x2) == can.end()) continue;
                if (can[x2].find(y2) == can[x2].end()) continue;
                if (use.find(x2) != use.end() && use[x2].find(y2) != use[x2].end()) continue;
                q.push({x2, y2, val + 1});
                use[x2].ins(y2);
            }
        }
    }
    cout << r;
}
