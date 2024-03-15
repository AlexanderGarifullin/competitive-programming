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

ull x, n;
ull mod = 1e9 + 7;

vector<ull> getprime(ull x) {
    ull d = 2;
    vector<ull> v;
    while(d*d<=x) {
        if (x % d == 0) {
            v.pb(d);
            while(x % d == 0) x/=d;
        }
        d++;
    }
    if (x > 1) {
        v.pb(x);
    }
    return v;
}

ull binpow(ull a, ull n, ull mod) {
    ull res = 1 % mod;
    while (n > 0) {
        if (n % 2ull == 1)
            res = res * a % mod;
        a = a * a % mod;
        n >>= 1ull;
    }
    return res % mod;
}

ull get2(ull d) {
    ull p = 1;
    vector<ull> v;
    ld c = d;
    while(c <= n) {
        v.pb(c);
        c *= d;
    }
    if (v.empty()) return 1ll;
    vector<ull> cnt(isz(v));
    for (int i = 0; i < isz(v); ++i) {
        cnt[i] = n / v[i];
        if (i > 0) {
            cnt[i-1] -= cnt[i];
        }
    }
    for (int i = 0; i < isz(v); ++i) {
        p *= binpow(v[i] % mod, cnt[i], mod);
        p %= mod;
    }
    return p % mod;
}

ull get(vector<ull> prime) {
    ull p = 1;
    for (ull d : prime) {
        ull cur = get2(d);
        p *= cur;
        p %= mod;
    }
    return p % mod;
}

i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20)

    cin >> x >> n;
    vector<ull> prime = getprime(x);
    cout << get(prime);
}
