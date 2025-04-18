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
        long n = scanner.nextInt();
        long a=0,b=0;
        for (int i =0; i < n; i++) {
            int x = scanner.nextInt();
            if (x % 2 == 0) {
                a++;
            } else {
                b++;
            }
        }
        if (b % 4 == 2) {
            System.out.println("Bob");
        } else if (b % 4 == 3 || b % 4 == 0) {
            System.out.println("Alice");
        } else if (a % 2 == 1) {
            System.out.println("Alice");
        } else {
            System.out.println("Bob");
        }
    }

    public static void main(String[] args) {

        int t = scanner.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}
