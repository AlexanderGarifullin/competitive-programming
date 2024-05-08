#include <bits/stdc++.h>

// #pragma GCC target ("avx2")
// #pragma GCC optimize("O3")
// #pragma GCC optimize("unroll-loops")


#define en '\n'
//#define mp make_pair
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
#define forn(start, end) for(int i = start; i < end; ++i)
#define cmo cout << -1 << en
#define ifyn(x) x ? cyes : cno
#define nline cout << en



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

template<typename C> void reuniq(C& c) { c.erase(unique(all(c)), end(c));}

ll solve(int n, vi p) {
    vi even,odd;
    vb use(n+1);
    for (int i = 1; i <= n; ++i) {
        use[p[i]] = true;
    }
    for (int i = 1; i <=n; ++i) {
        if (use[i]) continue;
        if (i%2) odd.pb(i);
        else even.pb(i);
    }

    if (isz(odd) + isz(even) == n) {
        if (n == 1) return 0;
        else return 1;
    }


    int start = -1, end = -1;
    for (int j = 1; j <= n; ++j) {
        if (p[j] != 0) {
            if (start == -1) start = j;
            end = j;
        }
    }
    int cntZeroBetween = 0;
    vpii equal, notEqual;
    int lastStart = -1;
    for (int j = start; j <= end; ++j) {
        if (p[j] == 0) {
            cntZeroBetween++;
        } else {
            if (cntZeroBetween) {
                if (p[lastStart] % 2 == p[j] % 2) {
                    equal.pb({lastStart, j});
                } else {
                    notEqual.pb({lastStart, j});
                }
            }
            cntZeroBetween = 0;
            lastStart = j;
        }
    }
    sort(equal.begin(), equal.end(),[](pii p1, pii p2){
        auto [l1,r1] = p1;
        auto [l2,r2] =p2;
        return r1 - l1 < r2 - l2;
    });
    sort(notEqual.begin(), notEqual.end(),[](pii p1, pii p2){
        auto [l1,r1] = p1;
        auto [l2,r2] =p2;
        return r1 - l1 < r2 - l2;
    });
    for (auto [l,r] : equal) {
        int len = (r-1) - (l+1) + 1;
        if (p[l] % 2) {
            if (isz(odd) < len) continue;
            for (int i = l+1; i < r; ++i) {
                p[i] = odd.back();
                odd.pop_back();
            }
        } else {
            if (isz(even) < len) continue;
            for (int i = l+1; i < r; ++i) {
                p[i] = even.back();
                even.pop_back();
            }
        }
    }


    if (p[1] == 0 && p[n] == 0)
    {
        int bord1=0,bord2=0;
        for (int i = 1; i <= n; ++i) {
            if (p[i] == 0) continue;
            bord1=i;
            break;
        }
        for (int i = n; i >=1; --i) {
            if (p[i] == 0) continue;
            bord2 =i;
            break;
        }
        if (p[bord1] %2 == p[bord2]%2) {
            if (bord1 - 1 <= n - bord2){
                for (int i = bord1-1; i >= 1; --i) {
                    if (p[bord1] % 2 == 0) {
                        if (!even.empty()) {
                            p[i] = even.back(); even.pop_back();
                        }
                        else if (!odd.empty()) {
                            p[i] = odd.back(); odd.pop_back();
                        }
                    } else {
                        if (!odd.empty()) {
                            p[i] = odd.back(); odd.pop_back();
                        }
                        else if (!even.empty()) {
                            p[i] = even.back(); even.pop_back();
                        }
                    }
                }
                for (int i = bord2+1; i <= n; ++i) {
                    if (p[bord1] % 2 == 0) {
                        if (!even.empty()) {
                            p[i] = even.back(); even.pop_back();
                        }
                        else if (!odd.empty()) {
                            p[i] = odd.back(); odd.pop_back();
                        }
                    } else {
                        if (!odd.empty()) {
                            p[i] = odd.back(); odd.pop_back();
                        }
                        else if (!even.empty()) {
                            p[i] = even.back(); even.pop_back();
                        }
                    }
                }
            } else {
                for (int i = bord2+1; i <= n; ++i) {
                    if (p[bord1] % 2 == 0) {
                        if (!even.empty()) {
                            p[i] = even.back(); even.pop_back();
                        }
                        else if (!odd.empty()) {
                            p[i] = odd.back(); odd.pop_back();
                        }
                    } else {
                        if (!odd.empty()) {
                            p[i] = odd.back(); odd.pop_back();
                        }
                        else if (!even.empty()) {
                            p[i] = even.back(); even.pop_back();
                        }
                    }
                }
                for (int i = bord1-1; i >= 1; --i) {
                    if (p[bord1] % 2 == 0) {
                        if (!even.empty()) {
                            p[i] = even.back(); even.pop_back();
                        }
                        else if (!odd.empty()) {
                            p[i] = odd.back(); odd.pop_back();
                        }
                    } else {
                        if (!odd.empty()) {
                            p[i] = odd.back(); odd.pop_back();
                        }
                        else if (!even.empty()) {
                            p[i] = even.back(); even.pop_back();
                        }
                    }
                }
            }
        }
        else {
            for (int i = bord1-1; i >= 1; --i) {
                if (p[bord1] % 2 == 0) {
                    if (!even.empty()) {
                        p[i] = even.back(); even.pop_back();
                    }
                    else if (!odd.empty()) {
                        p[i] = odd.back(); odd.pop_back();
                    }
                } else {
                    if (!odd.empty()) {
                        p[i] = odd.back(); odd.pop_back();
                    }
                    else if (!even.empty()) {
                        p[i] = even.back(); even.pop_back();
                    }
                }
            }
            for (int i = bord2+1; i <= n; ++i) {
                if (p[bord2] % 2 == 0) {
                    if (!even.empty()) {
                        p[i] = even.back(); even.pop_back();
                    }
                    else if (!odd.empty()) {
                        p[i] = odd.back(); odd.pop_back();
                    }
                } else {
                    if (!odd.empty()) {
                        p[i] = odd.back(); odd.pop_back();
                    }
                    else if (!even.empty()) {
                        p[i] = even.back(); even.pop_back();
                    }
                }
            }
        }
    }
    else if (p[1] == 0) {
        int border = 0;
        for (int i = 1; i <= n; ++i) {
            if (p[i] == 0) continue;
            border = i;
            break;
        }
        for (int i = border - 1; i >=1 ; --i) {
            if (p[border] % 2) {
                if (!odd.empty()) {
                    p[i] = odd.back(); odd.pop_back();
                }
                else if (!even.empty()) {
                    p[i] =even.back(); even.pop_back();
                }
            } else {
                if (!even.empty()) {
                    p[i] =even.back(); even.pop_back();
                }
                else if (!odd.empty()) {
                    p[i] = odd.back(); odd.pop_back();
                }
            }
        }
    }
    else if (p[n] == 0) {
        int border = 0;
        for (int i = n; i >=1 ; --i) {
            if (p[i] == 0) continue;
            border = i;
            break;
        }
        for (int i = border+1; i <= n; ++i) {
            if (p[border] % 2) {
                if (!odd.empty()) {
                    p[i] = odd.back(); odd.pop_back();
                }
                else if (!even.empty()) {
                    p[i] =even.back(); even.pop_back();
                }
            } else {
                if (!even.empty()) {
                    p[i] = even.back(); even.pop_back();
                }
                else if (!odd.empty()) {
                    p[i] = odd.back(); odd.pop_back();
                }
            }
        }
    }

    // equal notEqual

    for (int i = 1; i <= n; ++i) {
        if (p[i] == 0){
            if (p[i-1] % 2 ==0) {
                if (!even.empty()) {
                    p[i] =even.back(); even.pop_back();
                }
                else if (!odd.empty()) {
                    p[i] = odd.back(); odd.pop_back();
                }
            } else {
                if (!odd.empty()) {
                    p[i] = odd.back(); odd.pop_back();
                }
                else if (!even.empty()) {
                    p[i] =even.back(); even.pop_back();
                }
            }
        }
    }
//    cout << string(20,'-') << en;
//    cout << "my p = ";
//    fout(p);
//    cout << string(20,'-') << en;
    int r = 0;
    for (int i = 2; i <= n; ++i) {
        if (p[i-1] % 2 != p[i] % 2) r++;
    }
    return r;
}

ll corr(int N, vi P){
    int ans = N;
    for (int z0 = 1; z0 <= 2; z0++) {
        for (int zN = 1; zN <= 2; zN++) {
            P[0] = z0;
            P[N+1] = zN;

            int numEven = N / 2;
            int numOdd = (N+1) / 2;
            for (int i = 1; i <= N; i++) {
                if (P[i]) {
                    if (P[i] % 2 == 0) numEven--;
                    else numOdd --;
                }
            }
            vector<int> evenLens;
            vector<int> oddLens;
            int cost = 0;
            for (int i = 0; i <= N;) {
                assert(P[i]);
                int j = i+1;
                while (!P[j]) j++;
                if (P[i] % 2 == P[j] % 2) {
                    if (j-i-1 >= 1) {
                        if (P[i] % 2 == 0) evenLens.push_back(j-i-1);
                        else oddLens.push_back(j-i-1);
                    }
                } else {
                    cost += 1;
                }
                i = j;
            }

            sort(evenLens.begin(), evenLens.end());
            reverse(evenLens.begin(), evenLens.end());
            while (!evenLens.empty() && evenLens.back() <= numEven) {
                numEven -= evenLens.back();
                evenLens.pop_back();
            }
            cost += 2 * int(evenLens.size());
            sort(oddLens.begin(), oddLens.end());
            reverse(oddLens.begin(), oddLens.end());
            while (!oddLens.empty() && oddLens.back() <= numOdd) {
                numOdd -= oddLens.back();
                oddLens.pop_back();
            }
            cost += 2 * int(oddLens.size());
            ans = min(ans, cost);
        }
    }
    return ans;
}

void brute(){
    ll seed = chrono::steady_clock::now().time_since_epoch().count();
    mt19937 rnd(seed);

    for (int len = 2; len <= 10; ++len) {
        for (int test = 0; test < 5; ++test) {
            vi v(len);
            for (int i = 0; i < len; ++i) {
                v[i]=i+1;
            }
            do {
                vi p(len+1);
                vi pp(len+1);
                for (int i = 1; i <= len; ++i) {
                    p[i]=v[i-1];
                    pp[i] = p[i];
                }
                for (int cntZero = 0; cntZero <= len; ++cntZero) {
                    function<void(int,int)> f = [&](int pos, int cntZ) {
                        if (pos > len) return;
                        int ost = len - pos;
                        if (ost >= cntZ) {
                            int my = solve(len, p);
                            vi p2 = p;
                            p2.pb(0);
                            int ans = corr(len, p2);
                            if (my != ans) {
                                cout << "MY = " << my << en;
                                cout << "Correct = " << ans << en;
                                for (int i = 1; i <= len; ++i) {
                                    cout << p[i] << ' ';
                                }
                                exit(0);
                            }
                            f(pos+1, cntZ);
                        }
                        p[pos] = 0;
                        int my = solve(len, p);
                        vi p2 = p;
                        p2.pb(0);
                        int ans = corr(len, p2);
                        if (my != ans) {
                            cout << "MY = " << my << en;
                            cout << "Correct = " << ans << en;
                            cout << "N = " << len << en;
                            for (int i = 1; i <= len; ++i) {
                                cout << p[i] << ' ';
                            }
                            exit(0);
                        }
                        f(pos+1, cntZ-1);
                        p[pos] = pp[pos];
                    };
                    f(1, cntZero);
                }
            } while(next_permutation(all(v)));
        }
    }
    cout << "OK" << en;
}

void solveTask(){
    int n; cin >> n;
    vi p(n+1);
    for (int i = 1; i <= n; ++i) {
        cin >> p[i];
    }
    int my = solve(n,p);
    cout << my;
}

i32 main() {
//    freopen("input.txt", "rt", stdin);
//    freopen("output.txt", "wt", stdout);

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    // cout.setf(ios::fixed); cout.precision(20);
    
    solveTask();
    //brute();
}
