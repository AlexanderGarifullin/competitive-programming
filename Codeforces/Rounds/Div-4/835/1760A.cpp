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
       int x, y, z;
       cin >> x >> y >> z;
       cout << x + y + z - max(z,max(y,x)) - min(z,min(x,y)) << en;
   }
}
