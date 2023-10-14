#include <bits/stdc++.h>
#define ll long long
using namespace std;

ll t,n,m,x,y;
int main() {
//    ios_base::sync_with_stdio(false);
//    cin.tie(0);
    cin >> t;
    while (t--)
    {
        cin >> n >> m;
        bool fl = true;
        int matr[n][n];
        bool lad[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matr[i][j] = 0;
                lad[i][j] = false;
            }
        }
        for (int i = 0; i < m; ++i) {
            cin >> x >> y;
            lad[x-1][y-1]  =  true;
            for (int j = 0; j < n; ++j) {
                matr[x-1][j]++;
            }
            for (int j = 0; j < n; ++j) {
                matr[j][y-1]++;
            }
        }
        if (n == 1)
            cout <<"NO\n";
        else
        {
            if (m == 1)
                cout << "YES\n";
            else
            {
                if (n == m)
                    cout << "NO\n";
                else
                    cout <<"YES\n";
            }
        }
    }
    return 0;
}
 
