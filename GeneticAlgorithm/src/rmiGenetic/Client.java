package rmiGenetic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import newpackage.Specimen;

public class Client {
    public static void main( String[] args ) {
        ArrayList<String> linesArray = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader( new FileReader( new File( args[0] ) ) );
            String line = bf.readLine();
            int port = 1099;
            try {
                port = Integer.parseInt( args[1] );
            } catch ( Exception e ) {
                
            }
            System.out.println( "Looking at port " + port );
            
            while ( line != null ) {
                linesArray.add( line );
                line = bf.readLine();
            }

            ArrayList<Specimen> results = new ArrayList<>();
            ArrayList<String> resultsString = new ArrayList<>();
            AlgorithmStarter[] threads = new AlgorithmStarter[linesArray.size()];
            for ( int i = 0; i < linesArray.size(); i++ ) {
                threads[i] = new AlgorithmStarter( linesArray.get( i ), resultsString, port );
            }

            //starting
            System.out.println( "Starting remote threads" );
            for ( AlgorithmStarter thread : threads ) {
                thread.start();
            }

            //waiting
            int resultsSize = resultsString.size();
            while ( resultsSize < linesArray.size() ) {
                resultsSize = resultsString.size();
                System.out.print( "" );
            }

            //deserialSpecimen
            for ( int i = 0; i < resultsString.size(); i++ ) {
                Specimen newSpec = new Specimen( -1, -1 );
                newSpec.deserial( resultsString.get( i ) );
                results.add( newSpec );
            }

            //sortowanie wynikow
            Collections.sort( results, new Comparator<Specimen>() {
                public int compare( Specimen s1, Specimen s2 ) {
                    double result = s1.getFitness() - s2.getFitness();

                    if ( result < 0 ) {
                        return 1;
                    } else if ( result > 0 ) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            } );

            //print
            System.out.println( "Results ranked:" );
            for ( int i = 0; i < results.size(); i++ ) {
                System.out.println( i + 1 + ": " + results.get( i ) );
            }
        } catch ( FileNotFoundException ex ) {
            System.out.println( "File not found" );
        } catch ( IOException ex ) {
            System.out.println( "Error reading the file" );
        }

        System.out.println( "Client exits" );
    }
}
