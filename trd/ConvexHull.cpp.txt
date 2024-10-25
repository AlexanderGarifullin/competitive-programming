#include <bits/stdc++.h>
#define ll long long
#define ld long double
using namespace std;

using tpoint = ll;

struct point{
    tpoint x,y;
    point(tpoint x = 0, tpoint y = 0) : x(x), y(y) {};

    point operator-(point p)
    {
        return  point(x-p.x, y - p.y);
    }
    point operator+(point p)
    {
        return point(x + p.x,y + p.y);
    }
    ld operator*(point p)
    {
        return x*p.x +y * p.y;
    }
    ld operator^(point p)
    {
        return x * p.y - y * p.x;
    }
    tpoint len2(){
        return x * x + y * y;
    }
};


bool ccw(point p0, point p1, point p2)
{
    auto vec1 = p1-p0;
    auto vec2 = p2 - p1;
    return (vec1^vec2) > 0;
}

ll n, x,y;
int main() {
   ios_base::sync_with_stdio(false);
   cin.tie(0);
   freopen("hull.in","r",stdin);
    freopen("hull.out","w",stdout);
    cin >> n;
    vector <point> p(n);
    for (int i = 0; i < n; ++i) {
        cin >> x >> y;
        p[i] = point(x,y);
    }
    point p_first = p[0];
    for (int i = 1; i < n; ++i) {
        if (p[i].x < p_first.x || p[i].x == p_first.x && p[i].y < p_first.y)
            p_first = p[i];
    }

    sort(p.begin(),p.end(),[&p_first](point a, point b)
    {
        a = a - p_first;
        b = b - p_first;
        return (a^b) > 0 || (a^b) == 0 && a.len2() < b.len2();
    });
    vector <point> ans;
    for (int i = 0; i < p.size(); ++i) {
        while(ans.size() >= 2 && !ccw(ans[ans.size() - 2], ans[ans.size() - 1], p[i]))
        {
            ans.pop_back();
        }
        ans.push_back(p[i]);
    }
    cout << ans.size() << "\n";
    for(auto j: ans)
    {
        cout << j.x <<" "<< j.y << "\n";
    }

    return 0;
}

