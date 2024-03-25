#include <bits/stdc++.h>

using namespace std;


int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);

    int t; cin >> t;
    while(t--){
        string s; cin >> s;
        set<char>st;
        for (int i = 0; i < s.size(); ++i) {
            st.insert(s[i]);
        }
        if (st.size() == 1) {
            cout << -1 <<"\n";
            continue;
        }
        sort(s.begin(),s.end());
        cout << s << "\n";
    }
    return 0;
}
