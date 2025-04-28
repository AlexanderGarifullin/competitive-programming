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
        String s = cin.next();
        if (s.length() <= 2) {
            System.out.println(s);
            return;
        }
        List<Character> good = List.of('a', 'e', 'i', 'o', 'u');
        int last = -1;
        for (int i = 1; i + 1< s.length(); i++) {
            boolean left = !good.contains(s.charAt(i-1));
            boolean mid = !good.contains(s.charAt(i));
            boolean right = !good.contains(s.charAt(i+1));
            if (left && mid && right && !(s.charAt(i+1) == s.charAt(i) && s.charAt(i) == s.charAt(i-1))) {
                System.out.print(s.charAt(i-1));
                System.out.print(s.charAt(i));
                last = i;
                System.out.print(" ");
                i = i + 1;
            } else {
                last = i-1;
                System.out.print(s.charAt(i-1));
            }
        }
        System.out.println(s.substring(last+1));
    }


    public static void main(String[] args) {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
