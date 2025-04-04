package kakkoiichris.hypergame.util.math.easing;

public class Sine {
    public static double easeIn(double t, double b, double c, double d) {
        return -c * Math.cos(t / d * (Math.PI / 2)) + c + b;
    }

    public static double easeOut(double t, double b, double c, double d) {
        return c * Math.sin(t / d * (Math.PI / 2)) + b;
    }

    public static double easeInOut(double t, double b, double c, double d) {
        return -c / 2 * (Math.cos(Math.PI * t / d) - 1) + b;
    }
}