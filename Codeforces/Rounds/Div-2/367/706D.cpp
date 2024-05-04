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

string bin(auto x) {
    auto s = bitset<sizeof(x)*8>(x).to_string();
    return s.substr(min(s.size(),s.find('1')));
}

struct Node {
        int cnt = 0;
        Node* to[2] = {0};
};

Node* root = new Node();


void add(int x) {
    Node* v = root;
    for (int i = 30; i >=0 ; --i) {
        if (v->to[0] == 0) {
            v->to[0] = new Node();
        }
        if (v->to[1] == 0) {
            v->to[1] = new Node();
        }
        v = v->to[1 & (x >> i)];
        v->cnt++;
    }
}

void del(int x) {
    Node* v = root;
    for (int i = 30; i >=0; --i) {
        v = v->to[1 & (x >> i)];
        v->cnt--;
    }
}

ll get(int x) {
    ll r= 0;
    Node* v = root;
    for (int i = 30; i >= 0; --i) {
        if (1 & (x>>i)) {
            // now 1 we want 0
            if (v->to[0]->cnt > 0) {
                v = v->to[0];
                r += (1<<i);
            } else {
                v = v->to[1];
            }
        } else {
            // now 0 we want 1;
            if (v->to[1]->cnt > 0) {
                v = v->to[1];
                r += (1<<i);
            } else {
                v = v->to[0];
            }
        }
    }
    return r;
}

i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20);

    add(0);

    int q; cin >> q;
    while(q--) {
        char c; cin >> c;
        int x; cin >> x;
        if (c == '+') {
            add(x);
        } else if (c == '-') {
            del(x);
        } else {
            cout << get(x) << en;
        }
    }
}
