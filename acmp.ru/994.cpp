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

void print(  vector<vector<pair<short, short>>> dp) {
    for (int i = 0; i < isz(dp); ++i) {
        for (int j = 0; j < isz(dp[i]); ++j) {
            cout << dp[i][j].first << ' ' ;
        }
        nline;
    }
    nline;
}

void print2(  vector<vector<pair<short, short>>> dp) {
    for (int i = 0; i < isz(dp); ++i) {
        for (int j = 0; j < isz(dp[i]); ++j) {
            cout << dp[i][j].second << ' ' ;
        }
        nline;
    }
    nline;
}


i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20);

    str s1, s2; cin >> s1 >> s2;
    int n = max(isz(s1), isz(s2));
    reverse(all(s1));
    reverse(all(s2));
    while(isz(s1) < n) {
        s1 += '#';
    }
    while(isz(s2) < n) {
        s2 += '#';
    }
    reverse(all(s1));
    reverse(all(s2));

    vector<vector<pair<short, short>>> dpPref(n+2, vector<pair<short, short>>(n+2));

    // dp[i][j] = максимальная общая последовательность, которая заканчивается на i и j

    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (s1[i-1] == s2[j-1]) {
                dpPref[i][j] = {1, i-1};
                if (dpPref[i-1][j-1].first > 0) {
                    dpPref[i][j] = dpPref[i-1][j-1];
                    dpPref[i][j].first++;
                }
            }
        }
    }

    vector<vector<pair<short, short>>> dpSuf(n+2, vector<pair<short, short>>(n+2));

    for (int i = n; i >=1 ; --i) {
        for (int j = n; j >= 1; --j) {
            if (s1[i-1] == s2[j-1]) {
                dpSuf[i][j] = {1, i-1};
                if (dpSuf[i+1][j+1].first > 0) {
                    dpSuf[i][j] = dpSuf[i+1][j+1];
                    dpSuf[i][j].first++;
                }
            }
        }
    }

//    print(dpPref);
//    print(dpSuf);

    for (int i = 1; i <=n ; ++i) {
        for (int j = 1; j <= n; ++j) {
            dpPref[i][j] = max({dpPref[i][j], dpPref[i-1][j], dpPref[i][j-1], dpPref[i-1][j-1]});
        }
    }
    for (int i = n; i >=1 ; --i) {
        for (int j = n; j >= 1; --j) {
            dpSuf[i][j] = max({dpSuf[i][j], dpSuf[i+1][j], dpSuf[i][j+1], dpSuf[i+1][j+1]});
        }
    }

//    print(dpPref);
//    print(dpSuf);

    pair<short, short> p1 = {0,0},p2 = {0,0};
    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <=n; ++j) {
            int maxLen = p1.first + p2.first;
            int curLen = dpPref[i][j].first + dpSuf[i+1][j+1].first;
            if (curLen > maxLen) {
                p1 = dpPref[i][j];
                p2 = dpSuf[i+1][j+1];
            }
        }
    }

//    cout << p1.first << ' ' << p1.second << en;
//    cout << p2.first << ' ' << p2.second << en;

    str ss1 = s1.substr(p1.second, p1.first);
    str ss2 = s1.substr(p2.second - p2.first + 1, p2.first);
    cout <<ss1 << en << ss2;
}
