import java.util.*;

public class HelloWorld {
    public static void main(String [] args) {
        Long n,m;
        Scanner sc = new Scanner(System.in);
        n = sc.nextLong(); m = sc.nextLong();
        Map<Long, Long> cnt = new TreeMap<>();
        long x = 2;
        while(x*x<= m) {
            while(m % x == 0) {
                m/=x;
                if (cnt.containsKey(x))  {
                    cnt.put(x, cnt.get(x)+1);
                } else {
                    cnt.put(x, 1L);
                }
            }
            x++;
        }
        if (m > 1) cnt.put(m, 1L);
        ArrayList<Long> v = new ArrayList<>();
        for (var val : cnt.entrySet()) {
            long d = val.getKey();
            long c = val.getValue();
            long cn = 0;
            long cur = 1;
            while(1.0 * cur * d <= n) {
                cur *= d;
                cn += n/cur;
            }
            v.add(cn/c);
        }
        long mm = Collections.min(v);
        System.out.println(mm);
    }
}
