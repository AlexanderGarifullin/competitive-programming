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


// SPARSE TABLE

int spSize;
vvl sp;
vl dif;

// want to calculate GCD on all segments with len 2^i
void build (int n) {
    spSize = n;
    int c = __lg(n);
    sp = vvl(c+1, vl(n));
    for (int len = 0; len <= c; ++len) {
        for (int i = 0; i + (1<<len) - 1 < n; ++i) {
            if (len == 0) {
                sp[len][i] = dif[i];
            } else {
                sp[len][i] = __gcd(sp[len-1][i], sp[len-1][i + (1<<len) - (1<<(len-1))]);
            }
        }
    }
}

ll get(int left, int right){
    right++;
    int t = __lg(right - left);
    return __gcd(sp[t][left],sp[t][right - (1<<t)]);
}


i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20);

    int t_; cin >> t_; while(t_--) {
        int n; cin >> n;
        vl a(n);
        fin(a);
        if (n == 1) {
            cout << 1 << en;
        } else {
            dif = {};
            dif.reserve(n-1);
            for (int i = 1; i < n; ++i) {
                dif.pb(abs(a[i]-a[i-1]));
            }

            build(isz(dif));

            function<bool(int, int)> ok = [&](int left, int right){
                ll curGCD = get(left, right);
                return curGCD > 1;
            };


//            for (int i = 0; i < isz(sp); ++i) {
//                fout(sp[i]);
//            }
//            nline;

            int best = 1;
            for (int i = 0; i < isz(dif); ++i) {
                int left = i;
                int right = isz(dif);
                while(left+1<right){
                    int mid = (left+right)/2;
                    if (ok(i, mid)) {
                        left=mid;
                    } else right = mid;
                }
                // left = max I with GCD > 0
                ll curGCD = get(i, left);
                if (curGCD > 1) best=max(best, 1 +  left - i + 1);
            }

            cout << best << en;
        }
    }
}
