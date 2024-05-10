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

    int t_; cin >> t_; while(t_--) {
        int n,k; cin >> n >> k;
        vi x(n); fin(x);
        vi y(n); fin(y);
        map<int,int> cnt;
        for (int i = 0; i < n; ++i) {
            cnt[x[i]]++;
        }
        vpii v;
        v.reserve(isz(cnt)+2);
        v.pb({cnt.begin()->first, 0});
        for (auto [val, c] : cnt) {
            v.pb({val,c});
        }
        v.pb({(--cnt.end())->first, 0});
        vi maxPref(isz(v)+2);
        vi maxSuf(isz(v)+2);

        int l = 0, r= 1, s= 0;
        while(r<=isz(cnt)) {
            auto [predX, cnt1] = v[l];
            auto [curX, cnt2] = v[r];
            if (curX - predX <= k) {
                s += cnt2;
            } else {
                while(curX - v[l].first > k) {
                    s -= v[l].second;
                    l++;
                }
                s += cnt2;
            }
            maxPref[r] = max(maxPref[r-1], s);
            r++;
        }

        l = isz(cnt)+1;
        r = isz(cnt);
        s = 0;
        while(r >= 1) {
            auto [predX, cnt1] = v[l];
            auto [curX, cnt2] = v[r];
            if (abs(curX - predX) <= k) {
                s += cnt2;
            } else {
                while(abs(curX - v[l].first) > k) {
                    s -= v[l].second;
                    l--;
                }
                s += cnt2;
            }
            maxSuf[r] = max(maxSuf[r+1], s);
            r--;
        }

        int ans = 0;
        for (int i = 1; i <= isz(cnt); ++i) {
            ans = max(ans, maxPref[i] + maxSuf[i+1]);
        }

        cout << ans << en;
    }
}
