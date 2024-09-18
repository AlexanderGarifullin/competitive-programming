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
#define fin(x) for (auto &it: x) cin >> it
#define fout(x) for (auto &it: x) cout << it << ' '; cout << en;
#define cno cout << "NO" << en;
#define cyes cout << "YES" << en;
#define PI acos(-1.0L)

#define watch(x) std::cout << #x << " = " << x << std::endl;
#define debug(...) out (#__VA_ARGS__, __VA_ARGS__)



using namespace std;



using ll =  long long;
using ld  = long double;
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

using vvi = V<vi>;
using vvl = V<vl>;
using vvpii = V<vpii>;
using vvpll = V<vpll>;


using vvvi = V<vvi>;
using vvvl = V<vvl>;

using ull = unsigned long long;

template<typename T>
using PriorityQueue = priority_queue<T, vector<T>, greater<T>>;


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

// Удаление повторяющихся элементов в отсортированном массиве. 1 2 2 3 3 4 6 = 1 2 3 4 6.
template<typename C> void reuniq(C& c) { c.erase(unique(all(c)), end(c)); }

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

const int mod = (int)1e9 + 7;


int main() {
  //  freopen("dijkstra.in", "rt", stdin);
  //  freopen("dijkstra.out", "wt", stdout);

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    //timer.start();

    int nVert, nEdges;
    cin >> nVert >> nEdges;
    vvpii adj(1+nVert);
    for (int i = 0; i < nEdges; ++i) {
        int u, v, cost; cin >>u >> v >> cost;
        adj[u].push_back({v, cost});
        adj[v].push_back({u,cost});
    }
    const ll INF = 1e18;
    vector<ll> dist(1+nVert, INF);
    vector<int>prev(1+nVert);
    PriorityQueue<tuple<ll,int,int>> pq;
    // distance, curr vertex, prev vertex.
    pq.push({0,1,0});
    while(!pq.empty()){
        auto [currDist, currVert, prevVert] = pq.top();
        pq.pop();
        if (dist[currVert] <= currDist) continue;
        dist[currVert] = currDist;
        prev[currVert] = prevVert;
        for (auto &[nextVert, cost] : adj[currVert]){
            pq.push({currDist + cost, nextVert, currVert});
        }
    }

    if (dist[nVert] == INF)
        cout << -1 << en;
    else
    {
        vi answ;
        int currVert = nVert;
        while(currVert != 0){
            answ.push_back(currVert);
            currVert = prev[currVert];
        }
        reverse(all(answ));
        for (auto & it: answ)
            cout << it << ' ';
        cout << en;
    }


    //timer.finish();
    //cerr << en << string(20,'-') << en <<"runtime = " << timer() << " ms" << en;
    return 0;
}
