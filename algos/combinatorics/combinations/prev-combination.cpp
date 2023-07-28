// go from the end and find element we can decrease
vector<int> prev_combination(vector<int> combination , int n, int k){
        for (int i = k - 1; i >= 0 ; --i) {
            if (i > 0 && combination[i] - combination[i -1] > 1 || i == 0 && combination[i] - 0 > 1) {
                combination[i]--;
                for (int j = k -1; j > i ; --j) {
                    combination[j] = n + 1 - k + j;
                }
                break;
            }
        }
    return combination;
}
