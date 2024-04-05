#include <bits/stdc++.h>
#define ll long long
using namespace std;

ll t, n;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cin >> t;
    while (t--)
    {
        cin >> n;
        int dif = n/2;
        int c = 0;
        for (int i = 1; i <= dif; ++i) {
            cout << i << " ";
            cout << n - i + 1<< " ";
        }
        if (n % 2 == 1)
            cout << dif+ 1;
        cout << "\n";
    }
    return 0;
}
 
