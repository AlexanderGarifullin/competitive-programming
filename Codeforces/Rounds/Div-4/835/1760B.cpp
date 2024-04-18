#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;


int main()
{
   ios_base::sync_with_stdio(false);
   cin.tie(NULL);
   int q;
   cin >> q;
   while (q--)
   {
       int n;
       cin >> n;
       string s;
       cin >> s;
       char mx = 'a';
       for (int i = 0; i < s.size(); ++i) {
           if (s[i] > mx)
            mx = s[i];
       }
       cout << mx - 'a' + 1 << en;
   }
   return 0;
}
