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

    static void solve() throws IOException {
        int n = cin.nextInt();
        int q = cin.nextInt();

        int[] fa = new int[n];
        for (int i = 1; i < n; i++) {
            fa[i] = cin.nextInt() - 1;
        }

        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = cin.nextInt() - 1;
        }

        int[] siz = new int[n];
        Arrays.fill(siz, 1);
        for (int i = n - 1; i > 0; i--) {
            siz[fa[i]] += siz[i];
        }

        int[] tin = new int[n];
        int[] tout = new int[n];
        Arrays.fill(tout, 1);
        for (int i = 1; i < n; i++) {
            tin[i] = tout[fa[i]];
            tout[i] = tin[i] + 1;
            tout[fa[i]] += siz[i];
        }

        int[] g = new int[n];
        int[] good = {0};

        Runnable toggle = () -> {
            for (int i = 0; i < n - 1; i++) {
                update(good, g, i, p, fa, tin, tout);
            }
        };

        toggle.run();

        while (q-- > 0) {
            int u = cin.nextInt() - 1;
            int v = cin.nextInt() - 1;

            swap(p, u, v);

            for (int i : new int[]{u - 1, u, v - 1, v}) {
                if (i >= 0 && i + 1 < n) {
                    update(good, g, i, p, fa, tin, tout);
                }
            }

            if (good[0] == n - 1 && p[0] == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
    
    private static void update(int[] good, int[] g, int i, int[] p, int[] fa, int[] tin, int[] tout) {
        good[0] -= g[i];
        int f = fa[p[i + 1]];
        g[i] = (tin[f] <= tin[p[i]] && tout[p[i]] <= tout[f]) ? 1 : 0;
        good[0] += g[i];
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
