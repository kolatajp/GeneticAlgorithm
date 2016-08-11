package newpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class NewMain {
    public static void main( String[] args ) {
        TestingFunction f
                        = //new BealesFunction()
                //new BoothsFunction();
                //new BunkinFunctionN6();
                //new RosenbrockFunction();
                //new KekFunction();
                //new SphereFunction()
                new LeviFunction()
        ;
        //function, crossBreedProbability, mutationProbability, popPercentageToBreed, populationSize, generations )
        GeneticAlgorithm ga = new GeneticAlgorithm( f, 0.7, 0.05, 0.3, 3000, 1000 );

        //deb
        System.out.println( ga.getCrossBreedProbability() );

        /*ga.calculateFitness();
         int ind = ga.rankPopulation();
         for ( int i = 0; i < 1000; i++ ) {
         ArrayList<Specimen> newPop = ga.calculateNewPopulation( ind );
         ga.setPopulation( newPop );
         ga.calculateFitness();
         ind = ga.rankPopulation();
         }*/
        ga.computeEvolution();

        for ( int i = 0; i < 10; i++ ) {
            Specimen s = ga.getPopulation().get( i );
            System.out.println( i + "--->" + s.getX() + ":" + s.getY() + "=\n"
                                + f.getResult( s.getX(), s.getY() ) + " (" + s.getFitness() + ")" );
        }

        System.out.println( "--------\n" + ga.getBest() + " == " + f.getResult( ga.getBest().getX(), ga.getBest().getY() ) + "; gen=" + ga.getBestFoundInGen() );

        int bestX = -1, bestY = -1;

        Range2ColorWide r2cs = new Range2ColorWide();

        double maxRes = Math.pow( 10, 2 ) * 1;
        int imgWidth = 500;
        int imgHeight = 500;
        BufferedImage im = new BufferedImage( imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB );
        Graphics g = im.getGraphics();
        for ( int i = -(imgWidth / 2), ii = 0; ii < imgWidth; i++, ii++ ) {
            for ( int j = (imgHeight / 2), jj = 0; jj < imgHeight; j--, jj++ ) {
                double res = Math.abs( f.getResult( i, j ) );
                //deb
                //System.out.println( res );
                double preHeight = (res / maxRes);
                //deb
                //System.out.println( res + "/" + maxRes );
                if ( preHeight > 1.0 ) {
                    preHeight = 1.0;
                }
                //deb
                //System.out.println( "" + preHeight );
                int height = (int) (preHeight * (r2cs.map.length - 1));
                int[] rgb = r2cs.getColor( height );
                g.setColor( new Color( rgb[0], rgb[1], rgb[2] ) );
                g.drawRect( ii, jj, 1, 1 );

                if ( i == (int) ga.getBest().getX() && j == (int) ga.getBest().getY() ) {
                    //g.setColor( Color.red );
                    //g.drawLine( 0, jj, im.getWidth(), jj );
                    //g.drawLine( ii, 0, ii, im.getHeight() );
                    bestX = ii;
                    bestY = jj;
                }
            }
        }

        //dodaje osobniki
        g.setColor( Color.red );
        for ( Specimen s : ga.getPopulation() ) {
            //deb
            //System.out.println( (((int) s.getX()) + imgWidth / 2) + "/" + (((int) s.getY()) + imgHeight / 2) );
            //g.drawRect( ((int) s.getX()) + imgWidth / 2, ((int) s.getY()) + imgHeight / 2, 100, 100 );
        }

        g.setColor( Color.blue );
        g.drawLine( 0, im.getHeight() / 2, im.getWidth(), im.getHeight() / 2 );
        g.drawLine( im.getWidth() / 2, 0, im.getWidth() / 2, im.getHeight() );

        g.setColor( Color.green );
        g.drawLine( 0, bestY, im.getWidth(), bestY );
        g.drawLine( bestX, 0, bestX, im.getHeight() );

        g.setColor( Color.red );
        g.drawLine( 0, 1 + im.getHeight() / 2, im.getWidth(), 1 + im.getHeight() / 2 );
        g.drawLine( 1 + im.getWidth() / 2, 0, 1 + im.getWidth() / 2, im.getHeight() );

        try {
            ImageIO.write( im, "png", new File( "C:\\Users\\Paweł\\Desktop\\kdsajdksajdsalk.png" ) );
        } catch ( IOException e ) {
            System.out.println( e );
        }
    }
}
