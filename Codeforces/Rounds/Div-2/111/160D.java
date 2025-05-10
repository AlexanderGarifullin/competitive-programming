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

    static class DSU {
        int[] p;
        DSU(int n) { p = new int[n]; Arrays.fill(p, -1); }
        int find(int v) { return p[v] < 0 ? v : (p[v] = find(p[v])); }
        void union(int a, int b) {
            a = find(a); b = find(b);
            if (a == b) return;
            if (p[a] > p[b]) { int t = a; a = b; b = t; }
            p[a] += p[b];
            p[b]  = a;
        }
    }

    static class Edge {
        int u, v, w, id;
        Edge(int u, int v, int w, int id) { this.u = u; this.v = v; this.w = w; this.id = id; }
    }

    static class AdjEdge {
        int to, id;
        AdjEdge(int to, int id) { this.to = to; this.id = id; } }

    static int timer;
    static void dfs(int v, int parentEdge,
                    List<List<AdjEdge>> g,
                    boolean[] vis, int[] tin, int[] low,
                    String[] ans) {

        vis[v] = true;
        tin[v] = low[v] = ++timer;

        for (AdjEdge e : g.get(v)) {
            int to  = e.to;
            int eid = e.id;
            if (eid == parentEdge) continue;

            if (!vis[to]) {
                dfs(to, eid, g, vis, tin, low, ans);
                low[v] = Math.min(low[v], low[to]);

                if (low[to] > tin[v]) {
                    ans[eid] = "any";
                } else {
                    ans[eid] = "at least one";
                }
            } else {
                low[v] = Math.min(low[v], tin[to]);
                ans[eid] = "at least one";
            }
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();
        Edge[]edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int a = cin.nextInt() - 1;
            int b= cin.nextInt() - 1;
            int w = cin.nextInt();
            edges[i] = new Edge(a,b,w,i);
        }
        Arrays.sort(edges, Comparator.comparingInt(e -> e.w));

        DSU dsu = new DSU(n);
        String[] ans = new String[m];

        for (int i = 0; i < m; ) {
            int j = i;
            int curW = edges[i].w;
            while (j < m && edges[j].w == curW) j++;

            List<Edge> cand = new ArrayList<>();
            for (int k = i; k < j; k++) {
                Edge e = edges[k];
                if (dsu.find(e.u) == dsu.find(e.v)) {
                    ans[e.id] = "none";
                } else {
                    cand.add(e);
                }
            }

            Map<Integer, Integer> idx = new HashMap<>();
            int cnt = 0;
            for (Edge e : cand) {
                int a = dsu.find(e.u), b = dsu.find(e.v);
                if (!idx.containsKey(a)) idx.put(a, cnt++);
                if (!idx.containsKey(b)) idx.put(b, cnt++);
            }
            List<List<AdjEdge>> g = new ArrayList<>(cnt);
            for (int t = 0; t < cnt; t++) g.add(new ArrayList<>());
            for (Edge e : cand) {
                int a = idx.get(dsu.find(e.u));
                int b = idx.get(dsu.find(e.v));
                g.get(a).add(new AdjEdge(b, e.id));
                g.get(b).add(new AdjEdge(a, e.id));
            }

            timer = 0;
            boolean[] vis = new boolean[cnt];
            int[] tin  = new int[cnt];
            int[] low  = new int[cnt];

            for (int v = 0; v < cnt; v++) {
                if (!vis[v]) dfs(v, -1, g, vis, tin, low, ans);
            }

            for (int k = i; k < j; k++) {
                Edge e = edges[k];
                dsu.union(e.u, e.v);
            }

            i = j;
        }

        for (var s : ans) System.out.println(s);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
