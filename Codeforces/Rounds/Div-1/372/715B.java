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
    static class Edge {
        int to, id;
        Edge(int to, int id) { this.to = to; this.id = id; }
    }

    static final long INF = 4_000_000_000_000_000_000L;

    static int n, m, s, t;
    static long L;
    static int[] u, v;
    static long[] w;
    static int[] idx;
    static List<Edge>[] g;
    static int cntZeros;

    static void solve() throws IOException {
        n = cin.nextInt();
        m = cin.nextInt();
        L = cin.nextLong();
        s = cin.nextInt();
        t = cin.nextInt();
        u = new int[m];
        v = new int[m];
        w = new long[m];
        idx = new int[m];
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            u[i] = cin.nextInt();
            v[i] = cin.nextInt();
            w[i] = cin.nextLong();

            g[u[i]].add(new Edge(v[i], i));
            g[v[i]].add(new Edge(u[i], i));

            if (w[i] == 0) idx[i] = cntZeros++;
        }
        if (cntZeros == 0) {
            long d = dijkstra(-1);
            if (d == L) {
                System.out.println("YES");
                printEdges();
            } else {
                System.out.println("NO");
            }
            return;
        }
        long hi   = (L + 1) * cntZeros;
        long low  = cntZeros;

        if (dijkstra(hi) < L) {
            System.out.println("NO");
            return;
        }
        if (dijkstra(low) > L) {
            System.out.println("NO");
            return;
        }

        long lo = low, hiSearch = hi;
        while (lo < hiSearch) {
            long mid = (lo + hiSearch) >>> 1;
            if (dijkstra(mid) >= L) hiSearch = mid;
            else                    lo       = mid + 1;
        }

        long X = lo;

        for (int i = 0; i < m; i++) {
            if (w[i] == 0)
                w[i] = (X + idx[i]) / cntZeros;
        }

        System.out.println("YES");
        printEdges();
    }

    private static long dijkstra(long X) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        boolean[] done = new boolean[n];

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[]{0, s});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int   vtx  = (int) cur[1];
            if (done[vtx]) continue;
            done[vtx] = true;
            dist[vtx] = cur[0];
            if (vtx == t) break;

            for (Edge e : g[vtx]) {
                int to = e.to;
                if (done[to]) continue;
                long wEdge = w[e.id] != 0 ? w[e.id] : (X + idx[e.id]) / cntZeros;
                if (dist[to] > dist[vtx] + wEdge) {
                    dist[to] = dist[vtx] + wEdge;
                    pq.add(new long[]{dist[to], to});
                }
            }
        }
        return dist[t];
    }

    private static void printEdges() {
        for (int i = 0; i < m; i++) {
            System.out.println(u[i] + " " + v[i] + " " + w[i]);
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
