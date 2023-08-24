#include <bits/stdc++.h>

// #pragma GCC target ("avx2")
// #pragma GCC optimize("O3")
// #pragma GCC optimize("unroll-loops")


#define en '\n'
#define f first
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



#ifndef __DEBUG_HPP__
#define __DEBUG_HPP__
// ---- ---- ---- ---- ---- ---- DEBUG LIBRARY ---- ---- ---- ---- ---- ----
#define watch(...) debug && std::cerr << "{" << #__VA_ARGS__ << "} = " \
    << std::make_tuple(__VA_ARGS__) << std::endl

template<typename... X>
std::ostream& operator<<(std::ostream& os, const std::pair<X...>& p)
{ return os << std::make_tuple(std::get<0>(p), std::get<1>(p)); }

template<std::size_t I = 0, typename FuncT, typename... Tp>
inline typename std::enable_if<I == sizeof...(Tp), void>::type
for_each_const(const std::tuple<Tp...> &, FuncT) { }

template<std::size_t I = 0, typename FuncT, typename... Tp>
inline typename std::enable_if<I < sizeof...(Tp), void>::type
for_each_const(const std::tuple<Tp...>& t, FuncT f)
{ f(std::get<I>(t)),for_each_const<I + 1, FuncT, Tp...>(t, f); }


template<std::size_t I = 0, typename FuncT, typename... Tp>
inline typename std::enable_if<I == sizeof...(Tp), void>::type
for_each(std::tuple<Tp...> &, FuncT) { }

template<std::size_t I = 0, typename FuncT, typename... Tp>
inline typename std::enable_if<I < sizeof...(Tp), void>::type
for_each(std::tuple<Tp...>& t, FuncT f)
{ f(std::get<I>(t)); for_each<I + 1, FuncT, Tp...>(t, f); }

struct Printer {
    std::ostream& os; bool was{0};
    Printer(std::ostream& os_) : os(os_) { }
    template<typename X> void operator()(X x)
    { os << (was?", ":(was=1,"")) << x; }
};

template<typename... X>
std::ostream& operator<<(std::ostream& os, const std::tuple<X...>& t)
{ return os << "{", for_each_const(t, Printer(os)), os << "}"; }

template<typename Iterator>
std::ostream& print(std::ostream& os, Iterator begin, Iterator end)
{ return os << "{", std::for_each(begin,end,Printer(os)), os << "}"; }

#define OUTPUT(container) template<typename X, typename... T>           \
std::ostream& operator<<(std::ostream& os, const container<X,T...>& c)  \
{ return print(os, all(c)); }
OUTPUT(std::vector) OUTPUT(std::list) OUTPUT(std::deque)
OUTPUT(std::set) OUTPUT(std::unordered_set)
OUTPUT(std::multiset) OUTPUT(std::unordered_multiset)
OUTPUT(std::map) OUTPUT(std::multimap) OUTPUT(std::unordered_map)
#undef RANGE_OUTPUT

#define OUTPUT2(container, get, pop) template<typename X, typename... T> \
std::ostream& operator<<(std::ostream& os, container<X,T...> c) {       \
    std::vector<X> v(c.size());                                         \
    for (unsigned i = 0; i != v.size(); v[i++] = c.get(),c.pop());      \
    return os << v; }
OUTPUT2(std::queue,front,pop)
OUTPUT2(std::stack,top,pop)
OUTPUT2(std::priority_queue,top,pop)
#undef OUTPUT

const int debug = 1;
#endif // __DEBUG_HPP__

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
using pqmaximumtstart = priority_queue<T, vector<T>, less<T>>;



struct SegmentTree {
private:
    vl tree;
    int size;


    void clear_vertex(int v) {
        tree[v] = INT_MAX;
    }

    void accumulate(int v_left, int v_right) {
        tree[v_left] = min(tree[v_right],  tree[v_left]) ;
    }


    void update_vertex(int v, int v_left, int v_right) {
        clear_vertex(v);
        accumulate(v, v_left);
        accumulate(v, v_right);
    }

    vi a ;
    void build_tree(int v, int left, int right){
        if (left + 1 == right) {
            tree[v] = a[left];
        }
        else {
            int mid = (left + right) / 2;
            int v_left = 2 * v;
            int v_right = v_left + 1;
            build_tree(v_left, left, mid);
            build_tree(v_right, mid, right);
            update_vertex(v, v_left, v_right);
        }
    }

    int index;
    ll value;
    void update_tree(int v, int left, int right){
        if (left + 1 == right) {
            tree[v] = value;
        }
        else {
            int mid = (left + right) / 2;
            int v_left = 2 * v, v_right = v_left + 1;
            if (index < mid) update_tree(v_left, left, mid);
            else update_tree(v_right, mid, right);

            update_vertex(v, v_left, v_right);
        }
    }

    int v_res = 0;
    int start, end;
    void get_tree(int v, int left, int right) {
        if (start <= left && right <= end) {
            accumulate(v_res, v);
        } else {
            int mid = (left + right) / 2;
            int v_left = v * 2, v_right = v_left + 1;
            if (start < mid) get_tree(v_left, left, mid);
            if (mid < end) get_tree(v_right, mid, right);
        }
    }

public:
    SegmentTree(int n): size(n), tree (n << 2, 0) {}

    void build(vi & _a){
        a = _a;
        build_tree(1, 0, size);
    }

    void update(int _index, ll _value) {
        index = _index;
        value = _value;

        update_tree(1, 0, size);
    }

    ll get(int _start, int _end){
        start = _start;
        end = _end + 1;

        clear_vertex(v_res);
        get_tree(1, 0, size);
        return tree[v_res];
    }
};


template<typename T>
T Max(T a)
{
    return a;
}

template<typename T, typename... Package>
T Max (T x, Package... p)
{
    return std::max(x, Max(p...));
}


template<typename T>
T Min(T a)
{
    return a;
}

template<typename T, typename... Package>
T Min (T x, Package... p)
{
    return std::min(x, Min(p...));
}

template<typename C> void reuniq(C& c) { c.erase(unique(all(c)), end(c));}



void setmax(auto &x, auto y) { if (x < y) x = y; }

void setmin(auto &x, auto y) { if (x > y) x = y; }

ll binpow(ll a, ll n, ll mod) {
    ll res = 1 % mod;
    while (n > 0) {
        if (n % 2 == 1)
            res = res * a % mod;
        a = a * a % mod;
        n >>= 1;
    }
    return res % mod;
}

ll binpow(ll a, ll n) {
    ll res = 1;
    while (n > 0) {
        if (n % 2 == 1)
            res = res * a ;
        a = a * a ;
        n >>= 1;
    }
    return res ;
}



long long mysqrt (long long x) {
    long long ans = 0;
    for (ll k = 1LL << 30; k != 0; k /= 2) {
        if ((ans + k) * (ans + k) <= x) {
            ans += k;
        }
    }
    return ans;
}

__int128 mysqrt (__int128 x) {
    __int128 ans = 0;
    __int128 one = 1;
    for (__int128 k = one << 63; k != 0; k /= 2) {
        if ((ans + k) * (ans + k) <= x) {
            ans += k;
        }
    }
    return ans;
}


string bin(auto x) {
    auto s = bitset<sizeof(x)*8>(x).to_string();
    return s.substr(min(s.size(),s.find('1')));
}


// a^(-1) = binpow(a, mod - 2, mod)

ll perm(ll n, ll k){
    ll answ = 1;
    ll dif = n - k;
    for (int i = 1; i < k + 1; ++i) {
        answ += answ * dif / i;
    }
    return answ;
}

ll myceil(ll a, ll b){
    return (a + b - 1) / b;
}

__int128 read() {
    __int128 x = 0, f = 1;
    char ch = getchar();
    while (ch < '0' || ch > '9') {
        if (ch == '-') f = -1;
        ch = getchar();
    }
    while (ch >= '0' && ch <= '9') {
        x = x * 10 + ch - '0';
        ch = getchar();
    }
    return x * f;
}
void print(__int128 x) {
    if (x < 0) {
        putchar('-');
        x = -x;
    }
    if (x > 9) print(x / 10);
    putchar(x % 10 + '0');
}


void printvector(vvi &v){
    for (int i = 0; i < isz(v); ++i) {
        for (int j = 0; j < isz(v[i]); ++j) {
            cout << v[i][j] << ' ';
        }
        nline;
    }
}


struct comp{
    bool operator ()(const int& lhs, const int& rhs) const {
        return lhs > rhs;
    }
};



i32 main() {
    //  freopen("input.txt", "rt", stdin);
    //  freopen("output.txt", "wt", stdout);

     cin.tie(nullptr); ios_base::sync_with_stdio(false);

     int t; cin >> t; while(t--) {
         int n; cin >> n;
         str s; cin >> s;
         bool startclosed = false;
         bool startopen=false;
         int cnt = 0;
         int lastdelete = -1;
         // () / ((- del
         // ).....( - del
        for (int i = 0; i < n; ++i) {
            // psp
            if (s[i] == '(') {
                if (i == n - 1) break;
                cnt++;
                lastdelete = i + 1;
                i = i + 1;
            } else {
                // pal
                int j = i + 1;
                while(j < n && s[j] != ')') j++;
                if (j == n) break;
                cnt++;
                lastdelete = j;
                i = j;
            }
        }
        cout << cnt << ' ' << n-1-lastdelete << en;
     }
}
