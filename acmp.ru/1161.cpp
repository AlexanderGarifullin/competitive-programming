#include <bits/stdc++.h>

// #pragma GCC target ("avx2")
// #pragma GCC optimize("O3")
// #pragma GCC optimize("unroll-loops")


#define en '\n'
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
#define cmo cout << -1 << en
#define ifyn(x) x ? cyes : cno
#define nline cout << en
#define popcount __builtin_popcount
#define popcountll __builtin_popcountll
#define uid(a, b) uniform_int_distribution<int>(a, b)(rng)
#define forn(i, n) for (int i = 0; i < int(n); ++i)

template <typename T>
std::ostream& operator<<(std::ostream& os, const std::vector<T>& vec) {
    os << "[";
    for (size_t i = 0; i < vec.size(); ++i) {
        os << vec[i];
        if (i != vec.size() - 1) {
            os << ", ";
        }
    }
    return os << "]";
}

template <typename T>
std::ostream& operator<<(std::ostream& os, const std::set<T>& s) {
    os << "{";
    for (auto it = s.begin(); it != s.end(); ++it) {
        if (it != s.begin()) {
            os << ", ";
        }
        os << *it;
    }
    return os << "}";
}

template <typename T>
std::ostream& operator<<(std::ostream& os, const std::multiset<T>& ms) {
    os << "{";
    for (auto it = ms.begin(); it != ms.end(); ++it) {
        if (it != ms.begin()) {
            os << ", ";
        }
        os << *it;
    }
    return os << "}";
}

template <typename K, typename V>
std::ostream& operator<<(std::ostream& os, const std::map<K, V>& m) {
    os << "{";
    for (auto it = m.begin(); it != m.end(); ++it) {
        if (it != m.begin()) {
            os << ", ";
        }
        os << it->first << ": " << it->second;
    }
    return os << "}";
}

template <typename T>
std::ostream& operator<<(std::ostream& os, const std::list<T>& lst) {
    os << "[";
    for (auto it = lst.begin(); it != lst.end(); ++it) {
        if (it != lst.begin()) {
            os << ", ";
        }
        os << *it;
    }
    return os << "]";
}

// Перегрузка для последнего аргумента
void print_args(const char* names) {
    std::cerr << names << "\n";
}

// Шаблонная функция для вывода аргументов
template <typename T, typename... Args>
void print_args(const char* names, const T& arg, const Args&... args) {
    const char* comma = strchr(names, ',');
    if (comma) {
        std::cerr.write(names, comma - names) << " = " << arg << ", ";
        print_args(comma + 1, args...);
    } else {
        std::cerr << names << " = " << arg << "\n";
    }
}

// Макрос для вывода переменных
#define watch(...) if (debug) { print_args(#__VA_ARGS__, __VA_ARGS__); }
const int debug = 1;

using namespace std;

//#include <ext/pb_ds/assoc_container.hpp>
//#include <ext/pb_ds/tree_policy.hpp>
//using namespace __gnu_pbds;
//template<typename T>
//using ordered_set = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;
//template<typename T>
//using ordered_multiset = tree<T, null_type, less_equal<T>, rb_tree_tag, tree_order_statistics_node_update> ;


//const long double pi = 3.141592653589793238462643383279;

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

vector<int> z_function (string s) {
    int n = (int) s.size();
    vector<int> z(n, 0);
    int l = 0, r = 0;
    for (int i = 1; i < n; i++) {
        if (i <= r)
            z[i] = min(r - i + 1, z[i - l]);
        while (i + z[i] < n && s[z[i]] == s[i + z[i]])
            z[i]++;
        if (i + z[i] - 1 > r) {
            r = i + z[i] - 1;
            l = i;
        }
    }
    return z;
}

void solve() {
    str s; cin >> s;
    auto v = z_function(s);
    fout(v);
}

void multiSolve() {
    int t_; cin >> t_; while(t_--) {
        solve();
    }
}

void brute() {

}

void trash() {

}

i32 main(){

#ifdef DEBUG
    freopen("input.txt", "rt", stdin);
    freopen("output.txt", "wt", stdout);
#endif

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
//    cout.setf(ios::fixed); cout.precision(20);

//    trash();
//     brute();
    solve();
//    multiSolve();
}

