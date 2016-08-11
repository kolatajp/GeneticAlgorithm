package newpackage;

public class RosenbrockFunction implements TestingFunction {
    public RosenbrockFunction() {
    }
    
    @Override
    public double getResult( double x, double y ) {
        return Math.pow( 1-x, 2 ) + 100 * Math.pow( y-x*x, 2 );
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
