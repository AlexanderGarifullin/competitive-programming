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
        for (size_t col = 0; col < v.size(); ++col) {
            for (size_t k = 0; k < v.size(); ++k) {
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

void checker(){
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

i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20)

    int t_; cin >> t_; while(t_--) {
        str s; cin >> s;
        vi v(6);
        for (int i = 0; i < 6; ++i) {
            v[i] = s[i] - '0';
        }
        ifyn(v[0]+v[1] + v[2] == v[3] + v[4] + v[5]);
    }
}
