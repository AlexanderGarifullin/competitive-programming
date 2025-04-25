import javax.crypto.spec.PSource;
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

    static void solve() {
        long n = cin.nextLong();
        long k = cin.nextLong();
        // x / k * x % k = n
        long ans = Long.MAX_VALUE;
        for (int p = 1; p < k; p++) {
            if (n % p != 0) continue;;
            long x = n / p;
            ans = Math.min(ans, x * k + p);
        }
        System.out.println(ans);
    }


    public static void main(String[] args) {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
