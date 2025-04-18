import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static boolean isSorted(List<Long> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1) > list.get(i)) {
                return false;
            }
        }
        return true;
    }

    static void solve() {
        int n,k;
        n = scanner.nextInt();
        k = scanner.nextInt();

        List<Long> s = new ArrayList<>(k);
        for (int i =0; i <k; i++) {
            long x = scanner.nextLong();
            s.add(x);
        }
        if (k == 1) {
            System.out.println("YES");
            return;
        }
        List<Long> a = new ArrayList<>(k-1);
        for (int i = 1; i < k; i++) {
            a.add(s.get(i) - s.get(i-1));
        }
        if (!isSorted(a)) {
            System.out.println("NO");
            return;
        }
        long kek = a.getFirst();
        if (s.getFirst() <= kek * (n - k + 1)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {

        int t = scanner.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}
