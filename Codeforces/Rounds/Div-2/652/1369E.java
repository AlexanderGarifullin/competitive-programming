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

    static final int N = 100_005, M = 200_005;
    static int[] w = new int[N];
    static int[] sum = new int[N];
    static boolean[] vis = new boolean[M];
    static List<Pair>[] e = new ArrayList[N];

    static class Pair {
        int v, id;
        Pair(int v, int id) {
            this.v = v;
            this.id = id;
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt(), m = cin.nextInt();

        for (int i = 0; i < n; i++) {
            w[i] = cin.nextInt();
            e[i] = new ArrayList<>();
        }

        int[] x = new int[m];
        int[] y = new int[m];

        for (int i = 0; i < m; i++) {
            x[i] = cin.nextInt() - 1;
            y[i] = cin.nextInt() - 1;

            e[x[i]].add(new Pair(y[i], i));
            e[y[i]].add(new Pair(x[i], i));
            sum[x[i]]++;
            sum[y[i]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (sum[i] <= w[i]) {
                queue.add(i);
            }
        }

        List<Integer> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Pair p : e[u]) {
                int v = p.v;
                int i = p.id;
                if (vis[i]) continue;
                vis[i] = true;
                ans.add(i);
                if (--sum[v] == w[v]) {
                    queue.add(v);
                }
            }
        }

        if (ans.size() < m) {
            System.out.println("DEAD");
        } else {
            System.out.println("ALIVE");
            Collections.reverse(ans);
            for (int i = 0; i < m; i++) {
                System.out.print((ans.get(i) + 1) + " ");
            }
            System.out.println();
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
