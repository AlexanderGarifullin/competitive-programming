//51ms
//4.48Mb
#include "bits/stdc++.h"
#define  en '\n'
#define all(x) begin(x), end(x)
#define isz(s) int(s.size())
using namespace std;
using ll = long long;
using vi = vector<int>;

int main(){
    cin.tie(0);
    ios_base::sync_with_stdio(false);;

    freopen("input.txt", "rt",stdin);
    freopen("output.txt", "wt",stdout);


    string s1 , s2;
    getline(cin, s1);
    getline(cin, s2);

    unordered_set<string> st;

    string word = "";
    s1 += " ";
    for (int i = 0; i < isz(s1); ++i) {
        if (isspace(s1[i])) {
            st.insert(word);
            word = "";
        }
        else word += s1[i];
    }

    vector<string> v;
    word = "";
    s2 += " ";
    for (int i = 0; i < isz(s2); ++i) {
        if (isspace(s2[i])) {
            bool find = false;
            for (int j = 1; j <= isz(word); ++j) {
                string x = word.substr(0, j);
                if (st.find(x) != st.end()) {
                    find = true;
                    v.push_back(x);
                    break;
                }
            }
            if (!find) v.push_back(word);
            word = "";
        }
        else word += s2[i];
    }

    // cout << s1 << en;
    // cout << s2 << en;
    for (int i = 0; i < isz(v); ++i) {
        cout << v[i] << ' ';
    }

    return 0;
}
