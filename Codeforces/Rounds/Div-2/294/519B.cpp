#include <bits/stdc++.h>
#define en '\n'
#define f first
//#define s second
#define mp make_pair
#define  sz(x) int((x).size())
#define ins insert
#define pb push_back
#define eb emplace_back
#define ft front()
#define bk back()
#define bg(x) begin(x)
#define all(x) bg(x), end(x)
#define sor(x) sort(all(x))

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


int main()
{
     ios_base::sync_with_stdio(false);
     cin.tie(nullptr);
     int n;
     cin >> n;
     ll s1 = 0;
    for (int i = 0; i < n; ++i) {
        int x;
        cin >> x;
        s1 += x;
    }
    ll t =s1;
    for (int i = 0; i < n-1; ++i) {
        int x;
        cin >> x;
        s1 -= x;
    }
    cout << s1 << en;
    t -= s1;
    for (int i = 0; i < n-2; ++i) {
        int x;
        cin >> x;
        t -= x;
    }
        cout << t << en;
}
