ull mod_exp(ull base, ull exp, ull mod) {
    ull result = 1;
    base = base % mod;
    while (exp > 0) {
        if (exp % 2 == 1)
            result = (result * base) % mod;
        exp = exp >> 1;
        base = (base * base) % mod;
    }
    return result;
}

// Алгоритм Тонелли-Шенкса
ull get(ull n, ull p) {
    if (mod_exp(n, (p - 1) / 2, p) != 1) {
        return -1;
    }

    if (p % 4 == 3) {
        return mod_exp(n, (p + 1) / 4, p);
    }


   ull q = p - 1;
   ull s = 0;
    while (q % 2 == 0) {
        q /= 2;
        s++;
    }

    ull z = 2;
    while (mod_exp(z, (p - 1) / 2, p) == 1) {
        z++;
    }

    ull c = mod_exp(z, q, p);
    ull r = mod_exp(n, (q + 1) / 2, p);
    ull t = mod_exp(n, q, p);
    ull m = s;

    while (t != 1) {
        ull t2 = t;
        ull i = 0;
        for (i = 1; i < m; i++) {
            t2 = (t2 * t2) % p;
            if (t2 == 1) break;
        }

        ull b = mod_exp(c, 1LL << (m - i - 1), p);
        r = (r * b) % p;
        t = (t * b * b) % p;
        c = (b * b) % p;
        m = i;
    }

    return r;
}
