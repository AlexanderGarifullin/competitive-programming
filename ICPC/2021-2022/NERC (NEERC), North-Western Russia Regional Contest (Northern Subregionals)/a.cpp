#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    string time1,time2,sy1,sy2;
    cin >> time1 >> sy1 >> time2 >> sy2;
    ll y1,y2,ty1,ty2;
    if (time1[0] == 'A') {
        y1 = stoi(sy1);
        ty1 = 1;
    }
    else {
        y1 = -stoi(time1) ;
        ty1 = 2;
    }
    if (time2[0] == 'A') {
        y2 = stoi(sy2);
        ty2 = 1;
    }
    else {
        y2 = -stoi(time2) ;
        ty2 = 2;
    }
    if (ty1 != ty2)
        cout << abs(y1-y2) - 1;
    else
        cout <<   abs(y1-y2);
    return 0;
}
