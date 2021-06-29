package camp.loodeer;

/**
 * @author luzuheng
 * @since 2021-06-29 08:49
 */
public class Byte1 {
    public static void main(String[] args) {
        int i = 0;
        i = i++;
        System.out.println(i); // 0

        int j = 0;
        j = ++j;
        System.out.println(j); // 1

        int k = 0;
        int k1 = k++;
        System.out.println(k); // 1
        System.out.println(k1); // 0

        int h = 0;
        int h1 = ++h;
        System.out.println(h); // 1
        System.out.println(h1); // 1
    }
}
