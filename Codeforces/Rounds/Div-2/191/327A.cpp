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
    ll res = 1;
    while (n) {
        if (n & 1)
            res *= a;
        a *= a;
        n >>= 1;
    }
    return res;
}

ll gcd(ll a,ll b){
    return b?gcd(b,a%b):a;
}




int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    timer.start();

    int n; cin >> n;
    vi prefix_sum_1(n+1);
    for (int i = 1; i <= n; ++i) {
        int x; cin >> x;
        prefix_sum_1[i] = prefix_sum_1[i-1] + x;
    }
    int ans = 0;
    for (int l = 1; l < n+1; ++l) {
        for (int r = l; r < n+1; ++r) {
            int curr = prefix_sum_1[l-1] +  r - l + 1 - prefix_sum_1[r] + prefix_sum_1[l-1]  + prefix_sum_1[n] - prefix_sum_1[r] ;
            ans = max(ans,curr);
        }
    }
    cout << ans << en;
    timer.finish();
    cerr << en << string(20,'-') << en <<"runtime = " << timer() << " ms" << en;
    return 0;
}
