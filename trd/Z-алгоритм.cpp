vector<int> to_z(string& s, bool firstElementZero = true) {
    vector<int> z(s.size());
    if (!firstElementZero) 
        z[0] = z.size();

    int l = 0, r = 1;
    for (int i = 1; i < z.size(); i++) {
        if (r > i) 
            z[i] = min(z[i - l], r - i);

        while (z[i] + i < z.size() && s[z[i]] == s[z[i] + i]) 
            z[i]++;

        if (r < z[i] + i) { 
            l = i;
            r = z[i] + i; 
        }
    }

    return z;
}
