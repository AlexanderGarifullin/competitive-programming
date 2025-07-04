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

    static final int N = 100_001;
    static final int LOGN = 18;
    static final int X = 21;
    static final int M = N + X;

    static long[] distRoot = new long[N];
    static long[][] distA = new long[X][N];
    static long[][] distB = new long[X][N];
    static int[][] jump = new int[LOGN][N];
    static int[] depth = new int[N];
    static int[] A = new int[M], B = new int[M], W = new int[M];
    static int[] p = new int[N], notIn = new int[N];
    static int pnt = 0;

    static int get(int a) {
        return p[a] == a ? a : (p[a] = get(p[a]));
    }

    static boolean union(int a, int b) {
        a = get(a);
        b = get(b);
        if (a == b) return false;
        p[b] = a;
        return true;
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        for (int i = LOGN - 1; i >= 0; --i) {
            if (depth[jump[i][a]] >= depth[b]) {
                a = jump[i][a];
            }
        }
        if (a == b) return a;
        for (int i = LOGN - 1; i >= 0; --i) {
            if (jump[i][a] != jump[i][b]) {
                a = jump[i][a];
                b = jump[i][b];
            }
        }
        return jump[0][a];
    }

    static long dist(int a, int b) {
        return distRoot[a] + distRoot[b] - 2 * distRoot[lca(a, b)];
    }

    static List<List<int[]>> g = new ArrayList<>(N);

    static void dfs(int v, int par) {
        jump[0][v] = par;
        for (int[] edge : g.get(v)) {
            int to = edge[0], w = edge[1];
            if (to != par) {
                depth[to] = depth[v] + 1;
                distRoot[to] = distRoot[v] + w;
                dfs(to, v);
            }
        }
    }

    static long getDist(int x, int a, int b) {
        int idx = notIn[x];
        return Math.min(distA[x][a] + distB[x][b], distB[x][a] + distA[x][b]) + W[idx];
    }

    static void solve() throws IOException {
        int n = cin.nextInt(), m = cin.nextInt();

        for (int i = 0; i < n; ++i) {
            p[i] = i;
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < m; ++i) {
            A[i] = cin.nextInt() - 1;
            B[i] = cin.nextInt() - 1;
            W[i] = cin.nextInt();
        }

        Arrays.fill(notIn, -1);
        for (int i = 0; i < m; ++i) {
            if (!union(A[i], B[i])) {
                notIn[pnt++] = i;
            } else {
                g.get(A[i]).add(new int[]{B[i], W[i]});
                g.get(B[i]).add(new int[]{A[i], W[i]});
            }
        }

        dfs(0, 0);
        for (int j = 1; j < LOGN; ++j) {
            for (int i = 0; i < n; ++i) {
                jump[j][i] = jump[j - 1][jump[j - 1][i]];
            }
        }

        for (int i = 0; i < pnt; ++i) {
            int idx = notIn[i];
            for (int j = 0; j < n; ++j) {
                distA[i][j] = dist(A[idx], j);
                distB[i][j] = dist(B[idx], j);
            }
        }

        for (int x = 0; x < pnt; ++x) {
            for (int i = 0; i < pnt; ++i) {
                for (int j = 0; j < n; ++j) {
                    distA[i][j] = Math.min(distA[i][j], getDist(x, A[notIn[i]], j));
                    distB[i][j] = Math.min(distB[i][j], getDist(x, B[notIn[i]], j));
                }
            }
        }

        int q = cin.nextInt();
        while (q-- > 0) {
            int a = cin.nextInt() - 1;
            int b = cin.nextInt() - 1;
            long ans = dist(a, b);
            for (int i = 0; i < pnt; ++i) {
                ans = Math.min(ans, getDist(i, a, b));
            }
            System.out.println(ans);
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
