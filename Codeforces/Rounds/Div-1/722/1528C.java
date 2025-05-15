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

    static int timeStamp;
    static int[] in, out, id;
    static List<Integer>[] tree;
    static TreeSet<Integer> activeSet;
    static int maxClique;

    static void solve() throws IOException {
        int n = cin.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        a[0] = b[0] = -1;
        for (int i = 1; i < n; i++) {
            a[i] = cin.nextInt() - 1;
        }
        for (int i = 1; i < n; i++) {
            b[i] = cin.nextInt() - 1;
        }
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            tree[b[i]].add(i);
        }

        in = new int[n];
        out = new int[n];
        id = new int[n];
        timeStamp = 0;

        dfs1(0);

        for (int i = 0; i < n; i++) tree[i].clear();
        for (int i = 1; i < n; i++) {
            tree[a[i]].add(i);
        }

        activeSet = new TreeSet<>();
        maxClique = 0;

        dfs2(0);

        System.out.println(maxClique);
    }
    static void dfs1(int u) {
        in[u] = timeStamp++;
        id[in[u]] = u;
        for (int v : tree[u]) {
            dfs1(v);
        }
        out[u] = timeStamp;
    }
    static void dfs2(int u) {
        boolean added = false;
        Integer removed = null;

        Integer it = activeSet.ceiling(in[u]);
        if (it == null || it >= out[u]) {
            Integer prev = activeSet.lower(in[u]);
            if (prev == null || out[id[prev]] <= in[u]) {
                added = true;
                activeSet.add(in[u]);
            } else {
                removed = prev;
                activeSet.remove(prev);
                activeSet.add(in[u]);
                added = true;
            }
        }

        maxClique = Math.max(maxClique, activeSet.size());

        for (int v : tree[u]) {
            dfs2(v);
        }

        if (added) {
            activeSet.remove(in[u]);
            if (removed != null) {
                activeSet.add(removed);
            }
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
