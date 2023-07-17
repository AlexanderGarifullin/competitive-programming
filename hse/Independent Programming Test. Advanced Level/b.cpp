//2ms
//296.00Kb
#include "bits/stdc++.h"
#define  en '\n'


using namespace std;
using ll = long long;
using vi = vector<int>;

int main(){
    cin.tie(0);
    ios_base::sync_with_stdio(false);

    int n; cin >> n;
    int cnt = 0; cin >> cnt;
    int ans = 0;
    ans = cnt;
    for (int i = 0; i < n - 2; ++i) {
        int a, b; cin >> a >> b;
        int dif = b -a;
        cnt += dif;
        ans = max(ans, cnt);

    }
    int c; cin >> c;
    cnt -=c;
    cout << ans ;
    return 0;
}
