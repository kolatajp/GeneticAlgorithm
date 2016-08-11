package newpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class GeneticAlgorithm {
    private double crossBreedProbability;
    private double mutationProbability;
    private double popPercentageToBreed;
    private ArrayList<Specimen> population;
    private int populationSize;

    private int maxGenerations;
    private int currentGeneration = 0;

    private TestingFunction function;
    
    private Specimen best;
    private int bestFoundInGen = 0;

    public GeneticAlgorithm() {
    }
    
    public GeneticAlgorithm( TestingFunction function, double crossBreedProbability,
                             double mutationProbability, double popPercentageToBreed,
                             int populationSize, int generations ) {
        this.function = function;
        this.crossBreedProbability = crossBreedProbability;
        this.mutationProbability = mutationProbability;
        this.popPercentageToBreed = popPercentageToBreed;
        this.populationSize = populationSize;
        this.maxGenerations = generations;

        //losowanie
        this.initPopulation();
    }
    
    public void init( TestingFunction function, double crossBreedProbability,
                             double mutationProbability, double popPercentageToBreed,
                             int populationSize, int generations ) {
        this.function = function;
        this.crossBreedProbability = crossBreedProbability;
        this.mutationProbability = mutationProbability;
        this.popPercentageToBreed = popPercentageToBreed;
        this.populationSize = populationSize;
        this.maxGenerations = generations;
    }

    private void initPopulation() {
        Random generator = new Random();
        double xRange = this.function.getXRangeMax() - this.function.getXRangeMin();
        double yRange = this.function.getYRangeMax() - this.function.getYRangeMin();
        double xMin = this.function.getXRangeMin();
        double yMin = this.function.getYRangeMin();

        this.population = new ArrayList<Specimen>();

        for ( int i = 0; i < this.maxGenerations; i++ ) {
            Specimen s = new Specimen( generator.nextDouble() * xRange + xMin,
                                       generator.nextDouble() * yRange + yMin );
            this.population.add( s );
        }
    }
    
    public void computeEvolution() {
        this.calculateFitness();
        int index = this.rankPopulation();
        for ( int i = 0; i < this.maxGenerations; i++ ) {
            ArrayList<Specimen> newPop = this.calculateNewPopulation( index );
            this.setPopulation( newPop );
            this.calculateFitness();
            index = this.rankPopulation();
        }
    }

    public void calculateFitness() {
        for ( Specimen s : population ) {
            double result = this.function.getResult( s.getX(), s.getY() );
            result = 1 / result;
            s.setFitness( result );
        }
        
        this.currentGeneration++;
    }

    public Specimen getBest() {
        return best;
    }

    public void setBest( Specimen best ) {
        this.best = best;
    }
    
    //zwraca indeks ostatniego z gory osobnika, przeznaczonego do krzyzowania
    public int rankPopulation() {
        Collections.sort( this.population, new Comparator<Specimen>() {
            public int compare( Specimen s1, Specimen s2 ) {
                double result = s2.getFitness() - s1.getFitness();                
                if ( result > 0 ) {
                    return 1;
                } else if ( result < 0 ) {
                    return -1;
                } else {
                    return 0;
                }
            }
        } );

        if ( this.best == null ) {
            this.best = this.population.get( 0 );
        } else if ( this.population.get( 0 ).getFitness() > this.best.getFitness() ) {
            this.best = this.population.get( 0 );
            this.bestFoundInGen = this.currentGeneration;
        }
        
        int lastIndex = (int) (this.popPercentageToBreed * this.populationSize);
        return lastIndex;
    }

    public ArrayList<Specimen> calculateNewPopulation( int lastBreedIndex ) {
        ArrayList<Specimen> newPop = new ArrayList<Specimen>();
        ArrayList<Specimen> breeders = new ArrayList<Specimen>();

        for ( int i = 0; i < lastBreedIndex; i++ ) {
            double pickedRandom = new Random().nextDouble();            
            if ( pickedRandom <= this.crossBreedProbability ) {
                breeders.add( this.population.get( i ) );
            } else {
                newPop.add( this.population.get( i ) );
            }
        }
        
        //dopoki nowa populacja nie zastapi starej, krzyzuje
        while ( newPop.size() < this.populationSize ) {
            ArrayList<Specimen> breedTmp = new ArrayList<Specimen>();
            for ( Specimen s : breeders ) {
                breedTmp.add( s );
            }
            
            //jezeli jest 0 breedersow (straszna degeneracja)
            //to wypelnij nowe pokolenie tylko najlepszymi z newPop
            if ( breedTmp.size() == 0 ) {
                for ( int i=0; newPop.size() < this.populationSize; i++ ) {
                    newPop.add( newPop.get( i%newPop.size() ) );
                }
            }
            //jezeli, rzadko bo rzadko, zdarzy sie, ze zostanie tylko jeden osobnik
            //do krzyzowania, to po prostu dodam go do populacji nowej
            if ( breedTmp.size() == 1 ) {
                newPop.add( breedTmp.remove( 0 ) );
            }
            
            //dopoki nowa populacja nie zastapi poprzedniej
            while ( newPop.size() < this.populationSize && breedTmp.size() > 1 ) {
                Specimen father = breedTmp.remove( 0 );
                Specimen mother = breedTmp.remove( new Random().nextInt( breedTmp.size() ) );
                Specimen child = this.cross( father, mother );
                newPop.add( child );
            }

            //jezeli zostanie tylko jeden osobnik
            //do krzyzowania, to po prostu dodam go do populacji nowej
            //if ( breedTmp.size() == 1 && newPop.size() < this.populationSize ) {
            //    newPop.add( breedTmp.remove( 0 ) );
            //}
        }
        
        //mutacja
        for ( int i=0; i<newPop.size(); i++ ) {
            if ( new Random().nextDouble() < this.mutationProbability ) {
                Specimen xman = newPop.get( i );
                double x = xman.getX();
                double y = xman.getY();
                xman.setX( y );
                xman.setY( x );
                newPop.set( i, xman );
            }
        }
        
        return newPop;
    }

    private Specimen cross( Specimen a, Specimen b ) {
        double range = new Random().nextGaussian();
        if ( range < -1.0 ) {
            range = 1.0;
        } else if ( range > 1.0 ) {
            range = 1.0;
        }

        range += 1;
        range = range / 2.0;

        double xRange = Math.abs( a.getX() - b.getX() );
        double yRange = Math.abs( a.getY() - b.getY() );

        double newAdditionalX = range * xRange;
        double newAdditionalY = range * yRange;

        double newX, newY;

        if ( a.getX() < b.getX() ) {
            newX = a.getX() + newAdditionalX;
        } else {
            newX = b.getX() + newAdditionalX;
        }

        if ( a.getY() < b.getY() ) {
            newY = a.getY() + newAdditionalY;
        } else {
            newY = b.getY() + newAdditionalY;
        }
        
        return new Specimen( newX, newY );
    }

    //--------GET-SET----------
    
    public double getCrossBreedProbability() {
        return crossBreedProbability;
    }

    public int getCurrentGeneration() {
        return currentGeneration;
    }

    public TestingFunction getFunction() {
        return function;
    }

    public int getMaxGenerations() {
        return maxGenerations;
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public double getPopPercentageToBreed() {
        return popPercentageToBreed;
    }

    public ArrayList<Specimen> getPopulation() {
        return population;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setCrossBreedProbability( double crossBreedProbability ) {
        this.crossBreedProbability = crossBreedProbability;
    }

    public void setCurrentGeneration( int currentGeneration ) {
        this.currentGeneration = currentGeneration;
    }

    public void setFunction( TestingFunction function ) {
        this.function = function;
    }

    public void setMaxGenerations( int maxGenerations ) {
        this.maxGenerations = maxGenerations;
    }

    public void setMutationProbability( double mutationProbability ) {
        this.mutationProbability = mutationProbability;
    }

    public void setPopPercentageToBreed( double popPercentageToBreed ) {
        this.popPercentageToBreed = popPercentageToBreed;
    }

    public void setPopulation( ArrayList<Specimen> population ) {
        this.population = population;
    }

    public void setPopulationSize( int populationSize ) {
        this.populationSize = populationSize;
    }
    
    public int getBestFoundInGen() {
        return bestFoundInGen;
    }

    public void setBestFoundInGen( int bestFoundInGen ) {
        this.bestFoundInGen = bestFoundInGen;
    }
}
