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

    static int lowerBound(int[] a, int key) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (a[m] >= key) r = m;
            else l = m + 1;
        }
        return l;
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = cin.nextInt();
        Arrays.sort(a);

        List<List<Integer>> b = new ArrayList<>();
        int[] ave = new int[m];
        long[] groupSums = new long[m];
        int[] groupSizes = new int[m];
        List<int[]> groupFlatIndex = new ArrayList<>();

        int totalStudents = 0;

        for (int i = 0; i < m; i++) {
            int k = cin.nextInt();
            groupSizes[i] = k;
            List<Integer> ages = new ArrayList<>();
            long sum = 0;
            for (int j = 0; j < k; j++) {
                int age = cin.nextInt();
                sum += age;
                groupFlatIndex.add(new int[]{i, j});
                ages.add(age);
            }
            b.add(ages);
            groupSums[i] = sum;
            ave[i] = (int) ((sum + k - 1) / k);
            totalStudents += k;
        }

        int[] s = new int[n + 1];
        Arrays.fill(s, 1);
        s[n] = 0;
        int[] groupTeacherIndex = new int[m];
        for (int i = 0; i < m; i++) {
            int idx = lowerBound(a, ave[i]);
            groupTeacherIndex[i] = idx;
            s[idx]--;
        }

        for (int i = n - 1; i >= 0; i--) {
            s[i] += s[i + 1];
        }

        int min = Arrays.stream(s).min().getAsInt();

        if (min >= 0) {
            int[] prevZero = new int[n + 1];
            int last = -1;
            for (int i = 0; i <= n; i++) {
                if (s[i] == 0) last = i;
                prevZero[i] = last;
            }

            for (int[] pair : groupFlatIndex) {
                int i = pair[0], j = pair[1];
                long sum = groupSums[i] - b.get(i).get(j);
                int k = groupSizes[i] - 1;
                int nave = (int) ((sum + k - 1) / k);
                int newIdx = lowerBound(a, nave);
                if (prevZero[newIdx] <= groupTeacherIndex[i]) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
        } else {
            int l = -1, r = -1;
            for (int i = 0; i <= n; i++) {
                if (s[i] < 0) {
                    if (l == -1) l = i;
                    r = i;
                }
            }

            for (int[] pair : groupFlatIndex) {
                int i = pair[0], j = pair[1];
                long sum = groupSums[i] - b.get(i).get(j);
                int k = groupSizes[i] - 1;
                int nave = (int) ((sum + k - 1) / k);
                int newIdx = lowerBound(a, nave);
                if (newIdx < l && r <= groupTeacherIndex[i]) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
