import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.BiConsumer;

public class Main {
    private static final class FastScanner {
        private final byte[] buf = new byte[1 << 16];
        private int len = 0, ptr = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws java.io.IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buf[ptr++];
        }

        int nextInt() throws java.io.IOException {
            int c, sign = 1, val = 0;
            do { c = readByte(); } while (c <= ' ' && c != -1);
            if (c == '-') { sign = -1; c = readByte(); }
            while (c > ' ' && c != -1) {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        long nextLong() throws java.io.IOException {
            long c, sign = 1, val = 0;
            do { c = readByte(); } while (c <= ' ' && c != -1);
            if (c == '-') { sign = -1; c = readByte(); }
            while (c > ' ' && c != -1) {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        String next() throws java.io.IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);

            if (c == -1) return null;

            StringBuilder sb = new StringBuilder();
            while (c > ' ' && c != -1) {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }
    }

    static FastScanner cin = new FastScanner(System.in);

    public static <T extends Comparable<? super T>> boolean isSorted(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a * b / gcd(a,b);
    }

    static long modPow(long base, int exp, int mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }

    static final int MOD = 998244353;
    static final int MAXN = 510000;
    static int[] L = new int[MAXN], R = new int[MAXN], val = new int[MAXN];
    static long[] inv = new long[MAXN], invFact = new long[MAXN];
    static int n;
    static int C;
    static List<Integer> tour = new ArrayList<>(MAXN);

    static void precompute() {
        inv[1] = 1;
        for (int i = 2; i < MAXN; i++) {
            inv[i] = MOD - (MOD / i) * inv[MOD % i] % MOD;
        }
        invFact[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            invFact[i] = invFact[i - 1] * inv[i] % MOD;
        }
    }

    static void dfs(int v) {
        Deque<Integer> stack = new ArrayDeque<>();
        int cur = v;
        while (cur != -1 || !stack.isEmpty()) {
            while (cur != -1) {
                stack.push(cur);
                cur = L[cur];
            }
            int node = stack.pop();
            tour.add(val[node]);
            cur = R[node];
        }
    }

    static void solve() throws IOException {
        n = cin.nextInt();
        C = cin.nextInt();
        for (int i = 1; i <= n; i++) {
            L[i] = cin.nextInt();
            R[i] = cin.nextInt();
            val[i] = cin.nextInt();
        }
        tour.clear();
        tour.add(1);
        dfs(1);
        tour.add(C);

        long ans = 1;
        int last = 1, cnt = 0;
        for (int x : tour) {
            if (x == -1) {
                cnt++;
            } else {
                int a = last, b = x;
                for (int i = 1; i <= cnt; i++) {
                    ans = ans * ( (b - a) + i ) % MOD;
                }
                ans = ans * invFact[cnt] % MOD;
                last = x;
                cnt = 0;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        precompute();
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
