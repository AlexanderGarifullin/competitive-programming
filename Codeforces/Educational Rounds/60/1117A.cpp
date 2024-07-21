#include <bits/stdc++.h>

using namespace std;



int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);

   int n; cin >> n;
   vector<int> v(n);
    for (int i = 0; i < n; ++i) {
        cin >> v[i];
    }
    int maxel = *max_element(v.begin(),v.end());
    int answ = 0;
    int cur = 0;
    for (int i = 0; i < n; ++i) {
        if (v[i] == maxel) {
            cur++;
            answ = max(answ, cur);
        }
        else cur = 0;
    }

    cout << answ;
    return 0;
}
