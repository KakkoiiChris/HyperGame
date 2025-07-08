package kakkoiichris.hypergame.util.math.easing;

public class Bounce {
    private static final double P0 = 1.0 / 2.75;
    private static final double P1 = 2.0 / 2.75;
    private static final double P2 = 2.5 / 2.75;

    private static final double DT = 7.5625;

    private static final double OT1 = 1.5 / 2.75;
    private static final double OT2 = 2.25 / 2.75;
    private static final double OT3 = 2.625 / 2.75;

    private static final double AT1 = .75;
    private static final double AT2 = .9375;
    private static final double AT3 = .984375;

    public static double easeIn(double t, double b, double c, double d) {
        return c - easeOut(d - t, 0, c, d) + b;
    }

    public static double easeOut(double t, double b, double c, double d) {
        if ((t /= d) < P0) {
            return c * (DT * t * t) + b;
        }
        else if (t < P1) {
            return c * (DT * (t -= OT1) * t + AT1) + b;
        }
        else if (t < P2) {
            return c * (DT * (t -= OT2) * t + AT2) + b;
        }
        else {
            return c * (DT * (t -= OT3) * t + AT3) + b;
        }
    }

    public static double easeInOut(double t, double b, double c, double d) {
        if (t < d / 2) {
            return easeIn(t * 2, 0, c, d) * .5 + b;
        }
        else {
            return easeOut(t * 2 - d, 0, c, d) * .5 + c * .5 + b;
        }
    }
}