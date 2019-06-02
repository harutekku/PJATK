package rafal;

public class Complex {

    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;

    }


    public double re() {
        return this.re;
    }

    public double im() {
        return this.im;
    }

    public Complex add(Complex z) {
        return new Complex(
                this.re + z.re(), this.im + z.im());
    }

    public Complex sub(Complex z) {
        return new Complex(
                this.re - z.re(), this.im - z.im());
    }

    public Complex multiply(Complex z) {

        if (this.im == 0. || z.im() == 0.) {
            return new Complex(this.re * z.re(),0);
        }

        return new Complex(
                (this.re * z.re()) - (this.im * z.im()),
                (this.re * z.im()) + (this.im * z.re()));
    }

    public Complex divide(Complex z) {
        double c = z.re();
        double d = z.im();

        double zre2 = 0.0;
        if (c != 0.0) {
            zre2 = StrictMath.pow(c, 2.);
        }

        double zim2 = 0.0;
        if (d != 0.0) {
            zim2 = StrictMath.pow(d, 2.);
        }


        double ac = this.re * c;
        double bd = this.im * d;
        double bc = this.im * c;
        double ad = this.re * d;

        return new Complex((ac + bd) / (zre2 + zim2), (bc - ad) / (zre2 + zim2));


    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }


}
