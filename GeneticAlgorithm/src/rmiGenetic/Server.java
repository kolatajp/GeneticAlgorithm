package rmiGenetic;

import java.rmi.registry.Registry;

public class Server {
    public static void main( String[] args ) {
        try {
            int port = 1099;
            if ( args.length > 0 ) {
                port = Integer.parseInt( args[0] );
            }
            Registry r = java.rmi.registry.LocateRegistry.createRegistry( port );
            r.rebind( "GeneticAlgorithm", new GeneticAlgorithmRemote() );
            
            System.out.println( "Server started at port " + port );
        } catch ( Exception e ) {
            System.out.println( e );
            System.out.println( "Server failed" );
        }
    }
}
