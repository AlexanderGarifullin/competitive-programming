#include <bits/stdc++.h>
#define ll long long
#define en '\n'
#define ld long double
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    vector<char> v;
    string s;
    cin >> s;
    for (int i = 0; i < s.size(); ++i) {
        if (s[i] != '+')
            v.push_back(s[i]);
    }
    sort(v.begin(),v.end());
    for (int i = 0; i < v.size(); ++i) {
        cout << v[i] ;
        if (i != v.size() - 1)
            cout << "+";
    }
}
