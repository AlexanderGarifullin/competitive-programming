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

    int n; cin>> n;
    int x = 0, y = 0;
    string s; cin >> s;
    for (int i = 0; i < n; ++i) {
        if (s[i] == 'U' ) {
            y++;
        }
        else if (s[i] == 'D') {
            y--;
        }
        else if (s[i] == 'L'){
            x--;
        }
        else {
            x++;
        }
    }

    cout << n - abs(x) - abs(y) <<en;

    return 0;
}
 
