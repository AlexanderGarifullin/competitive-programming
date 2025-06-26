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

    static void solve() throws IOException {
        int n = cin.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        List<List<Integer>> e = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            e.add(new ArrayList<>());
        }

        for (int i = 0; i < n; ++i) {
            a[i] = cin.nextInt() - 1;
            e.get(a[i]).add(i);
        }
        for (int i = 0; i < n; ++i) {
            b[i] = cin.nextInt() - 1;
            e.get(b[i]).add(i);
        }

        boolean ok = true;
        for (int i = 0; i < n; ++i) {
            if (e.get(i).size() != 2) {
                ok = false;
                break;
            }
        }

        if (!ok) {
            System.out.println(-1);
            return;
        }

        boolean[] vis = new boolean[n];
        List<Integer> rev = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            if (vis[i]) continue;
            if (Objects.equals(e.get(i).get(0), e.get(i).get(1))) continue;

            List<Integer> x = new ArrayList<>();
            List<Integer> y = new ArrayList<>();

            int j = i;
            int k = e.get(i).get(0);
            while (!vis[j]) {
                vis[j] = true;
                k = e.get(j).get(0) ^ e.get(j).get(1) ^ k;

                if (a[k] == j) {
                    x.add(k);
                    j = b[k];
                } else {
                    y.add(k);
                    j = a[k];
                }
            }

            if (x.size() < y.size()) {
                rev.addAll(x);
            } else {
                rev.addAll(y);
            }
        }

        System.out.println(rev.size());
        for (int i = 0; i < rev.size(); ++i) {
            System.out.print((rev.get(i) + 1));
            System.out.print(i == rev.size() - 1 ? "\n" : " ");
        }
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
