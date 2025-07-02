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

    static final long INF = (long) 1e18;

    static void solve() throws IOException {
        int n = cin.nextInt(), m = cin.nextInt();

        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = cin.nextInt() - 1;
            int v = cin.nextInt() - 1;
            int w = cin.nextInt();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        long[] dis = new long[4 * n];
        Arrays.fill(dis, INF);
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        dis[0] = 0;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long d = top[0];
            int x = (int) top[1];
            if (d > dis[x]) continue;

            int u = x / 4;
            int p = (x / 2) % 2;
            int q = x % 2;

            for (int[] edge : graph[u]) {
                int v = edge[0], w = edge[1];
                int y = v * 4 + p * 2 + q;

                if (dis[y] > dis[x] + w) {
                    dis[y] = dis[x] + w;
                    pq.offer(new long[]{dis[y], y});
                }

                if (p == 0) {
                    int y2 = v * 4 + 2 + q;
                    if (dis[y2] > dis[x]) {
                        dis[y2] = dis[x];
                        pq.offer(new long[]{dis[y2], y2});
                    }
                }

                if (q == 0) {
                    int y3 = v * 4 + p * 2 + 1;
                    if (dis[y3] > dis[x] + 2L * w) {
                        dis[y3] = dis[x] + 2L * w;
                        pq.offer(new long[]{dis[y3], y3});
                    }
                }
            }
        }
        ;
        for (int i = 1; i < n; i++) {
            long res = Math.min(dis[i * 4], dis[i * 4 + 3]);
            System.out.print(res + " ");
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
