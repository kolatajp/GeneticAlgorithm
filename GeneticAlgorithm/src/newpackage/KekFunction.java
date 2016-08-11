package newpackage;

public class KekFunction implements TestingFunction {
    public KekFunction() {
    }
    
    @Override
    public double getResult( double x, double y ) {
        return (x*x*y+(y+x*x)) + Math.sin( 5*y + 3*x )-502;
    }

    @Override
    public double getXRangeMin() {
        return -4.5;
    }

    @Override
    public double getXRangeMax() {
        return 4.5;
    }

    @Override
    public double getYRangeMin() {
        return -4.5;
    }

    @Override
    public double getYRangeMax() {
        return 4.5;
    }
}
