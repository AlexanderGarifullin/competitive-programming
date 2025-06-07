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

    static class Query {
        int l, r, idx;
        public Query(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        long k = cin.nextLong();

        int[] t = new int[n];

        for (int i = 0; i < n; i++) {
            t[i] = cin.nextInt();
        }

        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            long v = cin.nextLong();
            a[i] = (t[i] == 1 ? v : -v);
        }

        long[] S = new long[n+1];
        S[0] = 0;
        for (int i = 1; i <= n; i++) {
            S[i] = S[i-1] + a[i-1];
        }

        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i <= n; i++) {
            set.add(S[i]);
            set.add(S[i] - k);
            set.add(S[i] + k);
        }
        int M = set.size();
        long[] all = new long[M];
        int ptr = 0;
        for (long v : set) {
            all[ptr++] = v;
        }

        Map<Long,Integer> index = new HashMap<>(M);
        for (int i = 0; i < M; i++) {
            index.put(all[i], i);
        }

        int[] idxS = new int[n+1];
        int[] idxLm = new int[n+1];
        int[] idxLp = new int[n+1];
        for (int i = 0; i <= n; i++) {
            idxS[i]  = index.get(S[i]);
            idxLm[i] = index.get(S[i] - k);
            idxLp[i] = index.get(S[i] + k);
        }

        int q = cin.nextInt();
        Query[] qs = new Query[q];
        for (int i = 0; i < q; i++) {
            int l = cin.nextInt() - 1;
            int r = cin.nextInt();
            qs[i] = new Query(l, r, i);
        }

        int block = Math.max(1, (int)(n / Math.sqrt(q)));
        Arrays.sort(qs, (u, v) -> {
            int bu = u.l / block, bv = v.l / block;
            if (bu != bv) return Integer.compare(bu, bv);
            if ((bu & 1) == 0) return Integer.compare(u.r, v.r);
            else return Integer.compare(v.r, u.r);
        });

        long[] answer = new long[q];
        long[] cnt = new long[M];
        int curL = 0, curR = -1;
        long curAns = 0;

        for (Query qu : qs) {
            int L = qu.l, R = qu.r;
            while (curR < R) {
                curR++;
                curAns += cnt[idxLm[curR]];
                cnt[idxS[curR]]++;
            }
            while (curR > R) {
                cnt[idxS[curR]]--;
                curAns -= cnt[idxLm[curR]];
                curR--;
            }
            while (curL < L) {
                cnt[idxS[curL]]--;
                curAns -= cnt[idxLp[curL]];
                curL++;
            }
            while (curL > L) {
                curL--;
                curAns += cnt[idxLp[curL]];
                cnt[idxS[curL]]++;
            }
            answer[qu.idx] = curAns;
        }

        for (var x : answer) {
            System.out.println(x);
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
