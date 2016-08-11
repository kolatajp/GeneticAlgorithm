package newpackage;

public class Specimen {
    private double x, y;
    private double fitness;

    public Specimen( double x, double y, double fitness ) {
        this.x = x;
        this.y = y;
        this.fitness = fitness;
    }

    public Specimen( double x, double y ) {
        this.x = x;
        this.y = y;
        this.fitness = 0.0;
    }

    public double getFitness() {
        return fitness;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setFitness( double fitness ) {
        this.fitness = fitness;
    }

    public void setX( double x ) {
        this.x = x;
    }

    public void setY( double y ) {
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "" + this.getX() + ":" + this.getY() + " (" + this.getFitness() + ")";
    }
    
    public String serial() {
        return "" + this.getX() + " " + this.getY() + " " + this.getFitness();
    }
    
    public void deserial( String s ) {
        String[] vals = s.split( " " );
        this.setX( Double.parseDouble( vals[0] ) );
        this.setY( Double.parseDouble( vals[1] ) );
        this.setFitness( Double.parseDouble( vals[2] ) );
    }
}
