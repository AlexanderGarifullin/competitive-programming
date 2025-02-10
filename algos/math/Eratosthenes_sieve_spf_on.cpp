const int N = 1e7;  // Граница
vector<int> primes;  // Список простых чисел
vector<int> min_prime(N + 1, 0);  // min_prime[i] - минимальный простой делитель i

void linear_sieve(int n) {
    for (int i = 2; i <= n; i++) {
        if (min_prime[i] == 0) {  // i - простое число
            min_prime[i] = i;
            primes.push_back(i);
        }
        for (int p : primes) {
            if (p * i > n || p > min_prime[i]) break; // Останавливаемся
            min_prime[p * i] = p;
        }
    }
}
