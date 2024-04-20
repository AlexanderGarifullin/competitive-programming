// Лимит времени 2000/4000/4000/4000 мс. Лимит памяти 65000/65000/65000/65000 Кб.

// Вася работает с символьной строкой, составленной из заглавных латинских букв. Он пытается разбить ее на две подстроки – префикс и суффикс, – так чтобы количество различных букв в подстроках совпадало. Если такого разбиения нет, то ему необходимо выдать слово IMPOSSIBLE. Если же варианты такого разбиения существуют, то указать вариант с самым коротким префиксом. Помогите Васе составить эту программу.
// Формат входных данных
// В первой строке записано натуральное число N – длина заданной строки (N ≤ 105).
// Во второй строке записаны N заглавных латинских букв.
// Формат выходных данных
// Слово IMPOSSIBLE, если заданную строку нельзя разбить на префикс и суффикс с одинаковым количеством различных букв.
// Если же варианты разбиения существуют, то записать вариант с самым коротким префиксом. Запись должна содержать искомые префикс и суффикс, разделенные знаком «+».

// Ввод 1	Ввод 2
// 3
// HEY	5
// HELLO
// Вывод 1	Вывод 2
// IMPOSSIBLE	HE+LLO

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
    str s; cin >> s;
    vvi pref(n, vi(26));
    vvi suf(n, vi(26));
    vi cntPref(n), cntSuf(n);
    for (int i = 0; i < n; ++i) {
        int j = s[i] - 'A';
        if (i > 0) {
            cntPref[i] = cntPref[i-1];
            pref[i] = pref[i-1];
        }
        if (pref[i][j] == 0) cntPref[i]++;
        pref[i][j] = 1;
    }
    for (int i = n-1; i >=0; --i) {
        int j = s[i] - 'A';
        if (i < n-1) {
            cntSuf[i] = cntSuf[i+1];
            suf[i] = suf[i+1];
        }
        if (suf[i][j] == 0) {
            cntSuf[i]++;
        }
        suf[i][j] = 1;
    }
    for (int i = 0; i < n-1; ++i) {
        if (cntPref[i] == cntSuf[i+1]) {
            cout << s.substr(0, i+1) << "+" <<s.substr(i+1);
            return 0;
        }
    }
    cout << "IMPOSSIBLE";
}
