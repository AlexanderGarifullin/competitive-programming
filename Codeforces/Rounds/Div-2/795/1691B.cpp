#include <bits/stdc++.h>
#define ll long long
using namespace std;

ll n, t;
string s;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cin >> t;
    while (t--){
        cin >> n;
        bool ans = true;
        vector <long long> perm (n);
        vector <long long> size (n);
        for (ll i = 0; i < n; ++i) {
            cin >> size[i];
            perm[i] = i + 1;
        }
        ll l = 0, r = 0;
        while (r < n)
        {
            while (r < n - 1 && size[r] == size[r+1])
                r++;
            if (l == r)
            {
                ans = false;
            }
            else
            {
                rotate(perm.begin() + l, perm.begin() + r, perm.begin() + r + 1);
            }
            l = r + 1;
            r++;
        }
        if (ans)
        {
            for (int i = 0; i < n; ++i) {
                cout << perm[i] << " ";
            }
            cout << endl;
        }
        else
        {
            cout << -1 << endl;
        }
    }
    return 0;
}
