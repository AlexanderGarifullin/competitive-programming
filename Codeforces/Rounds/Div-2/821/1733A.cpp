#include <bits/stdc++.h>
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
#define sortlargetosmall(x) sort(all(x), greater<>())
#define sortsmalltolarge(x) sort(all(x), less<>())
#define fin(x) for (auto &it: x) cin >> it
#define fout(x) for (auto &it: x) cout << it << ' '; cout << en
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

class Timer {
    std::chrono::time_point<std::chrono::steady_clock> timePoint;
    size_t value;
public:
    void start() { timePoint = std::chrono::steady_clock::now(); }
    void finish() {
        auto curr = std::chrono::steady_clock::now();
        auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(curr - timePoint);
        value = elapsed.count();
    }
    size_t operator()() const { return value; }
};

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


// Шаблон на поиск максимума среди нескольких элементов.
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

// Шаблон на поиск минимума среди нескольких элементов.
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

// Удаление повторяющихся элементов в отсортированном массиве. 1 2 2 3 3 4 6 = 1 2 3 4 6.
template<typename C> void reuniq(C& c) { c.erase(unique(all(c)), end(c));}

Timer timer;


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

ll gcd(ll a,ll b){
    return b?gcd(b,a%b):a;
}

ll lcm(ll a,ll b){
    return a / gcd(a, b) * b;
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
string bin(auto x) {
    auto s = bitset<sizeof(x)*8>(x).to_string();
    return s.substr(min(s.size(),s.find('1')));
}

// Обратное число a^(-1) = a^(p-2)
// a^(-1) = binpow(a, mod - 2, mod)
// 1) q, q^2, ... , q^k, 0, 0 ... - Необратимый элемент. binpow(q, 10^18, mod) == 0. q^(k+1) % mod == 0. Нельзя найти q^(-1)
// 2) q, q^2, ... , q^k, 1, q,  q^2, ... , q^k, ... Существует обратный элемент. Тогда => a^(-1) = a^(p-2) (mod p)
// a^(-1) = binpow(a, mod - 2, mod) - Если mod - простое.
// a^(-1) = binpow(a, f(m) - 1, m) - В общем случае (Если m не простое).  f(m) - Функция Эйлера - кол-во чисел от 1 до m - 1, где их НОД с m = 1
// Функция эйлера: f(p1^k1, p2^k2, ... , pn^kn) =  (p1^k1 - p1^(k1-1)) * (p2^k2 - p2^(k2-1)) (pn^kn - pn^(kn-1));


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

const int mod = (int)1e9 + 7;
const ll INF = (ll)1e18+1;

const ll NMAX = 2e6;





struct comp{
    bool operator ()(const int& lhs, const int& rhs) const {
        return lhs < rhs;
    }
};

ll solve(){

    return 1;
}



void voidsolve(){

}

void test(){

}



i32 main() {

    //Timer timer;
    //timer.start();


    //  freopen("input.txt", "rt", stdin);
    //  freopen("output.txt", "wt", stdout);

    // test();
     cin.tie(0); ios_base::sync_with_stdio(false);
     int t; cin >> t;
     while(t--){
         int n, k; cin >> n >> k;
         vi v( n+ 1);
         forn(1, n + 1){
             cin >> v[i];
         }
            ll ans = 0;
         for (int i = 1; i <= k; ++i) {
             for (int j = i; j <= n; j+= k) {
                 if (v[j] > v[i]){
                     swap(v[i],v[j]);
                 }
             }
             ans += v[i];
         }

         cout << ans << en;
     }

    //  cerr << en << string(20,'-') << en <<"runtime = " << timer() << " ms" << en;
    return 0;
}
