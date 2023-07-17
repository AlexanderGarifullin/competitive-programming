//2ms
//296.00Kb
#include "bits/stdc++.h"
#define  en '\n'


using namespace std;
using ll = long long;
using vi = vector<int>;

int main(){
    cin.tie(0);
    ios_base::sync_with_stdio(false);


    int n; cin >> n;
    cout << n / 48 << ' ' << (n % 48 / 16) << ' ' << (n % 48 % 16 / 4) <<' '<< (n % 48 % 16 % 4);

    return 0;
}
