package Chapter1;


import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.In;

public class Main {

    /**
     * Euclid's algorithm
     */
    public static int gcd(int p, int q) {
        if (q == 0 || p == 0) return p + q;

        if (p > q) {
            int r = p % q;
            return gcd(q, r);
        } else {
            int r = q % p;
            return gcd(p, r);
        }
    }

    public static int abs(int x) {
        if (x < 0) return -x;
        else return x;
    }

    public static double abs(double x) {
        if (x < 0) return -x;
        else return x;
    }

    public static boolean isPrime(int p) {
        if (p < 2) return false;
        for (int i = 2; i <= p / 2; i++)
            if (p % i == 0) return false;
        return true;
    }

    /**
     * Квадратный корень(Метод Ньютона - касательных)
     */
    public static double sqrt(double c) {
        if (c < 0) return Double.NaN;

        double err = Double.MIN_NORMAL;
        double t = c;
        while (Math.abs(t - (c / t)) > (err * t))
            t = (c / t + t) / 2.0;
        return t;
    }

    /**
     * Нахождение гармонического числа
     */
    public static double H(int n) {
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }

    public static int mystery(int a, int b) {
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }

    public static void Increase(int a, int b, int c) {
        int t;
        if (a > b) {
            t = a;
            a = b;
            b = t;
        }
        if (a > c) {
            t = a;
            a = c;
            c = t;
        }
        if (b > c) {
            t = b;
            b = c;
            c = t;
        }
        System.out.println(a + ",  " + b + ",  " + c);
    }

    public static boolean[][] workArray(boolean[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = gcd(i, j) == 1;
            }
        }
        return array;
    }

    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++)
            if (str.charAt(i) != str.charAt(str.length() - i - 1))
                return false;
        return true;

    }

    public static String unzipPro(String str) {
        int dot = str.indexOf(".");
        String base = str.substring(0, dot);
        String extension = str.substring(dot + 1, str.length());

        return base + " " + extension;
    }

    public static Date[] readDates(String date) {
        In in = new In(date);
        LinkedQueue<String> queue = new LinkedQueue<>();
        while (!in.isEmpty()) {
            queue.enqueue(in.readString());
        }
        Date[] dates = new Date[queue.size()];
        for (int i = 0; i < queue.size(); i++) {
            dates[i] = new Date(queue.dequeue());
        }
        return dates;
    }

    public static TransactionImpl[] readTransactions(String transaction) {
        In in = new In(transaction);
        LinkedQueue<String> queue = new LinkedQueue<>();
        while (!in.isEmpty())
            queue.enqueue(in.readString());
        TransactionImpl[] transactions = new TransactionImpl[queue.size()];
        for (int i = 0; i < queue.size(); i++) {
            transactions[i] = new TransactionImpl(queue.dequeue());
        }
        return transactions;
    }

    public static String toJadenSmith(String phrase) {
        String str;
        if (phrase == null || phrase.length() == 0) {
            return null;
        } else str = phrase.toUpperCase().substring(0, 1);


        for (int i = 1; i < phrase.length(); i++) {
            if (phrase.charAt(i - 1) == ' ') {
                str += Character.toUpperCase(phrase.charAt(i));
            } else str += phrase.charAt(i);
        }
        return str;
    }


    public static void main(String[] args) {
//        System.out.println(H(11));
//        System.out.println(Math.sin(Math.toRadians(90)));
//        System.out.printf("%14.5s\n", "Hello, World");
//        double sum = 0.0;
//        int i = 0;
//        while (!StdIn.isEmpty()) {
//            //Reading number and accumulation sum
//            sum += StdIn.readDouble();
//            i++;
//        }
//        double avg = sum/ i;
//        StdOut.printf("Среднее - %.5f\n", avg);
//        StdDraw.line(0,0,1,1);
//        StdDraw.line(0.5,0.5,0,0.5);
//        StdDraw.circle(0.5,0.5, 0.199);
//        int n = 100;
//        StdDraw.setXscale(0, 100);
//        StdDraw.setYscale(0, 100*100);
//        StdDraw.setPenRadius(.01);
//
//        for (int i = 1; i <= 100; i++) {
//            StdDraw.point(i, i);
//            StdDraw.point(i, i * i);
//            StdDraw.point(i, i * Math.log(i));

//        int N = 50;
//        double[] a = new double[N];
//        for (int i = 0; i < N; i++) {
//            a[i] = StdRandom.random();
//        }
//        for (int i = 0; i < N; i++) {
//
//            StdDraw.filledRectangle(1.0 * i / N, a[i] / 2.0, 0.5 / N, a[i] / 2.0);
//        }
//        int n = 50;
//        double[] a = new double[n];
//        for (int i = 0; i < n; i++) {
//            a[i] = StdRandom.random();
//        }
//        Arrays.sort(a);
//        for (int i = 0; i < n; i++) {
//
//            StdDraw.filledRectangle(1.0 * i / n, a[i] / 2.0, 0.5 / n, a[i] / 2.0);
//        }

//        double x = 0.1;
//        double y = 0.8 ;
//        if ((x > 0) && (x < 1) &&(y > 0) &&(y < 1))
//            System.out.println("x and y are locate between");
        /** Двоичное представление положительного целого N */
        /** String s = "";
         for (int i = N; i > 0 ; i /= 2) {
         s = (i %2) + s;
         }
         System.out.println(s); */

        /** boolean[] ch = {true, false, true, false, true, true, false, false};
         for (int i = 0; i < ch.length; i++) {
         if (ch[i]) System.out.print("*");
         else System.out.print("_");
         }*/
//        int a = 2;
//        int b = 25;
//        System.out.println(mystery(a, b));
//        double a = Integer.parseInt("123");
//        double b = Integer.parseInt("2");
//
//        System.out.print("ПИЗДЕЦ ");
//        System.out.printf("%2f\n", a / b);
//        double xlo = 0.2;
//        double xhi = 0.5;
//        double ylo = 0.5;
//        double yhi = 0.6;
//        int T = 1000000;
//
//        Interval1D xint = new Interval1D(xlo, xhi);
//        Interval1D yint = new Interval1D(ylo, yhi);
//        Interval2D box = new Interval2D(xint, yint);
//
//        box.draw();
//        Counter c = new Counter("Hits");
//
//        for (int i = 0; i < T; i++) {
//            double x = Math.random();
//            double y = Math.random();
//
//            Point2D p = new Point2D(x, y);
//            if (box.contains(p)) c.increment();
//            else p.draw();
//        }
//        System.out.println(c);
//        System.out.println(box.area());
//        Out out = new Out("out.txt");
//        In in = new In("in1.txt");
//        String s = in.readAll();
//        out.println(s);
//
//
//        in = new In("in2.txt");
//        s = in.readAll();
//        out.println(s);
//        in.close();
//        out.close();
//
//        int m = Integer.parseInt("12");
//        int d = Integer.parseInt("31");
//        int y = Integer.parseInt("1999");
//
//        DateFirstRealize dateFirstRealize = new DateFirstRealize(m, d, y);
//        System.out.println(dateFirstRealize);

//        var m1 = Integer.parseInt("08");
//        var d1 = Integer.parseInt("31");
//        var y1 = Integer.parseInt("2018");
//
//        DateSecondRealize dateSecondRealize = new DateSecondRealize(m1, d1, y1);
//        System.out.println(dateSecondRealize + " скоро, блять, день рождения");
//        int T = Integer.parseInt("200");
//        Accumulator a = new Accumulator(T, 1.0);
//
//        for (int i = 0; i < T; i++) {
//            a.addDataValue(StdRandom.random());
//        }
//        System.out.println(a);
//        DateFirstRealize date = new DateFirstRealize(10,  31, 1909);
//        System.out.println(date);
//
//        DateFirstRealize date1 = new DateFirstRealize(11,31,1909);
//        System.out.println(date1);
//
//        if(date1.equals(date)) System.out.println("Даты совпадают и method equals() runs");
//        date = date1;
//        System.out.println(date);
//        System.out.println(date1);


//        DateFirstRealize date1 = new DateFirstRealize(11, 5, 2001);(
//        DateFirstRealize date2 = new DateFirstRealize(11, 5, 2001);
//
//        TransactionImpl trans1 = new TransactionImpl("Mr.Jevs", date1, 200000);
//
//        TransactionImpl trans2 = new TransactionImpl("Mr.Jevs", date2, 200000);
//
//        if(trans1.equals(trans2)) System.out.println(true);
//        else System.out.println(false);

//        Rational rat1 = new Rational(30,40);
//        Rational rat2 = new Rational(6, 8);
//        System.out.println(rat1.equals(rat2));

//        RandomBag<Character> bag = new RandomBag<>(4);
//
//        for (int i = 0; i < 4; i++) {
//            bag.add((char) ('A' + i));
//        }

//        while (i != 24) {
//            i++;
//            for (char ch :
//                    bag) {
//                System.out.print(ch + " ");
//            }
//            System.out.println(i);
//
//        }

//        int N = Integer.parseInt("100");
//        for (int i = 0; i < N; i++) {
//            System.out.printf("%4d", StdRandom.uniform(0, N - 1));
//        }

        System.out.println(toJadenSmith(null));

    }
}