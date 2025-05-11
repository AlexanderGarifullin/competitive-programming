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

    private static final class Point {
        final int x, y, id;
        Point(int x, int y, int id) { this.x = x; this.y = y; this.id = id; }
    }

    private static final class Edge {
        final int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    private static final class Node implements Comparable<Node> {
        final int v;
        final long d;
        Node(int v, long d) { this.v = v; this.d = d; }
        public int compareTo(Node o) { return Long.compare(this.d, o.d); }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();
        int sx = cin.nextInt();
        int sy = cin.nextInt();
        int fx = cin.nextInt();
        int fy = cin.nextInt();

        Point[] p = new Point[m];
        for (int i = 0; i < m; ++i) {
            int x = cin.nextInt(), y = cin.nextInt();
            p[i] = new Point(x, y, i);
        }

        List<Edge>[] g = new ArrayList[m];
        for (int i = 0; i < m; ++i) g[i] = new ArrayList<>(4);

        addEdgesByCoordinate(p, g, true);
        addEdgesByCoordinate(p, g, false);

        long[] dist = new long[m];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (Point pt : p) {
            long d0 = Math.min(Math.abs((long)sx - pt.x),
                    Math.abs((long)sy - pt.y));
            dist[pt.id] = d0;
            pq.add(new Node(pt.id, d0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.d != dist[cur.v]) continue;
            for (Edge e : g[cur.v]) {
                long nd = cur.d + e.w;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    pq.add(new Node(e.to, nd));
                }
            }
        }

        long ans = Math.abs((long)sx - fx) + Math.abs((long)sy - fy);
        for (Point pt : p) {
            long cand = dist[pt.id]
                    + Math.abs((long)fx - pt.x)
                    + Math.abs((long)fy - pt.y);
            ans = Math.min(ans, cand);
        }

        System.out.println(ans);
    }

    private static void addEdgesByCoordinate(Point[] p, List<Edge>[] g, boolean byX) {
        Point[] sorted = p.clone();
        Arrays.sort(sorted, (a, b) -> byX ? Integer.compare(a.x, b.x)
                : Integer.compare(a.y, b.y));
        for (int i = 1; i < sorted.length; ++i) {
            Point u = sorted[i - 1], v = sorted[i];
            int w = byX ? Math.min(Math.abs(u.x - v.x), Math.abs(u.y - v.y))
                    : Math.min(Math.abs(u.x - v.x), Math.abs(u.y - v.y));
            g[u.id].add(new Edge(v.id, w));
            g[v.id].add(new Edge(u.id, w));
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
