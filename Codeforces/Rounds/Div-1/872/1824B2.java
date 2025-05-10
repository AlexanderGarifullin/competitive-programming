import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

    private static final int MAX = 500_000 + 5;

    private static int[] mobius = new int[MAX];
    private static int[] cnt    = new int[MAX];
    private static boolean[] onShelf;
    private static int[][] divisors;
    private static long answer = 0;

    static class Pair<F,S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        static <F,S> Pair<F,S> of(F first, S second) {
            return new Pair<>(first, second);
        }
    }

    static final long MOD = 1_000_000_007L;
    static int n, k, halfK;
    static long[] fact, invFact;
    static int[] head, to, next;
    static int edgePtr = 0;

    static long modPow(long a, long e) {
        long res = 1;
        while (e > 0) {
            if ((e & 1) == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            e >>= 1;
        }
        return res;
    }

    static long C(int n, int r) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    static long invC(int n, int r) {
        if (r < 0 || r > n) return 0;
        return invFact[n] * fact[r] % MOD * fact[n - r] % MOD;
    }

    static void addEdge(int u, int v) {
        to[edgePtr] = v;
        next[edgePtr] = head[u];
        head[u] = edgePtr++;
    }

    static void solve() throws IOException {
        n = cin.nextInt();
        k = cin.nextInt();
        if ((k & 1) == 1) {
            System.out.println(1);
            return;
        }
        halfK = k / 2;

        head = new int[n];
        Arrays.fill(head, -1);
        to   = new int[2 * (n - 1)];
        next = new int[2 * (n - 1)];

        for (int i = 0; i < n - 1; i++) {
            int u = cin.nextInt() - 1;
            int v = cin.nextInt() - 1;
            addEdge(u, v);
            addEdge(v, u);
        }

        fact    = new long[n + 1];
        invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % MOD;
        invFact[n] = modPow(fact[n], MOD - 2);
        for (int i = n; i > 0; i--) invFact[i - 1] = invFact[i] * i % MOD;

        long invChooseN = invC(n, k);

        int[] parent = new int[n];
        int[] order  = new int[n];
        int  ordTop  = 0;

        int[] stack = new int[n];
        int  stTop  = 0;
        stack[stTop++] = 0;
        parent[0] = -1;

        while (stTop > 0) {
            int v = stack[--stTop];
            order[ordTop++] = v;
            for (int eid = head[v]; eid != -1; eid = next[eid]) {
                int u = to[eid];
                if (u == parent[v]) continue;
                parent[u] = v;
                stack[stTop++] = u;
            }
        }

        int[] sub = new int[n];
        long ans = 1;

        for (int i = ordTop - 1; i >= 0; --i) {
            int v = order[i];
            sub[v] = 1;
            for (int eid = head[v]; eid != -1; eid = next[eid]) {
                int u = to[eid];
                if (u == parent[v]) continue;
                sub[v] += sub[u];
            }
            if (parent[v] != -1) {
                int left  = sub[v];
                int right = n - left;
                long add = C(left,  halfK) * C(right, halfK) % MOD;
                add = add * invChooseN % MOD;
                ans += add;
                if (ans >= MOD) ans -= MOD;
            }
        }

        System.out.println(ans % MOD);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
