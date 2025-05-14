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


    private static class Pair implements Comparable<Pair> {
        int dist, node;

        Pair(int dist, int node) {
            this.dist = dist;
            this.node = node;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.dist != other.dist) {
                return Integer.compare(this.dist, other.dist);
            }
            return Integer.compare(this.node, other.node);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return dist == p.dist && node == p.node;
        }

        @Override
        public int hashCode() {
            return Objects.hash(dist, node);
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();

        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int[] out = new int[n];

        for (int i = 0; i < m; i++) {
            int a = cin.nextInt() - 1;
            int b = cin.nextInt() - 1;
            out[a]++;
            adj.get(b).add(a);
        }

        final int INF = Integer.MAX_VALUE / 2;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));


        dist[n - 1] = 0;
        pq.offer(new int[]{0, n - 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], v = cur[1];
            if (d != dist[v]) continue;

            for (int to : adj.get(v)) {
                int oldOut = out[to];
                out[to]--;
                int newDist = dist[v] + oldOut;
                if (newDist < dist[to]) {
                    dist[to] = newDist;
                    pq.offer(new int[]{newDist, to});
                }
            }
        }

        System.out.println(dist[0]);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
