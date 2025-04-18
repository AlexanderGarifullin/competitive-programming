import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            ArrayList<Integer> a = new ArrayList<>(n);
            for (int i =0; i < n; i++) {
                int ai = scanner.nextInt();
                a.add(ai);
            }
            ArrayList<ArrayList<Long>> s = new ArrayList<>();
            s.add(new ArrayList<>(n));
            s.add(new ArrayList<>(n));
            for (int i =0; i < n; i++) {
                long x = scanner.nextInt();
                s.get(a.get(i)).add(x);
            }
            long res = Math.max(
                    s.get(0).stream().mapToLong(i->i).sum(),
                    s.get(1).stream().mapToLong(i->i).sum()
            );
            for (int i =0; i < 2; i++) {
                s.get(i).sort(Long::compareTo);
            }

            long rr;
            if (s.get(0).size() == s.get(1).size()) {
                rr = 2 * (s.get(0).stream().mapToLong(i -> i).sum() + s.get(1).stream().mapToLong(i -> i).sum());
                rr -= Math.min(s.get(0).getFirst(), s.get(1).getFirst());
            } else {
                rr = 0;
                while (!s.get(0).isEmpty() && !s.get(1).isEmpty()) {
                    rr += (s.get(0).getLast() + s.get(1).getLast()) * 2;
                    s.get(0).removeLast();
                    s.get(1).removeLast();
                }
                for (var x : s.get(0)) rr += x;
                for (var x : s.get(1)) rr += x;
            }
            res = Math.max(res, rr);
            System.out.println(res);
        }
    }
}
