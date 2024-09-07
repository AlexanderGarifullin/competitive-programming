#include <bits/stdc++.h>

using namespace std;


int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);

    int n; cin >> n;
    string s; cin >> s;
    int cur = 0;
    int cntG =0;
    vector<int> l(n),r(n);
    for (int i = 0; i < n; ++i) {
        if (s[i] == 'G') {
            cntG++;
            cur++;
        }
        else {
            cur = 0;
        }
        l[i] = cur;
    }
    if (cntG == n) {
        cout << n;
        return 0;
    }
    if (cntG == 0) {
        cout << 0;
        return 0;
    }
    cur = 0;
    for (int i = n-1; i >=0 ; --i) {
        if (s[i] == 'G') {
            cur++;
        }
        else cur = 0;
        r[i] = cur;
    }
    int answ = 0;
    for (int i = 0; i < n; ++i) {
        int res = 0;
        if (i > 0) res += l[i-1];
        if (i < n - 1) res += r[i+1];
        if (res == cntG) answ = max(answ, cntG);
        else {
            answ = max(answ, 1 + res);
        }
    }
    cout << answ;
    return 0;
}
