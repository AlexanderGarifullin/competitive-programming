#include <bits/stdc++.h>
#define en '\n'
#define f first
#define s second
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
#define debug(x) std::cout << __FUNCTION__ << ":" << __LINE__ << " " << #x << " = " << x <<  '\n'

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
//    ios_base::sync_with_stdio(false);
//    cin.tie(nullptr);
    int n;
    cin >> n;
    vl a(n);
    vl sum(1+n);
    for (int i = 0; i < n; ++i) {
        cin >> a[i];
        sum[i+1] = sum[i] + a[i];
    }
    set<ll> st;
    st.ins(0);
    st.ins(100);
    for (int i = 0; i < n; ++i) {
        for (int j = 1; j < 100 ; ++j) {
            ll x_min = a[i] * j / 100;
            ll x_max = a[i] * (j+1) / 100;
            while(x_min <= x_max)
            {
                ll mid = (x_min + x_max) / 2;
                ll sec_percentage = 100 * (sum[i] + mid) / sum[n];
                ll first_percentage = 100 * mid / a[i];
                if (first_percentage == j && sec_percentage == first_percentage)
                {
                    st.ins(j);
                    break;
                }
                if (first_percentage > j)
                {
                    break;
                }
                if (sec_percentage > j)
                {
                    x_max = mid - 1;
                }
                else 
                {
                    x_min =mid+1;
                }
            }
        }
    }
    for (auto &x: st)
        cout << x << en;

}
