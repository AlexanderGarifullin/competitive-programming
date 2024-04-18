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
       vector<int> v(n);
       int max = -1;
       int predmax = -1;
       for (int i = 0; i < n; ++i) {
           int x;
            cin >> x;
           v[i] = x;
           if (x > max)
           {
               predmax = max;
               max = x;
           }
           else if (x > predmax)
               predmax = x;
       }
       for (int i = 0; i < n; ++i) {
           if (v[i] == max)
               cout << v[i] - predmax << " ";
           else
               cout << v[i] - max << " ";
       }
        cout << en;
   }
   return 0;
}
