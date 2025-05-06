import java.security.PublicKey;
import java.util.*;

public class Main {
    static Scanner cin = new Scanner(System.in);

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

    static void solve() {
        int n = cin.nextInt();
        int [] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = cin.nextInt() - 1;
        }
        int [] dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[0]=0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == -1) continue;;
            int [] cnt = new int[n];
            int mx = 0;
            for (int j = i; j <= n; j++) {
                if (j>i) {
                    int v = a[j-1];
                    if (++cnt[v]  > mx) mx = cnt[v];
                }

                int len = j - i;
                if ((len & 1) == 0 && mx * 2 <= len) {
                    if (j == n) {
                        dp[n] = Math.max(dp[n], dp[i]);
                    } else if (i == 0 || a[i-1] == a[j]) {
                        dp[j+1] = Math.max(dp[j+1], dp[i] + 1);
                    }
                }
            }
        }

        System.out.println(dp[n]);
    }

    public static void main(String[] args) {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
