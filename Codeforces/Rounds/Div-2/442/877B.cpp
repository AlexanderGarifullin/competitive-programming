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
     string s;
     cin >> s;
     vi prefix_a(s.size()+1);
    for (int i = 1; i <= s.size(); ++i) {
        prefix_a[i] = prefix_a[i-1] +  (s[i-1] == 'a');
    }
    int mx = -1;
    for (int i = 0; i < s.size(); ++i) {
        for (int j = i ; j < s.size()+1; ++j) {
            mx = max(mx, prefix_a[i] + prefix_a[s.size()] - prefix_a[j] + j - i  - (prefix_a[j] - prefix_a[i]));
        }
    }
    cout << mx;
}
