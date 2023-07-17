#include "bits/stdc++.h"
#define  en '\n'
#define all(x) begin(x), end(x)
#define isz(s) int(s.size())
using namespace std;
using ll = long long;
using vi = vector<int>;
using pii = pair<int,int>;


int main(){
    // 35ms
    //296.00Kb
    cin.tie(0); ios_base::sync_with_stdio(false);;

    int n, k; cin >> n >> k;

    list<pair<int,int>> groups;

    for (int i = 0; i < k; ++i) {
        int x; cin >> x;
        if (x == 1) {
            int l, r; cin >> l >> r;
            bool ok = true;
            for (auto [left, right] : groups ){
                if (!(right < l || left > r)) {
                    ok =false;
                    break;
                }
            }
            if (!ok) {
                cout << 0 << en;
                continue;
            }
            cout << 1 << en;
            groups.push_back({l, r});
        }
        else {
            int d; cin >> d;
            int l, r;
            bool find_group = false;
            for (auto [left, right] :groups)
            {
                if (d >= left && d <= right) {
                    find_group = true;
                    l = left;
                    r= right;
                    break;
                }
            }
            if (!find_group) {
                cout << 0 << ' ' << 0 << en;
                continue;
            }
            groups.remove({l,r});
            cout << l << ' ' << r << en;
        }
    }

    return 0;
}

