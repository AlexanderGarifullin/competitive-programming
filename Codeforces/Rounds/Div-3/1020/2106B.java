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
        int n = cin.nextInt();
        int x = cin.nextInt();
        List<Integer> p = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            p.add(i);
        }
        if (x != n) {
            p.set(x, n - 1);
            p.set(n - 1, x);
        }
        for (int y : p) System.out.print(y + " ");
        System.out.println();
    }


    public static void main(String[] args) {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
