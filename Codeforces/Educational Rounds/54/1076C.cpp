#include <bits/stdc++.h>

using namespace std;


int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);

    int t; cin >> t;
    while(t--) {
        long double d ; cin >> d;
        long double D = d * d - 4 * d;
        if (D < 0) {
            cout << "N\n";
            continue;
        }
        long double x1,x2,x3,x4, sq;
        sq = sqrtl(D);
        x1 = (-d + sq) / 2.0;
        x2 = (-d - sq) / 2.0;
        x3 = (d + sq) / 2.0;
        x4 = (d - sq) / 2.0;
        cout << "Y " << setprecision(20) << x3 << ' ' << x4 <<"\n";

    }

    return 0;
}
