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
 
 
 
void fill_factorials(vector<long long> &fact){
    fact[0] = 1;
    for (int i = 1; i < int(fact.size()); ++i) {
        fact[i] = fact[i-1] * i;
    }
}
 
// n - size of permutation
// k - number of permutation (need to minus 1 from k because numeration start from 0
// factorials - calculated factorials in advance
vector<int> get_permitation(int n, long long k, vector<long long> &factorials) {
    vector<int> perm(n);
    vector<bool> use(n + 1);
    k--;
    for (int i = 0; i < n; ++i) {
        int blockNum = k / factorials[n - 1 - i] + 1;
        int pos = 0;
        for (int j = 1; j < use.size(); ++j) {
            if (!use[j]) {
                pos++;
                if (pos == blockNum) {
                    use[j] = true;
                    perm[i] =j;
                    k %= factorials[n - 1 - i];
                    break;
                }
            }
        }
    }
    return perm;
}
 
i32 main() {
    //  freopen("success.in", "rt", stdin);
    //  freopen("success.out", "wt", stdout);
 
    cin.tie(nullptr); ios_base::sync_with_stdio(false);
 
    int n; cin >> n;
    int k; cin >> k;
 
    vector<long long> factorials(n);
    fill_factorials(factorials);
 
    vector<int> perm = get_permitation(n, k, factorials);
 
    for (const int &x : perm) cout << x << ' ';
 
    return 0;
}
