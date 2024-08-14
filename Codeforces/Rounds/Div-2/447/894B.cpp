#include <bits/stdc++.h>
#define en '\n'
#define f first
//#define s second
#define mp make_pair
#define  isz(x) int((x).size())
#define ins insert
#define pb push_back
#define eb emplace_back
#define ft front()
#define bk back()
#define bg(x) begin(x)
#define all(x) bg(x), end(x)
#define sor(x) sort(all(x))
#define for_cin(x) for (auto &it: x) cin >> it
#define for_out(x) for (auto &it: x) cout << it << ' '; cout << en;
#define cNO cout << "NO" << en;
#define cYES cout << "YES" << en;

#define watch(x) std::cout << #x << " = " << x << std::endl;
#define debug(...) out (#__VA_ARGS__, __VA_ARGS__)



using namespace std;

using ll =  long long;
using ld  = long double;
using str = string;
// pairs
using pi = pair<int,int>;
using pl = pair<ll,ll>;

template<typename T>
using V = vector<T>;
using vi = V<int>;
using vl = V<ll>;
using vpi = V<pi>;
using vpl = V<pl>;
using vb = V<bool>;

using vvi = V<vi>;

using ull = unsigned long long;

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


string to_string(const string& s) {
    return '"' + s + '"';
}

string to_string(const char* s) {
    return to_string((string)s);
}

string to_string(bool b) {
    return (b ? "true" : "false");
}

template <typename A>
string to_string(A v){
    return empty(v) ? "{}": accumulate(++cbegin(v), cend(v), "{ " + to_string(*cbegin(v)),
                                       [](auto l, auto r){return l+", "+ to_string(r);}) + " }";
}

void out(string) { cerr << endl;}

template<typename Head, typename ... Tail>
void out(string format, Head H, Tail... T){
    if (format.find(',') == string::npos) {
        cerr << format << " ::";
    } else {
        cerr << string(format.begin(), format.begin() + format.find(',')) << " ::";
        format = string(format.begin() + format.find(',') + 2,format.end());
    }
    cerr << " " << to_string(H) << endl;
    out (format, T...);
}

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

Timer timer;

ll binpow (ll a, ll n) {
    if (n == 0)
        return 1ll;
    if (a == 1) return 1;
    if (n % 2 == 1)
        return binpow (a, n-1) * a;
    else {
        ll b = binpow (a, n/2);
        return b * b;
    }
}

ll binpow (ll a, ll n, ll MOD) {
    if (n == 0)
        return 1ll;
    if (a == 1) return 1;
    if (n % 2 == 1)
        return binpow (a % MOD, n-1, MOD) * (a% MOD) % MOD;
    else {
        ll b = binpow (a % MOD, n/2, MOD) % MOD;
        return (b % MOD) * (b% MOD) % MOD;
    }
}

ull binpow (ull a, ull n, ull MOD) {
    if (n == 0)
        return 1Ull;
    if (a == 1) return 1;
    if (n % 2 == 1)
        return binpow (a % MOD, n-1, MOD) * (a% MOD) % MOD;
    else {
        ll b = binpow (a % MOD, n/2, MOD) % MOD;
        return (b % MOD) * (b% MOD) % MOD;
    }
}

ll binpow2 (ll a, ll n) {
    ll res = 1;
    if (a == 1) return 1;
    while (n) {
        if (n & 1)
            res *= a;
        a *= a;
        n >>= 1;
    }
    return res;
}


ll binpow2 (ll a, ll n, ll MOD) {
    ll res = 1;
    if (a == 1) return 1;
    while (n) {
        if (n & 1) {
            res *= a % MOD;
            res %= MOD;
        }
        a *= a % MOD;
        a %= MOD;
        n >>= 1;
    }
    return res  %= MOD ;
}

ll gcd(ll a,ll b){
    return b?gcd(b,a%b):a;
}




int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    timer.start();

    ull n, m, k; cin >> n >> m >> k;
    const ull  mod = 1000000007;
    if ((n%2) != (m%2) && k == -1) {
        cout << 0 << en;
        return 0;
    }
    cout << binpow(binpow(2,n-1, mod), m-1, mod) << en;

    timer.finish();
    cerr << en << string(20,'-') << en <<"runtime = " << timer() << " ms" << en;
    return 0;
}
