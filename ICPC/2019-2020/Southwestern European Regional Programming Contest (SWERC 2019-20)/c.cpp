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

vi v(1e6+3);

int main()
{
   ios_base::sync_with_stdio(false);
   cin.tie(nullptr);
      int n;
      cin >> n;
    for (int i = 0; i < n; ++i) {
        string s;
        cin >> s;
        if (s.length() > 6 || s[0] == '-')
            continue;
        v[stoi(s)] = 1;
    }
    for (int i = 0; i <= n; ++i) {
        if (!v[i])
        {
            cout << i << en;
            break;
        }
    }
}
