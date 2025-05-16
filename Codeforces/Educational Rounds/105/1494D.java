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

    static int[][] a;
    static int[] c;
    static List<int[]> edges = new ArrayList<>();
    static int n;
    static int currentIndex;

    static int calc(List<Integer> group) {
        if (group.size() == 1) return group.get(0);

        int res = -1;
        for (int u : group)
            res = Math.max(res, a[group.get(0)][u]);

        List<List<Integer>> subgroups = new ArrayList<>();
        subgroups.add(new ArrayList<>(List.of(group.get(0))));

        for (int i = 1; i < group.size(); i++) {
            int v = group.get(i);
            boolean placed = false;

            for (List<Integer> subgroup : subgroups) {
                if (a[v][subgroup.get(0)] != res) {
                    subgroup.add(v);
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                List<Integer> newGroup = new ArrayList<>();
                newGroup.add(v);
                subgroups.add(newGroup);
            }
        }

        int manager = currentIndex++;
        c[manager] = res;

        for (List<Integer> subgroup : subgroups) {
            int subRoot = calc(subgroup);
            edges.add(new int[]{subRoot, manager});
        }

        return manager;
    }

    static void solve() throws IOException {
        n = cin.nextInt();
        a = new int[2 * n][2 * n];
        c = new int[2 * n];
        currentIndex = n;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = cin.nextInt();

        for (int i = 0; i < n; i++)
            c[i] = a[i][i];

        List<Integer> baseGroup = new ArrayList<>();
        for (int i = 0; i < n; i++)
            baseGroup.add(i);

        int root = calc(baseGroup);

        int totalEmployees = currentIndex;
        System.out.println(totalEmployees);

        for (int i = 0; i < totalEmployees; i++)
            System.out.print(c[i] + " ");
        System.out.println();

        System.out.println(root + 1);
        for (int[] edge : edges)
            System.out.println((edge[0] + 1) + " " + (edge[1] + 1));
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
