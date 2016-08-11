package newpackage;

public class BunkinFunctionN6 implements TestingFunction {
    public BunkinFunctionN6() {
    }
    
    @Override
    public double getResult( double x, double y ) {
        return 100 * Math.sqrt( Math.abs( y - 0.01 * x * x ) ) + 0.01 * Math.abs( x + 10 );
    }
    
    @Override
    public double getXRangeMin() {
        return -15;
    }
    
    @Override
    public double getXRangeMax() {
        return -5;
    }
    
    @Override
    public double getYRangeMin() {
        return -3;
    }
    
    @Override
    public double getYRangeMax() {
        return 3;
    }
}
