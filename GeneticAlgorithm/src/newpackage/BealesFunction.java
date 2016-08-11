package newpackage;

public class BealesFunction implements TestingFunction {
    public BealesFunction() {
    }
    
    @Override
    public double getResult( double x, double y ) {
        return Math.pow( 1.5 - x + x * y, 2 ) + Math.pow( 2.25 - x + x * y * y, 2 ) + Math.pow( 2.625 - x + x * y * y * y, 2 );
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
