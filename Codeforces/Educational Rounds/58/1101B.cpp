#include <bits/stdc++.h>

using namespace std;

const int mod = 998244353;

int main(){

   // cin.tie(0); ios_base::sync_with_stdio(false);

   string s; cin >> s;

   int indStartBar = -1, indEndBar = -1, indStartDots = -1, indEndDots = -1;

    for (int i = 0; i < s.size(); ++i) {
        if (s[i] == '[') {
            indStartBar = i;
            for (int j = i + 1; j < s.size(); ++j) {
                if (s[j] == ':') {
                    indStartDots = j;
                    break;
                }
            }
            break;
        }
    }

    int n = s.size();
    for (int i = n-1; i >=0 ; --i) {
        if (s[i] == ']'){
            indEndBar = i;
            for (int j = i-1; j >=0 ; --j) {
                if (s[j] == ':') {
                    indEndDots = j;
                    break;
                }
            }
            break;
        }
    }
    if ((indStartBar == -1 || indEndDots == -1 || indEndBar == -1 || indStartDots == -1)||
    !(indStartBar < indStartDots && indStartDots < indEndDots && indEndDots < indEndBar)) {
        cout << -1;
        return 0;
    }
    int cnt = 4;
    for (int i = indStartDots+1; i < indEndDots; ++i) {
        if (s[i] == '|') cnt++;
    }
    cout << cnt;


    return 0;
}
