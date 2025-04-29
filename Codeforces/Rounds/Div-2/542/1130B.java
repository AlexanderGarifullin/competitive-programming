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

    static void solve() {
        int n= cin.nextInt();
        List<List<Integer>> v = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            v.add(new ArrayList<>());
        }
        for (int i = 0; i < 2*n; i++) {
            int x = cin.nextInt();
            v.get(x).add(i);
        }
        long ans = v.get(1).getFirst() + v.get(1).getLast();
        for (int i = 2; i <= n; i++) {
            int next1 = v.get(i).getFirst();
            int next2 = v.get(i).getLast();
            int last1 = v.get(i-1).getFirst();
            int last2 = v.get(i-1).getLast();
            ans += Math.min(Math.abs(next1 - last1) +Math.abs(next2 - last2),
                    Math.abs(next2 - last1) +Math.abs(next1 - last2));
            ;
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
