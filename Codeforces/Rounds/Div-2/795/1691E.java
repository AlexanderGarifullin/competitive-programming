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


    static class DSU {
        int[] f, siz;

        DSU(int n) {
            f = new int[n];
            siz = new int[n];
            for (int i = 0; i < n; i++) {
                f[i] = i;
                siz[i] = 1;
            }
        }

        int leader(int x) {
            if (f[x] != x) f[x] = leader(f[x]);
            return f[x];
        }

        boolean merge(int x, int y) {
            x = leader(x);
            y = leader(y);
            if (x == y) return false;
            siz[x] += siz[y];
            f[y] = x;
            return true;
        }
    }

    static int[] c, l, r;
    static List<Integer>[] p;
    static DSU dsu;
    static int ans;

    static void solve() throws IOException {
        int n = cin.nextInt();
        c = new int[n];
        l = new int[n];
        r = new int[n];
        p = new List[2];
        p[0] = new ArrayList<>();
        p[1] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            c[i] = cin.nextInt();
            l[i] = cin.nextInt();
            r[i] = cin.nextInt();
            p[c[i]].add(i);
        }

        for (int col = 0; col < 2; col++) {
            p[col].sort((i, j) -> {
                if (l[i] != l[j]) return Integer.compare(l[i], l[j]);
                return Integer.compare(r[j], r[i]);
            });
        }

        dsu = new DSU(n);
        ans = n;

        Runnable work = () -> {
            List<Integer> a = new ArrayList<>();
            for (int i : p[0]) {
                if (a.isEmpty() || r[i] > r[a.getLast()]) {
                    a.add(i);
                }
            }

            for (int i : p[1]) {
                int low = 0, high = a.size();
                while (low < high) {
                    int mid = (low + high) / 2;
                    if (r[a.get(mid)] < l[i]) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                }

                if (low < a.size()) {
                    int j = a.get(low);
                    if (r[i] >= l[j]) {
                        if (dsu.merge(i, j)) {
                            ans--;
                        }
                    }
                }
            }
        };

        work.run();
        List<Integer> tmp = p[0];
        p[0] = p[1];
        p[1] = tmp;
        work.run();

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
