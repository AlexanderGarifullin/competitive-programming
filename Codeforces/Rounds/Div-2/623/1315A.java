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
        long a = cin.nextInt(), b = cin.nextInt(), x = cin.nextInt(), y = cin.nextInt();
        long ans = 0;
        ans = Math.max(ans, x * b);
        ans = Math.max(ans, (a - 1 - x) * b);
        ans = Math.max(ans,a * y);
        ans = Math.max(ans,a * (b - 1 - y));
        System.out.println(ans);
    }


    public static void main(String[] args) {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
