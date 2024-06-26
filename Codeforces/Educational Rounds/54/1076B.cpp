#include <bits/stdc++.h>

using namespace std;


int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);

    function<bool(long long)> check = [](long long x) {
        for (int i = 2; i * i <= x ; ++i) {
            if (x % i == 0) return false;
        }
        return true;
    };

    long long  n; cin >> n;
    for (long long i = 2; i * i <= n ; ++i) {
        if (n % i == 0) {
            if (check(i)) {
                cout << 1 + (n - i) / 2 ;
                 return 0;
            }
        }
    }
    cout << 1;
    return 0;
}
