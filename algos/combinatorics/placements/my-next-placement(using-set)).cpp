// take all not using elements from placement
// go from the end to start of placement and try to replace my element that greater than current
// replace cur element on element that greater than it, replace element from current to the end
// on elements from set not using elements
bool next_placement_set(int n, vector<int> &place){
    set<int> notUsingset;
    for (int i = 1; i <= n; ++i) {
        notUsingset.insert(i);
    }
    for (int i = 0; i < place.size(); ++i) {
        notUsingset.erase(place[i]);
    }
    for (int i = int(place.size()) - 1; i >= 0; --i) {
        auto it = notUsingset.upper_bound(place[i]);
        if (it != notUsingset.end()){
            notUsingset.insert(place[i]);
            place[i] = *it;
            notUsingset.erase(it);
            it = notUsingset.begin();
            for (int j = i + 1; j < place.size() ; ++j) {
                place[j] = *it;
                it++;
            }
            return true;
        }
        notUsingset.insert(place[i]);
    }

    return false;
}

i32 main() {
    //  freopen("input.txt", "rt", stdin);
    //  freopen("output.txt", "wt", stdout);


    cin.tie(nullptr); ios_base::sync_with_stdio(false);


    int n, k; cin >> n >> k;

    vector<int> placement(k);
    for (int i = 0; i < k; ++i) {
       placement[i] = i + 1;
    }

    bool next = true;
    do {
        for (int i = 0; i < placement.size(); ++i) {
            cout << placement[i];
            if (i != int(placement.size()) - 1) cout << ' ';
        }
        next = next_placement_set(n, placement);
        if (next) cout <<'\n';
    } while(next);

    return 0;
}
