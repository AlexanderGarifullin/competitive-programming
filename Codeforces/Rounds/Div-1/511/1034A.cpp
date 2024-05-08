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

    int n; cin >> n;
    vi v(n); fin(v);
    int g=0;
    for (int i = 0; i < n; ++i) {
        g=__gcd(g,v[i]);
    }
    int mm = 0;
    for (int i = 0; i < n; ++i) {
        v[i] = v[i] / g;
        mm =max(mm, v[i]);
    }
    
    vb sieve(mm+1,true);
    sieve[0]=sieve[1] = false;
    vi prime;
    for (int i = 2; i * i < isz(sieve); ++i) {
        if (sieve[i]) {
            for (int j = i*i; j < isz(sieve); j += i) {
                sieve[j] = false;
            }
        }
    }
    for (int i = 0; i < isz(sieve); ++i) {
        if (sieve[i]) prime.pb(i);
    }

    int res = 0;
    
    vi num(mm+1);
    for (int i = 0; i < n; ++i) {
        int vv = v[i];
        for (int j = 0; j < isz(prime); ++j) {
            if (prime[j] * prime[j] > vv) break;
            if (vv % prime[j] == 0) {
                num[prime[j]]++;
                res = max(res,  num[prime[j]]);
            }
            while(vv % prime[j] == 0) {
                vv /= prime[j];
            }
        }
        if (vv != 1) {
            num[vv]++;
            res = max(res, num[vv]);
        }
    }

    if (res == 0) cout << -1;
    else cout << n - res;
}

 
