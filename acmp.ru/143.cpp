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

bool cmp(str s1, str s2){
    if (isz(s1) < isz(s2)) return true;
    if (isz(s1) > isz(s2)) return false;
    for (int i = 0; i < isz(s1); ++i) {
        if (s1[i] < s2[i]) return true;
        if (s1[i] > s2[i]) return false;
    }
    return false;
}

str sub(str a, str b) {
    bool minus = cmp(a, b);
    str r;

    // a > b
    if (minus) {
        swap(a,b);
    }
    reverse(all(a));
    reverse(all(b));
    int n = isz(a);
    while(isz(b) < n) b += '0';
    for (int i = 0; i < n; ++i) {
        int c1 = a[i] -'0';
        int c2 = b[i] - '0';
        if (c1 >= c2) {
            r += char('0' + (c1 - c2));
        } else{
            r += char('0' + (10 + c1 - c2));
            for (int j = i+1; j < n; ++j) {
                if (a[j] == '0') {
                    a[j] = '9';
                } else {
                    a[j]--;
                    break;
                }
            }
        }
    }

    while(!r.empty() && r.back() == '0') r.pop_back();
    if (r.empty()) r = "0";
    reverse(all(r));

    if (minus) {
        r  = '-' + r;
    }
    return r;
}

i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20);

    str s1, s2;
    cin>>s1 >> s2;
    cout << sub(s1,s2);
}
