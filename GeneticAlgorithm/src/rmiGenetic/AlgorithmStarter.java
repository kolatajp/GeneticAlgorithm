package rmiGenetic;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AlgorithmStarter extends Thread implements Runnable {
    String config;
    ArrayList<String> rankList;
    int port = 1099;
    
    public AlgorithmStarter( String config, ArrayList<String> rankList, int port ) {
        this.config = config;
        this.rankList = rankList;
        this.port = port;
    }
    
    public AlgorithmStarter( String config, ArrayList<String> rankList ) {
        this.config = config;
        this.rankList = rankList;
    }
    
    @Override
    public void run() {
        String[] configArgs = config.split( " " );
        try {
            System.out.println( "Algorithm starting at url:" + "rmi://" + configArgs[6] + ":" + port + "/" + "GeneticAlgorithm" );
            GeneticAlgorithmInterface ga = (GeneticAlgorithmInterface) Naming.lookup( "rmi://" + configArgs[6] + ":" + port + "/" + "GeneticAlgorithm" );
                    

            //init
            ga.init( Double.parseDouble( configArgs[1] ),
                     Double.parseDouble( configArgs[2] ),
                     Double.parseDouble( configArgs[3] ),
                     Integer.parseInt( configArgs[4] ),
                     Integer.parseInt( configArgs[5] ) );

            //wybor funkcji
            ga.setFunction( configArgs[0] );
            
            //init pop
            ga.initPopulation();

            //run
            ga.computeEvolution();
            rankList.add( ga.getBestSerial() );
            
            System.out.println( "Thread succeeded" );
        } catch ( NotBoundException | MalformedURLException ex ) {
            System.out.println( ex );
        } catch ( RemoteException ex ) {
            System.out.println( ex );
        }
    }
}
