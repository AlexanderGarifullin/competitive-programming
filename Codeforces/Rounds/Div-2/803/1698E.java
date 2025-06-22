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

    static final int MOD = 998244353;

    static int lowerBound(List<Integer> list, int x) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m) < x) l = m + 1;
            else r = m;
        }
        return l;
    }

    static class Pair {
        int first, second;
        Pair(int f, int s) {
            first = f;
            second = s;
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int s = cin.nextInt();
        Pair[] a = new Pair[n];
        boolean[] vis = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = new Pair(cin.nextInt(), -1);
        }
        for (int i = 0; i < n; i++) {
            a[i].second = cin.nextInt();
            if (a[i].second != -1) {
                vis[a[i].second] = true;
            }
        }

        List<Integer> missing = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) missing.add(i);
        }

        Arrays.sort(a, Comparator.comparingInt(p -> p.second == -1 ? Integer.MAX_VALUE : p.second));

        int maxDiff = 0;
        for (Pair p : a) {
            if (p.second != -1) {
                if (p.first - p.second > maxDiff) {
                    maxDiff = p.first - p.second;
                }
            }
        }

        if (maxDiff > s) {
            System.out.println(0);
            return;
        }

        List<Integer> cnts = new ArrayList<>();
        Collections.sort(missing);
        for (Pair p : a) {
            if (p.second == -1) {
                int threshold = p.first - s;
                int count = missing.size() - lowerBound(missing, threshold);
                cnts.add(count);
            }
        }

        Collections.sort(cnts);
        long res = 1;
        for (int i = 0; i < cnts.size(); i++) {
            int val = cnts.get(i) - i;
            if (val <= 0) {
                res = 0;
                break;
            }
            res = (res * val) % MOD;
        }

        System.out.println(res);
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
