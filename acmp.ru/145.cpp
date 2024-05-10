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
    int last = 1;
    while(isz(b) < n) b += '0';
    for (int i = 0; i < n; ++i) {
        last = max(last, i+1);
        int c1 = a[i] -'0';
        int c2 = b[i] - '0';
        if (c1 >= c2) {
            r += char('0' + (c1 - c2));
        } else{
            r += char('0' + (10 + c1 - c2));
            for (int j = last; j < n; ++j) {
                if (a[j] == '0') {
                    a[j] = '9';
                } else {
                    last = j;
                    a[j]--;
                    if (a[j] == '0') last++;
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


str get(ll x) {
    str r;
    while(x) {
        r += char('0' + (x % 10));
        x /= 10;
    }
    if (r.empty()) r = "0";
    reverse(all(r));
    return r;
}

str div(str s, ll k) {
    str r;
    int n = isz(s);
    ll curNumber = 0;
    for (int i = 0; i < n; ++i) {
        int c = s[i] - '0';
        curNumber = curNumber * 10 + c;
        if (curNumber < k) {
            r += "0";
            continue;
        }
        ll d = curNumber / k;
        r += get(d);
        ll x = d * k;
        str res = sub(get(curNumber), get(x));
        ll newNumber = 0;
        for (int j = 0; j < isz(res); ++j) {
            newNumber = newNumber * 10 + (res[j] - '0');
        }
        curNumber = newNumber;
    }
    
    if (r[0] == '0'){
        reverse(all(r));
        while(!r.empty() && r.back() == '0') r.pop_back();
        if (r.empty()) r = "0";
        reverse(all(r));
    }
    
    return r;
}

void solve(){
    str n; cin >> n;
    ll k; cin >> k;
    cout << div(n, k);
}

void brute() {
    for (int i = 0; i <= 1000; ++i) {
        for (int j = 1; j <= 1000; ++j) {
            int r = i / j;
            str my = div(get(i), j);
            if (get(r) != my) {
                cout << "A = " << i << en;
                cout << "B = " << j << en;
                cout << "Correct = " << r << en;
                cout << "My = " << my << en;
                return;
            }
        }
    }
}

i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20);

    solve();
    //brute();
}
