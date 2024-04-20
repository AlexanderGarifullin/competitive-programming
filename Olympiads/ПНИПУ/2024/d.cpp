// Лимит времени 2000/4000/4000/4000 мс. Лимит памяти 65000/65000/65000/65000 Кб.

// Наш знакомый программист Вася нашел одну занимательную задачу. Он обнаружил, что натуральное число называется«переменчивым», если в нем каждая цифра не совпадает с ближайшими соседними цифрами, но отличается от них не более, чем на 2. Например, «переменчивым» является число 3424576.
// Васе требуется вычислить по модулю 1012количество N-разрядных «переменчивых» чисел для произвольного заданного N. Естественно Вася знает, что лень это двигатель прогресса, и чтобы не считать каждый раз руками он всегда пишет программы. Помогите ему.
// Формат входных данных
// В единственной строке записано натуральное число N, не превосходящее 50.
// Формат выходных данных
// В результате должно быть записано количество различных N-разрядных «переменчивых» чисел, вычисленное по модулю1012.

// Ввод 1	Ввод 2
// 1	2
// Вывод 1	Вывод 2
// 9	32

// Пояснения ко второму примеру: существует ровно 32 двузначных «переменчивых» числа – это числа 10, 12, 13, 20, 21, 23, 24, 31, 32, 34, 35, 42, 43, 45, 46, 53, 54, 56, 57, 64, 65, 67, 68, 75, 76, 78, 79, 86, 87, 89, 97, 98.

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

#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;
template<typename T>
using ordered_set = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;

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

i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20)


    int n; cin >> n;
    ll mod = 1e12;
    vvl dp(n+1, vl(10));
    for (int i = 1; i < 10; ++i) {
        dp[1][i] = 1;
    }
    for (int i = 2; i <= n; ++i) {
        for (int j = 0; j < 10; ++j) {
            int l = max(0, j - 2);
            int r = min(9, j+2);
            if (j == 0) {
                l = max(l, 1);
            }
            for (int k = l; k <= r; ++k) {
                if (k == j) continue;
                dp[i][j] += dp[i-1][k];
                dp[i][j] %= mod;
            }
        }
    }
    ll s = 0;
    for (int i = 0; i < 10; ++i) {
        s += dp[n][i];
        s %= mod;
    }
    cout << s % mod;
}
