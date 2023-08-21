#include <bits/stdc++.h>
#define ll long long
using namespace std;

ll t,n;
int main() {

    cin >> t;
    while(t--)
    {
        cin >> n;
        char x;
        cin >> x;
        string s;
        cin >> s;
        ll ans = 0;
        if (x =='g')
            cout<< ans << "\n";
        else
        {
            vector<vector<ll>> colors(3);
            for (ll i = 0; i < s.length(); ++i) {
                if (s[i] == 'g')
                    colors[0].push_back(i);
                else if (s[i] == 'y')
                    colors[1].push_back(i);
                else
                    colors[2].push_back(i);
            }
            short ty = 1;
            if (x == 'r')
                ty = 2;
            for (int i = 0; i < colors[ty].size(); ++i) {
                int tek  = colors[ty][i];
                bool greater = false;
                for (int j = 0; j < colors[0].size(); ++j) {
                    int tek_green =  colors[0][j];
                    if (tek_green > tek)
                    {
                        ll dif = tek_green - tek;
                        ans = max(ans, dif);
                        greater = true;
                        break;
                    }
                }
                if (!greater)
                    for (int j = 0; j < colors[0].size(); ++j) {
                        int tek_green =  colors[0][j];
                        if (tek_green < tek)
                        {
                            ll dif = n - tek + tek_green ;
                            ans = max(ans, dif);
                            break;
                        }
                        else
                            break;
                    }
            }
            cout<< ans << "\n";
        }
    }
    return 0;
}
 
