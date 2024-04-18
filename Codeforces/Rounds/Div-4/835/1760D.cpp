#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;
bool solve(vector<int> &v)
{
    if (v.size() < 3)
        return true;
    int c= 0;
    if (v[1] > v[0])
        c++;
    if (v.back() < v[v.size()-2])
        c++;
    for (int i = 1; i <= v.size() - 2 ; ++i) {
        if (v[i] < v[i+1] && v[i] < v[i-1])
            c++;
    }
    if (c > 1)
        return false;
    return true;
}
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
       cin >> v[0];
       vector<int>uniq;
       uniq.push_back(v[0]);
       for (int i = 1; i < n; ++i) {
           cin >> v[i];
           if (v[i] != uniq.back())
               uniq.push_back(v[i]);
       }
       if (solve(uniq))
           cout << "YES" << en;
       else
           cout << "NO" << en;
   }
   return 0;
}
