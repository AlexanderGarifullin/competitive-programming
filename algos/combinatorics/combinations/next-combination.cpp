// go from the end and find  element we can increase
vector<int> next_combination(vector<int> combination , int n, int k){
    // can increase last element
    if (combination.back() != n) {
        combination.back()++;
        return combination;
    }
    // cannot increase last element
    else {
        for (int i = k - 2; i >=0 ; --i) {
            if (combination[i + 1] - combination[i] > 1) {
                combination[i]++;
                for (int j = i + 1; j < k; ++j) {
                    combination[j] = combination[j - 1] + 1;
                }
                break;
            }
        }
        return combination;
    }
}
