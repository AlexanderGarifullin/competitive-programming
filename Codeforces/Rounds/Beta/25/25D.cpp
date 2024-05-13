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
    vi id(n+1);
    vector<list<int>> list(n+1);
    for (int i = 1; i <= n; ++i) {
        id[i] = i;
        list[i] = {i};
    }
    vpii canClose;

    function<int(int)> get = [&](int a){
        if (id[a] != a) {
            id[a] = get(id[a]);
        }
        return id[a];
    };

    function<void(int,int)> unite = [&](int a, int b){
        int x = get(a);
        int y=  get(b);
        if (x == y) return;
        if (isz(list[x]) < isz(list[y])) {
            swap(x,y);
        }
        // x > y
        for (int v : list[y]) {
            id[v] = x;
            list[x].push_back(v);
        }
        list[y].clear();
    };

    for (int i = 0; i < n-1; ++i) {
        int a,b; cin >> a >> b;
        int ida = get(a);
        int idb = get(b);
        if (ida == idb) {
             canClose.pb({a,b});
        } else {
            unite(a,b);
        }
    }
    cout << isz(canClose) << en;
    if (isz(canClose)) {
        for (int i = 2; i <= n; ++i) {
            if (get(1) != get(i)) {
                cout << canClose.back().first << ' ' << canClose.back().second << ' ' << 1 << ' ' << i << en;
                unite(1, i);
                canClose.pop_back();
            }
        }
    }
}
