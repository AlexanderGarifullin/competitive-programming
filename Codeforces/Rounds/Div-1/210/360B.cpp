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
//using namespace __gnu_pbds;
//template<typename T>
//using ordered_set = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;

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


mt19937 rng(chrono::steady_clock::now().time_since_epoch().count());

template<typename T>
using pqsmalleststart = priority_queue<T, vector<T>, greater<T>>;
template<typename T>
using pqmaximumstart = priority_queue<T, vector<T>, less<T>>;


template<typename C> void reuniq(C& c) { c.erase(unique(all(c)), end(c));}


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

ull binpow(ull a, ull n, ull mod) {
    ull res = 1 % mod;
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

vector<vector<long long>> mul(const vector<vector<long long>> &v1, const vector<vector<long long>> &v2) {
    vector<vector<long long>> v(v1.size(), vector<long long>(v1[0].size()));

    for (size_t row = 0; row < v.size(); ++row) {
        for (size_t col = 0; col < v.size(); ++col) {
            for (size_t k = 0; k < v.size(); ++k) {
                v[row][col] += v1[row][k] * v2[k][col];
            }
        }
    }

    return v;
}

vector<vector<long long>> binpow(vector<vector<long long>> v, const int step){
    if (step == 0) {
        for (size_t i = 0; i < v.size(); ++i) {
            for (size_t j = 0; j < v.size(); ++j) {
                v[i][j] = 0;
                if (i == j) v[i][j] = 1;
            }
        }
        return v;
    }
    else if (step == 1) {
        return v;
    } else {
        if (step % 2) {
            return mul(v, binpow(v, step - 1));
        } else {
            vector<vector<long long>> v2 = binpow(v, step/2);
            return mul(v2, v2);
        }
    }
}


vector<vector<long long>> mul(const vector<vector<long long>> &v1, const vector<vector<long long>> &v2, const int mod) {
    vector<vector<long long>> v(v1.size(), vector<long long>(v1[0].size()));

    for (size_t row = 0; row < v.size(); ++row) {
        for (size_t k = 0; k < v.size(); ++k)  {
            for (size_t col = 0; col < v.size(); ++col) {
                v[row][col] += v1[row][k] * v2[k][col] % mod;
                v[row][col] %= mod;
            }
        }
    }

    return v;
}

vector<vector<long long>> binpow(vector<vector<long long>> v, const int step, const int mod){
    if (step == 0) {
        for (size_t i = 0; i < v.size(); ++i) {
            for (size_t j = 0; j < v.size(); ++j) {
                v[i][j] = 0;
                if (i == j) v[i][j] = 1;
            }
        }
        return v;
    }
    else if (step == 1) {
        return v;
    } else {
        if (step % 2) {
            return mul(v, binpow(v, step - 1, mod), mod);
        } else {
            vector<vector<long long>> v2 = binpow(v, step/2, mod);
            return mul(v2, v2, mod);
        }
    }
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

ll perm(ll n, ll k){
    ll answ = 1;
    ll dif = n - k;
    for (ll i = 1; i < k + 1; ++i) {
        answ += answ * dif / i;
    }
    return answ;
}
ull perm(ull n, ull k){
    ull answ = 1;
    ull dif = n - k;
    for (ull i = 1; i < k + 1; ++i) {
        answ += answ * dif / i;
    }
    return answ;
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

// dot product
// |a||b|cos(a,b) = a.x * b.x + a.y * b.y

// cross product
// |a||b|sin(a,b) = a.x * b.y - a.y * b.x
// > 0 = left turn
// < 0 =right turn

// atan2(x,y)

//struct comp{
//    bool operator ()(const int& lhs, const int& rhs) const {
//        return isz(can[lhs]) < isz(can[rhs]);
//    }
//};

vi dp, a;
int n,k;

bool ok(int x)  {
    int res = 0;
    for (int i = 0; i < n; ++i) {
        dp[i]=1;
        for (int j = 0; j < i; ++j) {
            if (abs(a[i] - a[j]) <= (i - j) * 1ll * x) {
                dp[i]=max(dp[i], 1+dp[j]);
            }
        }
        res=max(dp[i],res);
    }
    return res >= n - k;
}


void solve() {
    cin >> n >> k;
    dp.resize(n);
    a.resize(n);
    fin(a);
    int l = -1;
    int r = 2e9 + 10;
    while(l + 1 < r) {
        int m = (0ll + l + r)/2;
        if (ok(m)) r = m;
        else l = m;
    }
    cout << r << en;
}

void multiSolve() {
    int t_; cin >> t_; while (t_--) {
        solve();
    }
}

void brute() {

}

i32 main(){

#ifdef DEBUG
    freopen("input.txt", "rt", stdin);
    freopen("output.txt", "wt", stdout);
#endif

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
//      cout.setf(ios::fixed); cout.precision(20);

//     brute();
    solve();
//    multiSolve();

}
