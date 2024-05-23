#include "bits/stdc++.h"
#define  en '\n'
#define all(x) begin(x), end(x)
#define isz(s) int(s.size())
using namespace std;
using ll = long long;
using vi = vector<int>;
using pii = pair<int,int>;

int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);;



    string s; cin >> s;
    int ans = isz(s);
    function<int(char)> check =[&](int c) {
        int mx= 0;
        int pred = -1;
        for (int i = 0; i < isz(s); ++i) {
            if (s[i] == c) {
                mx = max(mx, i - pred);
                pred  =i;
            }
        }

        mx = max(mx, isz(s) - pred);
        return mx;
    };
    for (int i = 0; i < 26; ++i) {
        ans=min(ans, check(i + 'a'));
    }
    cout << ans << en;

    return 0;
}
 
