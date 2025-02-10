vi calculatePhi(int n) {
    vi phi(n + 1);
    iota(all(phi), 0);
    for (int i = 1; i <= n; i++) {
        for (int j = 2 * i; j <= n; j += i) {
            phi[j] -= phi[i];
        }
    }
    return phi;
}
 
