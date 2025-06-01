import java.io.IOException;
import java.io.InputStream;
import java.util.*;
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


    static void solve() throws IOException {
        int n = cin.nextInt();
        int k = cin.nextInt();
        String s = cin.next();

        ArrayList<Integer> zeros = new ArrayList<>();
        ArrayList<Integer> ones  = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                zeros.add(i);
            } else {
                ones.add(i);
            }
        }

        if (zeros.isEmpty() || ones.isEmpty()) {
            System.out.println("tokitsukaze");
            return;
        }

        int z_min = zeros.get(0);
        int z_max = zeros.get(zeros.size() - 1);
        int o_min = ones.get(0);
        int o_max = ones.get(ones.size() - 1);

        int span0 = z_max - z_min;
        int span1 = o_max - o_min;

        if (span0 < k || span1 < k) {
            System.out.println("tokitsukaze");
            return;
        }

        if (span0 > k || span1 > k) {
            System.out.println("once again");
            return;
        }

        int idx0 = lowerBound(zeros, k);

        if (idx0 < zeros.size()) {
            if (z_max - zeros.get(idx0) >= k) {
                System.out.println("once again");
                return;
            }
        }

        int idx1 = lowerBound(ones, k);
        if (idx1 < ones.size()) {
            if (o_max - ones.get(idx1) >= k) {
                System.out.println("once again");
                return;
            }
        }

        int t0 = lowerBound(zeros, n - k);
        int idx0_2 = t0 - 1;
        if (idx0_2 >= 0) {
            if (zeros.get(idx0_2) - z_min >= k) {
                System.out.println("once again");
                return;
            }
        }

        int t1 = lowerBound(ones, n - k);
        int idx1_2 = t1 - 1;
        if (idx1_2 >= 0) {
            if (ones.get(idx1_2) - o_min >= k) {
                System.out.println("once again");
                return;
            }
        }

        System.out.println("quailty");
    }

    private static int lowerBound(ArrayList<Integer> arr, int key) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid) < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
