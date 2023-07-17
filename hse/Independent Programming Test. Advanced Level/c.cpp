//2ms
//296.00Kb
#include "bits/stdc++.h"
#define  en '\n'
#define all(x) begin(x), end(x)

using namespace std;
using ll = long long;
using vi = vector<int>;

int main(){
    cin.tie(0);
    ios_base::sync_with_stdio(false);

    int n; cin >> n;

    vector<string> v(n);
    for (int i = 0; i < n; ++i) {
        cin >> v[i];
    }

    set<char> gl = {'a', 'e', 'i', 'o', 'u'};


    stable_sort(all(v), [&](string s1, string s2){
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = 0; i < int(s1.size()); ++i) {
            if (gl.find(s1[i]) != gl.end()) cnt1++;
        }
        for (int i = 0; i < int(s2.size()); ++i) {
            if (gl.find(s2[i]) != gl.end()) cnt2++;
        }
        int len1 = int(s1.size());
        int len2 = int(s2.size());
        return cnt1 > cnt2 || cnt1 == cnt2 && len1 < len2;
    });

    for (int i = 0; i < n; ++i) {
        cout << v[i] << en;
    }

    return 0;
}
