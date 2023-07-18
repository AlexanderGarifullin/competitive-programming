// O(n)  
// 1. Find the first element from the end in such a way that a[i] < a[i+1].
// 2. From i + 1 to the end, find the minimum element that a[j] > a[i]. Swap a[i] and a[j].
// 3. Reverse the elements from i + 1 to the end.
vector<int> my_next_permutation(vector<int> v){
    int n = int(v.size());
    for (int i = n - 2; i >=0 ; --i) {
        if (v[i] < v[i+1]){
            int minmax = i + 1;
            for (int j = i + 2; j < n; ++j) {
                if (v[j] < v[minmax] && v[j] > v[i]) swap(j, minmax);
            }
            swap(v[i], v[minmax]);
            reverse(v.begin()+i+1, v.end());
            return v;
        }
    }
    // return smth. Current permutation is the maximum.
    for (int i = 0; i < n; ++i) {
        v[i] = i + 1;
    }
    return v;
}
