// O(n)  
// 1. Find the first element from the end in such a way that a[i] > a[i+1].
// 2. From i + 1 to the end, find the maximum element that a[j] < a[i]. Swap a[i] and a[j].
// 3. Reverse the elements from i + 1 to the end.

vector<int> my_prev_permutation(vector<int> v){
    int n = int(v.size());
    for (int i = n - 2; i >=0 ; --i) {
        if (v[i] > v[i+1]){
            int maxmin = i + 1;
            for (int j = i + 1; j < n; ++j) {
                if (v[j] > v[maxmin] && v[j] < v[i]) maxmin = j;
            }
            swap(v[i], v[maxmin]);
            reverse(v.begin()+i+1, v.end());
            return v;
        }
    }
    // return smth. Current permutation is the minimum.
    for (int i = 0; i < n; ++i) {
        v[i] = n - i ;
    }
    return v;
}
