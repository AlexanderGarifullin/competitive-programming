int s[maxn];

int sizes (int v) {
    s[v] = 1;
    for (int u : g[v])
        // для простоты считаем, что дерево корневое
        s[v] += sizes(u);
    return s[v];
}

// второй параметр -- размер дерева
int centroid (int v, int n) {
    for (int u : g[v])
        if (s[u] > n/2)
            return centroid(u, n);
    return v;
}