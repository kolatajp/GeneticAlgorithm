package newpackage;

import java.util.ArrayList;

public class Base256 {
    private static int coIle = 8;
    
    public static Integer[] convert( long n, int places ) {
        String bin = Long.toString( n, 2 );
        //System.out.println( "binary:" + bin );
        ArrayList<Integer> al;
        al = new ArrayList<Integer>();
        int tmp = 0;
        //int coIle = 3;
        for ( int i=bin.length()-1, iter=0; i>=0; i--, iter++ ) {
            
            if ( iter%coIle == 0 && iter != 0 ) {
                al.add( tmp );
                tmp = 0;
            }
            
            if ( bin.charAt( i ) == '1' ) {
                tmp += Math.pow( 2, iter%coIle );
            }
        }
        
        al.add( new Integer( tmp ) );
        tmp = 0;
        
        while ( al.size() < places ) {
            al.add( 0 );
        }
        
        Integer[] out = new Integer[al.size()];
        
        for ( Object al1 : al ) {
            //System.out.println( "[" + al1 + "]" );
        }
        
        return al.toArray( out );
    }
    
    public static int[] convert( long n ) {
        String bin = Long.toString( n, 2 );
        System.out.println( "binary:" + bin );
        ArrayList al;
        al = new ArrayList<Integer>();
        int tmp = 0;
        //int coIle = 3;
        for ( int i=bin.length()-1, iter=0; i>=0; i--, iter++ ) {
            
            if ( iter%coIle == 0 && iter != 0 ) {
                al.add( tmp );
                tmp = 0;
            }
            
            if ( bin.charAt( i ) == '1' ) {
                tmp += Math.pow( 2, iter%coIle );
            }
        }
        
        al.add( tmp );
        tmp = 0;
        
        for ( Object al1 : al ) {
            System.out.println( "[" + al1 + "]" );
        }
        
        return null;
    }
}
