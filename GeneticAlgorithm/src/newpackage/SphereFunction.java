package newpackage;

public class SphereFunction implements TestingFunction {
    public SphereFunction() {
    }
    
    @Override
    public double getResult( double x, double y ) {
        return x*x+y*y;
    }

    @Override
    public double getXRangeMin() {
        return -10000;
    }

    @Override
    public double getXRangeMax() {
        return 10000;
    }

    @Override
    public double getYRangeMin() {
        return -10000;
    }

    @Override
    public double getYRangeMax() {
        return 10000;
    }
}
