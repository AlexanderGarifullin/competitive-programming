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


bool solve()
{
    int a1,a2,a3,a4;
    cin >> a1 >> a2 >> a3 >> a4;
    if (a1 < a2 && a3 < a4 && a1 < a3 && a2 < a4)
        return true;
    else if (a3 < a1 && a4 < a2 && a3 < a4 && a1 < a2)
        return true;
    else if (a4 < a3 && a2 < a1 && a4 < a2 && a3 < a1)
        return true;
    else if (a2 < a4 && a1 < a3 && a2 < a1 && a4 < a3)
        return true;
        return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;
    while (t--)
    {
        if (solve())
        cout << "YES" << en;
        else
        cout << "NO" << en;
    }
}
