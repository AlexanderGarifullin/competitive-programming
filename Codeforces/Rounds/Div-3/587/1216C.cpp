#include <bits/stdc++.h>

// #pragma GCC target ("avx2")
// #pragma GCC optimize("O3")
// #pragma GCC optimize("unroll-loops")


#define en '\n'
//#define f first
//#define s second
//#define mp make_pair
#define  isz(x) int((x).size())
#define ins insert
#define pb push_back
#define eb emplace_back
#define ft front()
#define bk back()
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
#define fac(x, col) for(auto x: col)
#define facl(x, col) for(auto &x: col)
#define forn(start, end) for(int i = start; i < end; ++i)
#define cmo cout << -1 << en
#define ifyn(x) x ? cyes : cno
#define xout(x) cout << x << en
#define xin(x) cin >> x
#define nline cout << en
#define cen cerr <<"\n"



using namespace std;

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


vl getinterset (ll x1,ll y1,ll  x2, ll y2, ll x3, ll y3,ll  x4,ll y4) {
    vl v(4);
    if (x3 < x1) {
        swap(x1, x3);
        swap(y1, y3);
        swap(x2, x4);
        swap(y2, y4);
    }
    // x1 < x3
    if (x2 < x3 || y1 > y4 || y2 < y3) return {0,0,0,0};
    v[0] = x3;
    v[1] = max(y1,y3);
    v[2] = min(x2,x4);
    v[3] = min(y2, y4);
    return v;
}

ll getsqrt(vl v) {
    return abs(v[0]-v[2]) * abs(v[1]-v[3]);
}

i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20)

    ll x1,y1,x2,y2; cin >> x1 >> y1 >> x2 >> y2;
    ll x3,y3, x4,y4; cin >> x3 >> y3 >> x4 >> y4;
    ll x5, y5, x6, y6; cin >> x5 >> y5 >> x6 >> y6;
    vl inter10 = getinterset(x1,y1,x2,y2, x3,y3, x4,y4);
    vl inter20 = getinterset(x1,y1,x2,y2, x5, y5, x6, y6);
    vl inter12 = getinterset(inter10[0], inter10[1],inter10[2],inter10[3],
                             inter20[0], inter20[1],inter20[2],inter20[3]);
    ifyn(getsqrt({x1,y1,x2,y2}) > getsqrt(inter10) + getsqrt(inter20) - getsqrt(inter12));
}
