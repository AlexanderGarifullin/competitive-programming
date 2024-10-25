vector<int> prefix_function(string s) {
    int n = (int) s.size();
    vector<int> p(n, 0);
    for (int i = 1; i < n; i++) {
        // префикс функция точно не больше этого значения + 1
        int cur = p[i - 1];
        // уменьшаем cur значение, пока новый символ не сматчится
        while (s[i] != s[cur] && cur > 0)
            cur = p[cur - 1];
        // здесь либо s[i] == s[cur], либо cur == 0
        if (s[i] == s[cur])
            p[i] = cur + 1;
    }
    return p;
}