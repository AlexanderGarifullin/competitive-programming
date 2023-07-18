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

/*
    Proof 3 point
    We need sort elements from i + 1 to n in ascending order. 
    We can reverse instead of sorting because:
    Let x < a1; a1 > a2 > a3 >a4; a3 - minimum that a3 > x;
    Then if we swap x and a3: a3 < a1 > a2 > x > a4
    Elements a1,a2,x,a4 already sorted descending and if we reverse them we'll get ascending order: a4 < x < a2 < a1 
*/
