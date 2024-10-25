// SPARSE TABLE
 
int spSize;
vvl sp;
vl dif;
 
// want to calculate GCD on all segments with len 2^i
void build (int n) {
    spSize = n;
    int c = __lg(n);
    sp = vvl(c+1, vl(n));
    for (int len = 0; len <= c; ++len) {
        for (int i = 0; i + (1<<len) - 1 < n; ++i) {
            if (len == 0) {
                sp[len][i] = dif[i];
            } else {
                sp[len][i] = accumulate(sp[len-1][i], sp[len-1][i + (1<<len) - (1<<(len-1))]);
            }
        }
    }
}
 
ll get(int left, int right){
    right++;
    int t = __lg(right - left);
    return accumulate(sp[t][left],sp[t][right - (1<<t)]);
}
 