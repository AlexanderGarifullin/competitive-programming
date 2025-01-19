/*
Количество способов разложить n видов предметов по k ящикам, таким способом,
что в каждом язике будет хотя бы 1 предмет, при этом все способы должны быть различными, 
то в конкретном наборе не должно быть ящиков, содежащих одинаковое множество элементов.
*/
long long count_ways(const vector<int>& x, int k) {
    if (k == 1) return 1L;
    int n = x.size();
    long long total_ways = 0;
 
    // Применяем принцип включения-исключения
    for (int j = 0; j < k; ++j) {
        long long current_term = (j % 2 == 0 ? 1 : -1) * C(k, j);
        for (int i = 0; i < n; ++i) {
            current_term *= C(x[i] + k - j - 1, k - j - 1);
        }
        total_ways += current_term;
    }
 
    return total_ways;
}
