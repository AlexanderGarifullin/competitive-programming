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
       ll count1 = 0;
       ll a = 0;
       int first_0 = -1;
       int last_1 = -1;
       for (int i = 0; i < n; ++i) {
           cin >> v[i];
           if (v[i] == 1) {
                count1++;
               last_1 = i;
           }
           else 
           {
               a += count1;
               if (first_0 == -1)
                   first_0 = i;
           }
       }
       if (last_1 != -1) {
           v[last_1] = 0;
           ll a2 = 0;
           count1 = 0;
           for (int i = 0; i < n; ++i) {
               if (v[i] == 0) {
                   a2 += count1;
               } else
                   count1++;
           }
           a = max(a, a2);
           v[last_1] = 1;
       }
       if (first_0 != -1) {
           v[first_0] = 1;
           ll a3 = 0;
           count1 = 0;
           for (int i = 0; i < n; ++i) {
               if (v[i] == 0) {
                   a3 += count1;
               } else
                   count1++;
           }
           a = max(a, a3);
       }
       cout << a << en;
   }
   return 0;
}
