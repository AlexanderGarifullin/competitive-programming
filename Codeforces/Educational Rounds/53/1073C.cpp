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

template<typename T>
using pqsmalleststart = priority_queue<T, vector<T>, greater<T>>;
template<typename T>
using pqmaximumstart = priority_queue<T, vector<T>, less<T>>;



//struct SegmentTree {
//private:
//    vl tree;
//    int size;
//
//
//    void clear_vertex(int v) {
//        tree[v] = INT_MAX;
//    }
//
//    void accumulate(int v_left, int v_right) {
//        tree[v_left] = min(tree[v_right],  tree[v_left]) ;
//    }
//
//
//    void update_vertex(int v, int v_left, int v_right) {
//        clear_vertex(v);
//        accumulate(v, v_left);
//        accumulate(v, v_right);
//    }
//
//    vi a ;
//    void build_tree(int v, int left, int right){
//        if (left + 1 == right) {
//            tree[v] = a[left];
//        }
//        else {
//            int mid = (left + right) / 2;
//            int v_left = 2 * v;
//            int v_right = v_left + 1;
//            build_tree(v_left, left, mid);
//            build_tree(v_right, mid, right);
//            update_vertex(v, v_left, v_right);
//        }
//    }
//
//    int index;
//    ll value;
//    void update_tree(int v, int left, int right){
//        if (left + 1 == right) {
//            tree[v] = value;
//        }
//        else {
//            int mid = (left + right) / 2;
//            int v_left = 2 * v, v_right = v_left + 1;
//            if (index < mid) update_tree(v_left, left, mid);
//            else update_tree(v_right, mid, right);
//
//            update_vertex(v, v_left, v_right);
//        }
//    }
//
//    int v_res = 0;
//    int start, end;
//    void get_tree(int v, int left, int right) {
//        if (start <= left && right <= end) {
//            accumulate(v_res, v);
//        } else {
//            int mid = (left + right) / 2;
//            int v_left = v * 2, v_right = v_left + 1;
//            if (start < mid) get_tree(v_left, left, mid);
//            if (mid < end) get_tree(v_right, mid, right);
//        }
//    }
//
//public:
//    SegmentTree(int n): size(n), tree (n << 2, 0) {}
//
//    void build(vi & _a){
//        a = _a;
//        build_tree(1, 0, size);
//    }
//
//    void update(int _index, ll _value) {
//        index = _index;
//        value = _value;
//
//        update_tree(1, 0, size);
//    }
//
//    ll get(int _start, int _end){
//        start = _start;
//        end = _end + 1;
//
//        clear_vertex(v_res);
//        get_tree(1, 0, size);
//        return tree[v_res];
//    }
//};


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
vector<int> to_z(string& s, bool flag = false) {
    vector<int> z(isz(s));
    if (flag) z[0] = isz(z);
    int l = 0, r = 1;
    for (int i = 1; i < isz(z); i++) {
        if (r > i) z[i] = min(z[i - l], r - i);
        while (z[i] + i < isz(z) && s[z[i]] == s[z[i] + i]) z[i]++;
        if (r < z[i] + i) { l = i; r = z[i] + i; }
    }
    return z;
}


// a^(-1) = binpow(a, mod - 2, mod)

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

// dot product
// |a||b|cos(a,b) = a.x * b.x + a.y * b.y

// cross product
// |a||b|sin(a,b) = a.x * b.y - a.y * b.x
// > 0 = left turn
// < 0 =right turn

// atan2(x,y)

void printvector(vvi &v){
    for (int i = 0; i < isz(v); ++i) {
        for (int j = 0; j < isz(v[i]); ++j) {
            cout << v[i][j] << ' ';
        }
        nline;
    }
}

void printvector(vvl &v){
    for (int i = 0; i < isz(v); ++i) {
        for (int j = 0; j < isz(v[i]); ++j) {
            cout << v[i][j] << ' ';
        }
        nline;
    }
}

bool in(int value, int l, int r) {
    return l <= value && value <= r;
}


struct comp{
    bool operator ()(const int& lhs, const int& rhs) const {
        return lhs > rhs;
    }
};


void stressGenerate(){
    // add new
    // ofstream testFile("test.txt". ios::app);
    // replace new
    ofstream testFile("test.txt");

    ll seed = chrono::steady_clock::now().time_since_epoch().count();
    // int seed = 1;

    mt19937 rnd(seed);

    testFile << "SEED = " << seed << en;

    int n = rnd()% 10;
    for (int i = 0; i < n; ++i) {
        testFile << i << en;
    }

    testFile.close();
}

void stupid(){
    ifstream testFile("test.txt");
    ofstream stupidFile("stupid.txt");
    str seed;
    getline(testFile, seed);
    stupidFile << seed << en;

    str s;
    while(getline(testFile, s)) {
        stupidFile << s + "X" + s << en;
    }

    testFile.close();
    stupidFile.close();
}

void smart(){
    ifstream testFile("test.txt");
    ofstream smartFile("smart.txt");
    str seed;
    getline(testFile, seed);
    smartFile << seed << en;

    str s;
    while(getline(testFile, s)) {
        smartFile << "@"+  s  << en;
    }

    testFile.close();
    smartFile.close();
}

void checker() {
    ifstream smartFile("smart.txt");
    ifstream stupidFile("stupid.txt");
    ofstream resultFile("results.txt");

    string smartLine, stupidLine;
    int testNumber = 1;

    while (std::getline(smartFile, smartLine) && std::getline(stupidFile, stupidLine)) {
        resultFile << smartLine << ' ' << stupidLine << en;
        resultFile << testNumber << en;
        testNumber++;
    }

    smartFile.close();
    stupidFile.close();
    resultFile.close();
}

void solve() {

}

void INIT() {

}

void slow() {

}

void brute() {

}

void svo(){
    int n; cin >> n;
    string s; cin >> s;
    int x,y; cin >> x >> y;

    function<bool(int,int,int,int,int)> can = [](int x1, int y1, int x2, int y2, int len) {
        int d = abs(x1-x2) + abs(y1-y2);
        if (d%2 != len %2) return false;
        return len >= d;
    };

    if (!can(0,0,x,y,n)) {
        cmo;
        return;
    }

    vpii pref(n);
    vpii suf(n);
    int cx=0,cy=0;
    for (int i = 0; i < n; ++i) {
        if (s[i] == 'U') cy++;
        else if (s[i] == 'D') cy--;
        else if (s[i] == 'L') cx--;
        else if (s[i] == 'R') cx++;
        pref[i] = make_pair(cx,cy);
    }

    cx=0;cy=0;
    for (int i = n-1; i >=0 ; --i) {
        if (s[i] == 'U') cy++;
        else if (s[i] == 'D') cy--;
        else if (s[i] == 'L') cx--;
        else if (s[i] == 'R') cx++;
        suf[i] = make_pair(cx, cy);
    }

    function<bool(int)> ok = [&](int len) {
        for (int start = 0, end = len-1; end < n; ++start, ++end) {
            int cx=0,cy=0;
            if (start>0) {
                cx = pref[start-1].first;
                cy = pref[start-1].second;
            }
            if (end<n-1) {
                cx += suf[end+1].first;
                cy += suf[end+1].second;
            }
            if (can(cx,cy,x,y,len)) return true;
        }
        return false;
    };

    int left= -1;
    int right =n;

    while(left+1<right) {
        int mid = (left+right)/2;
        if (ok(mid)) right = mid;
        else left = mid;
    }
    cout<<right;
}

void multiSolve() {
    int t_; cin >> t_; while(t_--) {
        //INIT();
        svo();
    }
}

i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    //  cout.setf(ios::fixed); cout.precision(20);


    svo();
    //brute();
    //multiSolve();
}
