#include <bits/stdc++.h>

using namespace std;


int main(){

    cin.tie(0);
    ios_base::sync_with_stdio(false);

    int n; cin >> n;
    string s; cin >> s;
    bool find = 0;
    for (int i = 0; i < s.size()-1; ++i) {
        if (s[i] > s[i+1]) {
            for (int j = 0; j < i; ++j) {
                cout << s[j];
            }
            for (int j = i + 1; j < n; ++j) {
                cout << s[j];
            }

            find =1;
            break;
        }
    }
    if (!find) {
        for (int i = 0; i < n-1; ++i) {
            cout << s[i];
        }
    }


    return 0;
}
