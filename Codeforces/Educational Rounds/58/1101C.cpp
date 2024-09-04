#include <bits/stdc++.h>

using namespace std;



int main(){

    cin.tie(0); ios_base::sync_with_stdio(false);

    int t; cin >> t;
    while(t--){
        int n; cin >> n;
        vector<int>types(n, 1);
        vector<tuple<int,int,int>> events;
        events.reserve(n);
        for (int i = 0; i < n; ++i) {
            int l, r; cin >> l >> r;
            events.push_back({l,r, i});
        }
        sort(events.begin(), events.end(),
             [](tuple<int,int,int> t1, tuple<int,int,int> t2){
                 auto [l1, r1, i1] = t1;
                 auto [l2,r2,i2] = t2;
                 return  l1 < l2 || l1 == l2 && r1 > r2;
             });

        bool find = 0;

        vector<tuple<int,int,int>> pairs;
        vector<pair<int,int>> startI_endI;
        pairs.push_back(events.front());
        auto[x1,x2,j] = events.front();
        vector<vector<int>> g;
        g.push_back({j});
        for (int i = 1; i < n; ++i) {
            auto [lastL, lastR, lastI] = pairs.back();
            auto [curL, curR, curI] = events[i];
            if (curL <= lastR) {
                pairs.back() = {lastL, max(lastR, curR), curI};
                g.back().push_back(curI);
            }
            else {
                pairs.push_back({curL, curR, curI});
                g.push_back({curI});
            }
        }


        if (pairs.size() == 1) {
            cout <<"-1\n";
        }
        else {
            for (int& id : g.front()) {
                types[id] = 2;
            }
            for (int i = 0; i < n; ++i) {
                cout << types[i] << ' ';
            }
            cout <<"\n";
        }

    }


    return 0;
}
