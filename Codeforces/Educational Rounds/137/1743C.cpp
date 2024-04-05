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
        string s;
        cin >> s;
        ll sum = 0;
        vector <ll> numbers(n);
        cin >> numbers[0];
        if (s[0] == '1')
            sum+= numbers[0];
        for (ll i = 1; i < n; ++i) {
            cin >> numbers[i];
        }
        for (ll i = 1; i < n; ++i) {
            if (s[i-1] == '0' && s[i] == '1') {
                if (numbers[i - 1] > numbers[i]) {
                    sum += numbers[i - 1];
                    s[i] = '0';
                    s[i-1]='1';
                 }
                else
                {
                    bool fl = false;
                    for (int j = i+1; j < n; ++j) {
                        if (s[j] == '0')
                            break;
                        if (numbers[j] <  numbers[i-1])
                        {
                            sum += numbers[i-1];
                            sum += numbers[i];
                            s[i-1] = '1';
                            s[j] = '0';
                            fl = true;
                            break;
                        }
                    }
                    if (!fl)
                        sum += numbers[i];
                }
            }
            else if (s[i-1] == '1' && s[i] == '1')
                sum += numbers[i];
        }
        cout << sum << "\n";
    }
    return 0;
}
 
