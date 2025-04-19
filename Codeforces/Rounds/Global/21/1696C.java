import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
    static int n, m;
    static List<List<Long>> calc(List<Integer> v) {
        List<List<Long>> ans = new ArrayList<>();
        for (int i = 0; i < v.size(); i++) {
            long val = v.get(i), c = 1;
            while(val % m == 0) {
                val /= m;
                c *= m;
            }
            if (!ans.isEmpty() && ans.getLast().getFirst() == val) {
                List<Long> last = ans.getLast();
                last.set(1, last.get(1) + c);
            } else {
                ans.add(Arrays.asList(val, c));
            }
        }
        return ans;
    }

    static void solve() {
        n = cin.nextInt();
        m = cin.nextInt();
        List<Integer> a = new ArrayList<>(n);
        for (int i = 0, x; i < n; i++) {
            x = cin.nextInt();
            a.add(x);
        }
        int k = cin.nextInt();
        List<Integer> b = new ArrayList<>(k);
        for (int i = 0, x; i < k; i++) {
            x = cin.nextInt();
            b.add(x);
        }

        if (calc(a).equals(calc(b))) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {

        int t = cin.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}
