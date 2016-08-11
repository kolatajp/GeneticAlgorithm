package newpackage;

public class BoothsFunction implements TestingFunction {
    public BoothsFunction() {
    }
    
    @Override
    public double getResult( double x, double y ) {
        return Math.pow( x+2*y-7, 2 ) + Math.pow( 2*x+y-5, 2 );
    }

    @Override
    public double getXRangeMin() {
        return -10;
    }

    @Override
    public double getXRangeMax() {
        return 10;
    }

    @Override
    public double getYRangeMin() {
        return -10;
    }

    @Override
    public double getYRangeMax() {
        return 10;
    }
}
