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

int mod = 998244353;

int MAXN = 300000;
vi fact(MAXN+1);
vi ifact(MAXN+1);

int C(int n, int k) {
    return (1ll * (1ll * fact[n] * ifact[n-k]) % mod) * ifact[k] % mod;
}

void fill_fact() {
    fact[0]=1;
    for (int i = 1; i < isz(fact); ++i) {
        fact[i] = (1ll * i * fact[i-1]) % mod;
    }

}

int binpow(int n, int k, int mod) {
    if (k == 0) {
        return 1;
    }
    if (k % 2) {
        return (1ll * n * binpow(n,k-1,mod)) % mod;
    } else {
        int v = binpow(n, k / 2, mod);
        return (1ll * v * v) % mod;
    }

}

void fill_ifact() {
    ifact[MAXN] = binpow(fact[MAXN], mod-2, mod);
    for (int i = MAXN-1; i >=0; --i) {
        ifact[i] = (1ll * ifact[i+1] * (i+1)) % mod;
    }
}


signed main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20);

    fill_fact();
    fill_ifact();



    int n, k; cin >> n >> k;
    vpii v(2*n);
    for (int i = 0; i < n; ++i) {
        int l,r; cin >> l >> r;
        v[2*i] = {l, 1};
        v[2*i+1] = {r+1, -1};
    }
    sort(all(v));
    int curOpen = 0;
    ll res = 0;
    for (auto [pos, event] : v) {
        if (event == 1) {
            curOpen++;
        } else {
            if (curOpen >= k) {
                res += (1ll * mod + C(curOpen, k) -
                        (curOpen>k ? C(curOpen-1, k) % mod : 0)) % mod;
            }
            curOpen--;
        }
        res %= mod;
    }
    cout << res;
}
