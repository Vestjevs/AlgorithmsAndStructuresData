package Chapter1;

public class Rational {
    private final long numerator;
    private final long denominator;

    Rational(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Rational plus(Rational b) {
        Rational ans = new Rational(this.numerator * b.denominator + b.numerator * this.denominator, this.denominator * b.denominator);

        if (gcd(ans.numerator, ans.denominator) != 1)
            ans = new Rational(ans.numerator / gcd(ans.numerator, ans.denominator), ans.denominator / gcd(ans.numerator, ans.denominator));
        return ans;
    }

    public Rational minus(Rational b) {
        Rational ans = new Rational(this.numerator * b.denominator - b.numerator * this.denominator, this.denominator * b.denominator);

        if (gcd(ans.numerator, ans.denominator) != 1)
            ans = new Rational(ans.numerator / gcd(ans.numerator, ans.denominator), ans.denominator / gcd(ans.numerator, ans.denominator));
        return ans;
    }

    public Rational times(Rational b) {
        Rational ans = new Rational(this.numerator * b.numerator, this.denominator * b.denominator);

        if (gcd(ans.numerator, ans.denominator) != 1)
            ans = new Rational(ans.numerator / gcd(ans.numerator, ans.denominator), ans.denominator / gcd(ans.numerator, ans.denominator));

        return ans;
    }

    public Rational divides(Rational b) {
        Rational ans = new Rational(this.numerator * b.denominator, this.denominator * b.numerator);

        if (gcd(ans.numerator, ans.denominator) != 1)
            ans = new Rational(ans.numerator / gcd(ans.numerator, ans.denominator), ans.denominator / gcd(ans.numerator, ans.denominator));
        return ans;
    }

    private static long gcd(long p, long q) {
        if (q == 0 || p == 0) return p + q;

        if (p > q) {
            long r = p % q;
            return gcd(q, r);
        } else {
            long r = q % p;
            return gcd(p, r);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Rational this1 = this;
        Rational that = (Rational) obj;
        if (gcd(this1.numerator, this1.denominator) != 1) {
            this1 = new Rational(this.numerator / gcd(this.numerator, this.denominator), this.denominator / gcd(this.numerator, this.denominator));
        }
        if (gcd(that.numerator, that.denominator) != 1)
            that = new Rational(that.numerator / (gcd(that.numerator, that.denominator)), that.denominator / gcd(that.numerator, that.denominator));
        if (this1.numerator != that.numerator) return false;
        if (this1.denominator != that.denominator) return false;
        return true;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }


}
