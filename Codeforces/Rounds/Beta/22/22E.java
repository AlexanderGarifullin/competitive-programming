import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;
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
    static class DSU {
        int[] parent, size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int leader(int x) {
            if (parent[x] != x)
                parent[x] = leader(parent[x]);
            return parent[x];
        }

        boolean same(int x, int y) {
            return leader(x) == leader(y);
        }

        boolean merge(int x, int y) {
            int lx = leader(x);
            int ly = leader(y);
            if (lx == ly)
                return false;
            parent[ly] = lx;
            size[lx] += size[ly];
            return true;
        }

        int size(int x) {
            return size[leader(x)];
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int[] f = new int[n];
        int[] in = new int[n];
        DSU dsu = new DSU(n);

        for (int i = 0; i < n; i++) {
            f[i] = cin.nextInt() - 1;
            in[f[i]]++;
            dsu.merge(i, f[i]);
        }

        int[] a = new int[n];
        int[] vis = new int[n];
        Arrays.fill(vis, -1);

        for (int i = 0; i < n; i++) {
            int j = i;
            while (vis[j] == -1) {
                vis[j] = i;
                j = f[j];
            }
            if (vis[j] == i) {
                a[dsu.leader(j)] = j;
            }
        }

        boolean conn = true;
        for (int i = 0; i < n; i++) {
            if (!dsu.same(0, i) || in[i] != 1) {
                conn = false;
                break;
            }
        }

        if (conn) {
            System.out.println(0);
            return;
        }

        boolean[] used = new boolean[n];
        List<int[]> ed = new ArrayList<>();
        List<int[]> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                int leader = dsu.leader(i);
                if (!used[leader]) {
                    ed.add(new int[]{a[leader], i});
                    used[leader] = true;
                } else {
                    ans.add(new int[]{a[leader], i});
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int leader = dsu.leader(i);
            if (!used[leader]) {
                ed.add(new int[]{i, i});
                used[leader] = true;
            }
        }

        for (int i = 0; i < ed.size(); i++) {
            int x = ed.get(i)[0];
            int y = ed.get((i + 1) % ed.size())[1];
            ans.add(new int[]{x, y});
        }

        System.out.println(ans.size());
        for (int[] pair : ans) {
            System.out.println((pair[0] + 1) + " " + (pair[1] + 1));
        }
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
