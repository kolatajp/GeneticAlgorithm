package newpackage;

public class LeviFunction implements TestingFunction {
    public LeviFunction() {
    }

    @Override
    public double getResult( double x, double y ) {
        return Math.pow( Math.sin( 3 * Math.PI * x ), 2 ) + (x - 1) * (x - 1) * (1 + Math.pow( Math.sin( 3 * Math.PI * y ), 2 ))
               + (y - 1) * (y - 1) * (1 + Math.pow( Math.sin( 2 * Math.PI * y ), 2 ));
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
