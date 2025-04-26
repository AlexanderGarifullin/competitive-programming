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
        long n = cin.nextLong(), k = cin.nextLong();
        List<Long> a = new ArrayList<>();
        List<Long> b = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long x = cin.nextLong();
            a.add(x);
        }
        boolean find = false;
        for (int i = 0; i < n; i++) {
            long x = cin.nextLong();
            b.add(x);
            if (x != -1) {
                find = true;
            }
        }
        if (find) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (b.get(i) != -1) {
                    sum = b.get(i) + a.get(i);
                    break;
                }
            }
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                if (b.get(i) == -1) {
                    long need = sum - a.get(i);
                    if (need < 0 || need > k) {
                        ok = false;
                        break;
                    }
                } else {
                    if (sum != a.get(i) + b.get(i)) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) System.out.println(1); else System.out.println(0);
            return;
        }

        long maxValue = 0;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, a.get(i));
        }

        long left = 0, right = k + 1;
        while(left + 1 < right) {
            long mid = (left + right) / 2;
            long sum = maxValue + mid;
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                if (b.get(i) == -1) {
                    long need = sum - a.get(i);
                    if (need < 0 || need > k) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) left = mid;
            else right = mid;
        }
        System.out.println(left+1);
    }


    public static void main(String[] args) {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
