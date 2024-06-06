package kakkoiichris.hypergame.util.math.easing;

public class Back {
    private static final double S = 1.70158;
    private static final double DS = 1.525;
    
    public static double easeIn(double t, double b, double c, double d) {
        double s = S;
        
        return c * (t /= d) * t * ((s + 1) * t - s) + b;
    }
    
    public static double easeIn(double t, double b, double c, double d, double s) {
        return c * (t /= d) * t * ((s + 1) * t - s) + b;
    }
    
    public static double easeOut(double t, double b, double c, double d) {
        double s = S;
        
        return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
    }
    
    public static double easeOut(double t, double b, double c, double d, double s) {
        return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
    }
    
    public static double easeInOut(double t, double b, double c, double d) {
        double s = S;
        
        if ((t /= d / 2) < 1) {
            return c / 2 * (t * t * (((s *= DS) + 1) * t - s)) + b;
        }
        
        return c / 2 * ((t -= 2) * t * (((s *= DS) + 1) * t + s) + 2) + b;
    }
    
    public static double easeInOut(double t, double b, double c, double d, double s) {
        if ((t /= d / 2) < 1) {
            return c / 2 * (t * t * (((s *= DS) + 1) * t - s)) + b;
        }
        
        return c / 2 * ((t -= 2) * t * (((s *= DS) + 1) * t + s) + 2) + b;
    }
}